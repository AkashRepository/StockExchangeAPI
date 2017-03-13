<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/sockExchangeScript.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
 <script src="js/fusioncharts.js"></script>
 <script src="js/fusioncharts.charts.js"></script>
 <script src="js/fusioncharts.jqueryplugin.js"></script>
<title>Stock Exchange</title>
<style type="text/css">
#ticketTable tr td {
width:50%;
padding: 2.5px;
    text-align: center;
}
#CompanyTable tr td {

padding: 2.5px;
    text-align: center;
}
table {
border : 1px solid grey;
background-color:#f2f2f2;
 border-collapse: collapse;
}


tr:nth-child(even){background-color: #f2ffff}

</style>
</head>
<body style="    width: 98%;    margin: 2%;top:-2%">
	<!-- onload=getDataOnLoad();> -->

	<center><div id="searchTicker" style="    position: relative;    top: -10px;">
		
			<label>Enter Ticker name : </label> <input type="text"
				name="searchTextBox" id="searchTextBox"> <input
				type="submit" value="Go" name="submit" onclick="getDataOnLoad()">
	
	</div></center>



<div style="width:98%;height:90%;position:absolute;">

<div style=" width:29%;height:40%;float:left;padding:2%;">

<table style="width:100%;text-align: center;height: 100%;" id='ticketTable'>
	<tr><td colspan=2><b>Details of Ticker</b></td></tr>
	
	<tr><td>company</td><td><div id="company"></div></td></tr>
	<tr><td>Exchange</td><td><div id="Exchange"></div></td></tr>
	<tr><td>LastPrice</td><td><div id="LastPrice"></div></td></tr>
	<tr><td>LastTradeTime</td><td><div id="LastTradeTimeFormatted"></div></td></tr>
	<tr><td>Price</td><td><div id="Price"></div></td></tr>
	<tr><td>Change</td><td><div id="Change"></div></td></tr>
	<tr><td>ChangePercentage</td><td><div id="ChangePercentage"></div></td></tr>

<!-- 	<tr><td>AfterHoursLastPrice</td><td>	<div id="AfterHoursLastTradeTimeFormatted"></div></td></tr> -->
</table>
	
		
<!-- 	<div id="LastTradeTime"></div> -->
		
<!-- 		<div id="LastTradeDateTime"></div> -->
<!-- 		<div id="AfterHoursLastPrice"></div> -->
		
	
<!-- 		<div id="Dividend"></div> -->
<!-- 		<div id="DividendYield"></div> -->
	</div>
	<div id="#recentTicker" style="display: block; width:61%;height:34.5%;float:left;padding:0.25%;">
	<center><div style="    width: 98%;    margin: 2%;top:-2%"><label >Click here to view company details : </label><input type='button' value='companyDetails' onclick='getCompanyDetails();'></div></center>
		<table style="width:100%;text-align: center;height: 100%;" id="CompanyTable">
	<tr><td colspan=2><b>Details of Company</b></td></tr>
	
	<tr><td style="width:25%">Ticker</td><td><div id="com_ticker"></div></td></tr>
	<tr><td style="width:25%">Name</td><td><div id="com_name"></div></td></tr>
	<tr><td style="width:25%">Market Capital</td><td><div id="com_market_cap"></div></td></tr>
	<tr><td style="width:25%">Sector</td><td><div id="com_sector"></div></td></tr>
	<tr><td style="width:25%">Industry</td><td><div id="com_industry"></div></td></tr>
</table>
	</div>
	
	<center><div id="graph" style="float:left;display: block; width:94%;height:40%;	padding:2%;">	</div></center>
	<%@page import="com.stockexchange.utilities.FusionCharts" %>

	</div>
</body>
</html>