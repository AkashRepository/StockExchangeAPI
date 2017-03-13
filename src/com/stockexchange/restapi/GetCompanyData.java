package com.stockexchange.restapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.stockexchange.entities.TickerBean;

/**
 * Servlet implementation class getCompanyData
 */
@WebServlet("/getCompanyData")
public class GetCompanyData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCompanyData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			String company = request.getParameter("company");
			JSONArray larr =  new  JSONArray();

			PrintWriter lpw= response.getWriter();
			try{
			if(company!=null){

				RestApiDataFetch lRestApiDataFetch =  new RestApiDataFetch();
				larr = lRestApiDataFetch.getComapnyDataFromTable(company);

				lpw.write(larr.toJSONString().toString());
			} else {

				JSONObject obj = new JSONObject();
				obj.put("error-status", "1");
				obj.put("error-msg","Company value can not be none.");
				larr.add(obj);
				lpw.write(larr.toJSONString().toString());
			}
		} catch(Exception e){
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("error-status", "1");
			obj.put("error-msg","Something went wrong.");
			larr.add(obj);
			lpw.write(larr.toJSONString().toString());
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
