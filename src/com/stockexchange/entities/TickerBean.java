package com.stockexchange.entities;


public class TickerBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long id;
	String ticker;
	String exchange;
	Double last_price;
	String last_trade_time;
	Double price;
	String last_trade_time_formatted;
	String last_trade_date_time;
	Double change;
	Double change_percentage;
	Double after_hours_last_price;
	String after_hours_last_trade_time_formatted;
	Double divident;
	Double divident_yield;
	
	public  TickerBean(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Double getLast_price() {
		return last_price;
	}
	public void setLast_price(Double last_price) {
		this.last_price = last_price;
	}
	public String getLast_trade_time() {
		return last_trade_time;
	}
	public void setLast_trade_time(String last_trade_time) {
		this.last_trade_time = last_trade_time;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLast_trade_time_formatted() {
		return last_trade_time_formatted;
	}
	public void setLast_trade_time_formatted(String last_trade_time_formatted) {
		this.last_trade_time_formatted = last_trade_time_formatted;
	}
	public String getLast_trade_date_time() {
		return last_trade_date_time;
	}
	public void setLast_trade_date_time(String last_trade_date_time) {
		this.last_trade_date_time = last_trade_date_time;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public Double getChange_percentage() {
		return change_percentage;
	}
	public void setChange_percentage(Double change_percentage) {
		this.change_percentage = change_percentage;
	}
	public Double getAfter_hours_last_price() {
		return after_hours_last_price;
	}
	public void setAfter_hours_last_price(Double after_hours_last_price) {
		this.after_hours_last_price = after_hours_last_price;
	}
	public String getAfter_hours_last_trade_time_formatted() {
		return after_hours_last_trade_time_formatted;
	}
	public void setAfter_hours_last_trade_time_formatted(
			String after_hours_last_trade_time_formatted) {
		this.after_hours_last_trade_time_formatted = after_hours_last_trade_time_formatted;
	}
	public Double getDivident() {
		return divident;
	}
	public void setDivident(Double divident) {
		this.divident = divident;
	}
	public Double getDivident_yield() {
		return divident_yield;
	}
	public void setDivident_yield(Double divident_yield) {
		this.divident_yield = divident_yield;
	}
	
	
}
