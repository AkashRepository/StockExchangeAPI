package com.stockexchange.entities;

import java.sql.Timestamp;

public class CompanyBean {

	//Data variables
	
	Long company_id;
	String symbol;
	String name;
	Double market_cap;
	String sector;
	String industry;
	Timestamp logged_date;
	Timestamp last_updated_date;
	Long rowstate;

	//getters and setters
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMarket_cap() {
		return market_cap;
	}
	public void setMarket_cap(Double market_cap) {
		this.market_cap = market_cap;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public Timestamp getLogged_date() {
		return logged_date;
	}
	public void setLogged_date(Timestamp logged_date) {
		this.logged_date = logged_date;
	}
	public Timestamp getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(Timestamp last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	public Long getRowstate() {
		return rowstate;
	}
	public void setRowstate(Long rowstate) {
		this.rowstate = rowstate;
	}
	

	
	

	
	
	
}
