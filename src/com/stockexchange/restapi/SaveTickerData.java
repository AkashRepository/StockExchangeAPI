package com.stockexchange.restapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 * Servlet implementation class SaveTickerData
 */
public class SaveTickerData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveTickerData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String lRealTimeData = request.getParameter("saveTickerData");
		if(!lRealTimeData.equals("")||lRealTimeData!=null){
			JSONArray lRealTimeDataObj = (JSONArray) JSONValue.parse(lRealTimeData);
		
		RestApiDataFetch lRestApiDataFetch =  new RestApiDataFetch();
		lRestApiDataFetch.saveRealTimeData(lRealTimeDataObj);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
