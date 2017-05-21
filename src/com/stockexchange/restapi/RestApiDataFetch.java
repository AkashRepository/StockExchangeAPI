package com.stockexchange.restapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.stockexchange.daos.TickerDao;
import com.stockexchange.entities.TickerBean;
import com.stockexchange.utilities.ConnectionPool;
import com.stockexchange.utilities.FusionCharts;

public class RestApiDataFetch {



	/**
	 * @param TicketName
	 * @brief This function is used to fetch data from 3rd Party API.
	 */

	public static JSONArray getJSONFromApi(String searchTicker){
		JSONArray jsonobj = new JSONArray();
		JSONObject	obj = 	new JSONObject();
		try {

			URL url = new URL("http://finance.google.com/finance/info?client=ig&q=NASDAQ%3A"+searchTicker);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			//				BufferedReader br = new BufferedReader(new InputStreamReader(
			//					(conn.getInputStream())));
			conn.getInputStream().skip(3);

			//				JSONParser lJSONParser = new JSONParser();
			JSONValue l =  new JSONValue();
			try {
				jsonobj = (JSONArray)JSONValue.parseWithException(new InputStreamReader(
						(conn.getInputStream())) );
			} catch (ParseException e) {
				e.printStackTrace();

				obj.put("error-status", "1");
				obj.put("error-msg", "error while parsing REST API data, please try again.");
				jsonobj.add(obj);
			}

			System.out.println("Output from Server .... \n" +jsonobj);
			//				while ((output = br.readLine()) != null) {
			//					System.out.println(output);
			//				}

			conn.disconnect();

		} catch (MalformedURLException e) {


			obj.put("error-status", "1");
			obj.put("error-msg", "URL error at rest api site, please try again.");
			jsonobj.add(obj);
			e.printStackTrace();

		} catch (IOException e) {


			obj.put("error-status", "1");
			obj.put("error-msg", "Error while connecting with 3rd party API, please try again.");
			jsonobj.add(obj);
			e.printStackTrace();

		}
		return jsonobj;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray larr = RestApiDataFetch.getJSONFromApi("GOOG");
		System.out.print(larr.toJSONString().toString());
	}


	public TickerBean saveRealTimeData(JSONArray pRealTimeDataArr) {
		// TODO Auto-generated method stub
		JSONObject pRealTimeDataObj =  (JSONObject) pRealTimeDataArr.get(0);
		TickerDao lTickerDao = new TickerDao();
		
		TickerBean lTickerBean = new TickerBean();
		lTickerBean.setAfter_hours_last_price(checkNullValueForDouble(pRealTimeDataObj.get("el")));
		lTickerBean.setAfter_hours_last_trade_time_formatted(checkNullValue(pRealTimeDataObj.get("elt")));
		lTickerBean.setChange(checkNullValueForDouble(pRealTimeDataObj.get("c")));
		lTickerBean.setChange_percentage(checkNullValueForDouble(pRealTimeDataObj.get("cp")));
		lTickerBean.setExchange(checkNullValue(pRealTimeDataObj.get("e")));
		lTickerBean.setDivident(checkNullValueForDouble(pRealTimeDataObj.get("div")));
		lTickerBean.setDivident_yield(checkNullValueForDouble(pRealTimeDataObj.get("yld")));
		lTickerBean.setId(checkNullValueForLong(pRealTimeDataObj.get("id")));
		lTickerBean.setLast_price(checkNullValueForDouble(pRealTimeDataObj.get("l")));
		lTickerBean.setLast_trade_date_time(checkNullValue(pRealTimeDataObj.get("lt_dts")));
		lTickerBean.setLast_trade_time(checkNullValue(pRealTimeDataObj.get("ltt")));
		lTickerBean.setLast_trade_time_formatted(checkNullValue(pRealTimeDataObj.get("lt")));
		lTickerBean.setPrice(checkNullValueForDouble(pRealTimeDataObj.get("l")));
		lTickerBean.setTicker(checkNullValue(pRealTimeDataObj.get("t")));
		
		lTickerDao.create(lTickerBean);
		return lTickerBean;
	}
	public JSONArray convertObjectToJson(TickerBean lTickerBean){
		JSONArray pRealTimeDataArr = new JSONArray();
		JSONObject pRealTimeDataObj = new JSONObject();
		pRealTimeDataObj.put("el",	lTickerBean.getAfter_hours_last_price());
		pRealTimeDataObj.put("elt",lTickerBean.getAfter_hours_last_trade_time_formatted());
		pRealTimeDataObj.put("c",lTickerBean.getChange());
		pRealTimeDataObj.put("cp", lTickerBean.getChange_percentage());
		pRealTimeDataObj.put("e",lTickerBean.getExchange());
		pRealTimeDataObj.put("div",lTickerBean.getDivident());
		pRealTimeDataObj.put("yld",lTickerBean.getDivident_yield());
		pRealTimeDataObj.put("id",lTickerBean.getId());
		pRealTimeDataObj.put("l", lTickerBean.getLast_price());
		pRealTimeDataObj.put("lt_dts", lTickerBean.getLast_trade_date_time());
		pRealTimeDataObj.put("ltt", lTickerBean.getLast_trade_time());
		pRealTimeDataObj.put("lt",lTickerBean.getLast_trade_time_formatted());
		pRealTimeDataObj.put("i",lTickerBean.getPrice());
		pRealTimeDataObj.put("t",lTickerBean.getTicker());
		pRealTimeDataArr.add(pRealTimeDataObj);
		return pRealTimeDataArr;
	}
	public static String checkNullValue(Object obj){
		return (obj!=null && !obj.toString().equals("")?obj.toString():null);
	}
	public static Double checkNullValueForDouble(Object obj){
		return (obj!=null &&  !obj.toString().equals("")?Double.parseDouble(obj.toString()):null);
	}
	public static Long checkNullValueForLong(Object obj){
		return (obj!=null &&  !obj.toString().equals("")?Long.parseLong(obj.toString()):null);
	}

	public JSONObject getDataFromTricker(String searchTicker){


		ConnectionPool c = null;
		Connection conn = null;

		//create 'dataMap' map object to make a complete FC datasource.

		JSONObject  dataMap= new JSONObject();  
		try {
			if (c == null) {
				c = ConnectionPool.getInstance();
				c.initialize();
			}
			conn = (Connection) c.getConnection();

			// Establish a connection to the database

			JSONObject json = new JSONObject();

			// Form the SQL query that returns the top 10 most populous countries
			String sql="SELECT last_updated_date,price FROM ticker where ticker_symbol= ? and rowstate<>-1 ORDER BY last_updated_date desc LIMIT 10";

			// Execute the query.
			PreparedStatement pt=conn.prepareStatement(sql);    
			pt.setString(1, searchTicker);
			ResultSet rs=pt.executeQuery();

			// The 'chartobj' map object holds the chart attributes and data.

			JSONObject chartobj = new JSONObject();

			chartobj.put("caption" , "Price variation against time");
			chartobj.put("subcaption" ,searchTicker);
			chartobj.put("xaxisname" , "Time");
			chartobj.put("yaxisname" , "Price");
			chartobj.put("paletteColors", "#33bdda, #e44a00 ,#583e78" );
                	chartobj.put("bgColor", "#ffffff"                               );
                	chartobj.put("showBorder", "0"                                  );
                	chartobj.put("showCanvasBorder", "0"                            );
                	chartobj.put("usePlotGradientColor", "0"                        );
                	chartobj.put("plotBorderAlpha", "10"                            );
                	chartobj.put("placeValuesInside", "1"                           );
                	chartobj.put("valueFontColor", "#ffffff"                        );
                	chartobj.put("showAxisLines", "1"                               );
                	chartobj.put("axisLineAlpha", "25"                              );
                	chartobj.put("divLineAlpha", "10"                               );
                	chartobj.put("alignCaptionWithCanvas", "0"                      );
                	chartobj.put("showAlternateVGridColor", "0"                     );
                	chartobj.put("captionFontSize", "14"                            );
                	chartobj.put("subcaptionFontSize", "14"                         );
                	chartobj.put("subcaptionFontBold", "0"                          );
                	chartobj.put("toolTipColor", "#ffffff"                          );
                	chartobj.put("toolTipBorderThickness", "0"                      );
                	chartobj.put("toolTipBgColor", "#000000"                        );
                	chartobj.put("toolTipBgAlpha", "80"                             );
                	chartobj.put("toolTipBorderRadius", "2"                         );
                	chartobj.put("toolTipPadding", "5"                              );
//			chartobj.put("paletteColors" , "#f8bd19,#008ee4,#33bdda,#e44a00,#6baa01,#583e78");
//			chartobj.put("bgColor" , "#fffddf");
//			chartobj.put("borderAlpha", "20");
//			chartobj.put("canvasBorderAlpha", "0");
//			chartobj.put("usePlotGradientColor", "0");
//			chartobj.put("plotBorderAlpha", "10");
//			chartobj.put("showXAxisLine", "1");
//			chartobj.put("xAxisLineColor" , "#999999");
//			chartobj.put("showValues" , "0");
//			chartobj.put("divlineColor" , "#999999");
//			chartobj.put("divLineIsDashed" , "1");
//			chartobj.put("showAlternateHGridColor" , "0");

			// Push the data into the array using map object.
			JSONArray arrData = new JSONArray();
			JSONArray arrDataReg = new JSONArray();
			JSONArray arrDataPrice = new JSONArray();
			Float lPrice=null;
			while(rs.next())
			{

				JSONObject lv = new JSONObject();
				JSONObject lvreg = new JSONObject();
				JSONObject lvprice = new JSONObject();
				String time = new SimpleDateFormat("MMM/dd/yyyy HH:mm").format(rs.getTimestamp("last_updated_date"));
				if(lPrice!=null){
					
						lvreg.put("value", (rs.getFloat("price")-lPrice)/100);
						
					
				} else {
					lvreg.put("value",0);
				}
				lv.put("label",time);
				lvprice.put("value", rs.getFloat("price"));
				arrData.add(lv);    
				arrDataReg.add(lvreg);    
				arrDataPrice.add(lvprice);    
				lPrice = rs.getFloat("price");
			}

			rs.close();

			String label = new String("[{\"category\":"+arrData.toJSONString()+"}]");
			dataMap.put("chart", chartobj);
			dataMap.put("categories", JSONValue.parse(label));
			String reg = new String("[{\"seriesname\":\"Regression-Progression\",\"data\":"+arrDataReg.toJSONString()+"},{\"seriesname\":\"Price\",\"data\":"+arrDataPrice.toJSONString()+"}]");
			dataMap.put("dataset", JSONValue.parse(reg));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataMap;
	}
	
	public JSONArray getComapnyDataFromTable(String company){
 
		ConnectionPool c = null;
		Connection conn = null;


		JSONArray arrData = new JSONArray();
		try {
			if (c == null) {
				c = ConnectionPool.getInstance();
				c.initialize();
			}
			conn = (Connection) c.getConnection();

			// Establish a connection to the database

			JSONObject json = new JSONObject();

			// Form the SQL query that returns the top 10 most populous countries
			String sql="SELECT symbol,name,market_cap,sector,industry FROM company where symbol= ? and rowstate<>-1 ";

			// Execute the query.
			PreparedStatement pt=conn.prepareStatement(sql);    
			pt.setString(1, company);
			ResultSet rs=pt.executeQuery();

			while(rs.next())
			{

				JSONObject lv = new JSONObject();
				lv.put("company", rs.getString(1));
				lv.put("name", rs.getString(2));
				lv.put("market_cap", rs.getString(3));
				lv.put("sector", rs.getString(4));
				lv.put("industry", rs.getString(5));
				arrData.add(lv);
			}

			rs.close();

	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrData;
	
	}
}
