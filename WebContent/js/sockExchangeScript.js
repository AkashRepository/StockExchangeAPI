
function getDataOnLoad(){

	var searchTicker = $("#searchTextBox").val();

	if(searchTicker===""){
		alert('Please enter Ticker value.');
		return ;
	}
	//get ticker data on page load.
	$.ajax({
		type: 'GET',
		contentType: 'application/json',
		url: '/StockExchangeIndex/GetTickerData',
		dataType: 'json',
		data: {searchTextBox: searchTicker},
		success:function (data, textStatus, jqXHR){
			if($.isArray(data) ){
				jQuery(data).each(function(k,v){
					insertValueToDiv(k,v);
					$('#ticker').show();
				});
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			$('#ticker').show().text('Error while Fetching data from 3rd party API.');
		},
		complete: function(data){
			arr = JSON.parse(data.responseText);

			$('#graph').insertFusionCharts({
				type: 'msline',
				id: 'chart1',
				width: '800',
				height: '300',
				dataFormat: 'json',
				dataSource: arr[1]
			});
		}

	});
}
function getCompanyDetails(){
	var company = $("#company").text();
	if(company!=""){
		$.ajax({
			type: 'GET',
			contentType: 'application/json',
			url: '/StockExchangeIndex/GetCompanyData',
			dataType: 'json',
			data: {company: company},
			success:function (data, textStatus, jqXHR){
				if(data[0]["error-status"]!="1"){
					$("#com_ticker").text((data[0]['company']!=""?data[0]['company']:'----'));
					$("#com_name").text((data[0]['name']!=""?data[0]['name']:'----'));
					$("#com_market_cap").text((data[0]['market_cap']!=""?data[0]['market_cap']:'----'));
					$("#com_sector").text((data[0]['sector']!=""?data[0]['sector']:'----'));
					$("#com_industry").text((data[0]['industry']!=""?data[0]['industry']:'----'));

				} else {
					$("#com_ticker").text((data[0]['error-msg']));
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				$('#com_ticker').show().text('Something went wrong...!!!');
			}
		});
	} else {
		alert('Please enter Ticker value.');
		return ;
	}
}


function insertValueToDiv(k,v) {
	$(v).each(function(key,value){
		if(value["error-status"]!='1'){
			if(key=="0"){
				$("#company").text(value['t']);
				$("#Exchange").text(value['e']);
				$("#LastPrice").text(value['l']);
				$("#LastTradeTime").text(value['ltt']);
				$("#Price").text(value['l']);
				$("#LastTradeTimeFormatted").text(value['lt']);
				$("#LastTradeDateTime").text(value['lt_dts']);
				$("#Change").text(value['c']);
				$("#ChangePercentage").text(value['cp']);
				$("#AfterHoursLastPrice").text(value['el']);
				$("#AfterHoursLastTradeTimeFormatted").text(value['elt']);
				$("#Dividend").text(value['div']);
				$("#DividendYield").text(value['yld']);
			}
		} else {
			$("#company").text(value['error-msg']);
		}

	});

}
