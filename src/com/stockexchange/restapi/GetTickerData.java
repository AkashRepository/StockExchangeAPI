package com.stockexchange.restapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.jcs.JCS;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.stockexchange.entities.TickerBean;

/**
 * Servlet implementation class GetTickerData
 */
public class GetTickerData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTickerData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//		doPost(request,response);

		JSONArray larr = new JSONArray();
		PrintWriter lpw= response.getWriter();

		try{

			String searchTicker = request.getParameter("searchTextBox");

			if(searchTicker!=null){
				CacheDataHandler lCacheDataHandler = new CacheDataHandler();
				TickerBean lCachedTickerObj = lCacheDataHandler.getTicker(searchTicker); //Check if  ticker data in cache

				RestApiDataFetch lRestApiDataFetch =  new RestApiDataFetch();

				if(lCachedTickerObj!=null && lCachedTickerObj.getTicker()!=null &&  lCachedTickerObj.getTicker().equals(searchTicker)){
					larr = lRestApiDataFetch.convertObjectToJson(lCachedTickerObj);				// return the cached object but first convert to json object.
				} else {
					larr = RestApiDataFetch.getJSONFromApi(searchTicker);								// get it from rest api.
					lCacheDataHandler.addTicker(lRestApiDataFetch.saveRealTimeData(larr));	//save Ticker details in "ticker" table and then return to save it in Cache.
				}
				larr.add(lRestApiDataFetch.getDataFromTricker(searchTicker));						//Data from table to generate graph.
				lpw.write(larr.toJSONString().toString());
			} else {

				JSONObject obj = new JSONObject();
				obj.put("error-status", "1");
				obj.put("error-msg","Ticker value can not be none.");
				larr.add(obj);
				lpw.write(larr.toJSONString().toString());
			}
		} catch(Exception e){
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("error-status", "1");
			obj.put("error-msg","Ticker not found.");
			larr.add(obj);
			lpw.write(larr.toJSONString().toString());
		} finally{

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub

	}

}
