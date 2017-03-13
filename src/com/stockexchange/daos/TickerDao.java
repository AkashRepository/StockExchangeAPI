package com.stockexchange.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import com.stockexchange.entities.TickerBean;
import com.stockexchange.utilities.ConnectionPool;

public class TickerDao {


	public static final String PSTMTCOMMA = "?,";
	
    ConnectionPool c = null;
    Connection conn = null;

    public void create(TickerBean  pTickerBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            StringBuilder sql = new StringBuilder("insert into ticker (id,ticker_symbol,exchange,last_price,last_trade_time,price,last_trade_time_formatted,last_trade_date_time,change_price,change_percentage,after_hours_last_price,divident,divident_yield,after_hours_last_trade_time_formatted,logged_date,last_updated_date,rowstate) values (");
        	
            for(int i=0;i<14;i++){
            	sql.append(PSTMTCOMMA);
            }
            
            
////            if(pTickerBean.getId()!=null){
//            	sql.append(PSTMTCOMMA);
////            }
//            if(pTickerBean.getTicker()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getExchange()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getLast_price()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getPrice()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getLast_trade_time()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getLast_trade_time_formatted()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getChange()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getChange_percentage()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getDivident()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getDivident_yield()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getAfter_hours_last_price()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
//            if(pTickerBean.getAfter_hours_last_trade_time_formatted()!=null){
//            	sql.append(PSTMTCOMMA);
//            }
            
            sql.append("now(),now(),1) ");
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
        	
            if(pTickerBean.getId()!=null){
            	pstmt.setLong(1, pTickerBean.getId());
            } else {
            	pstmt.setNull(1, Types.BIGINT);
            }
            if(pTickerBean.getTicker()!=null){
            	pstmt.setString(2, pTickerBean.getTicker());
            }else {
            	pstmt.setNull(2, Types.VARCHAR);
            }
            if(pTickerBean.getExchange()!=null){
            	pstmt.setString(3, pTickerBean.getExchange());
            }else {
            	pstmt.setNull(3, Types.VARCHAR);
            }
            if(pTickerBean.getLast_price()!=null){
            	pstmt.setDouble(4, pTickerBean.getLast_price());
            }else {
            	pstmt.setNull(4, Types.FLOAT);
            }
            if(pTickerBean.getLast_trade_time()!=null){
            	pstmt.setString(5, pTickerBean.getLast_trade_time());
            }else {
            	pstmt.setNull(5, Types.VARCHAR);
            }
            if(pTickerBean.getPrice()!=null){
            	pstmt.setDouble(6, pTickerBean.getPrice());
            }else {
            	pstmt.setNull(6, Types.FLOAT);
            }
            
            if(pTickerBean.getLast_trade_time_formatted()!=null){
            	pstmt.setString(7, pTickerBean.getLast_trade_time_formatted());
            }else {
            	pstmt.setNull(7, Types.VARCHAR);
            }
            if(pTickerBean.getLast_trade_date_time()!=null){
            	pstmt.setString(8, pTickerBean.getLast_trade_date_time());
            }else {
            	pstmt.setNull(8, Types.FLOAT);
            }
            if(pTickerBean.getChange()!=null){
            	pstmt.setDouble(9, pTickerBean.getChange());
            }else {
            	pstmt.setNull(9, Types.FLOAT);
            }
            if(pTickerBean.getChange_percentage()!=null){
            	pstmt.setDouble(10, pTickerBean.getChange_percentage());
            }else {
            	pstmt.setNull(10, Types.FLOAT);
            }
            if(pTickerBean.getAfter_hours_last_price()!=null){
            	pstmt.setDouble(11, pTickerBean.getAfter_hours_last_price());
            }else {
            	pstmt.setNull(11, Types.FLOAT);
            }
            if(pTickerBean.getDivident()!=null){
            	pstmt.setDouble(12, pTickerBean.getDivident());
            }else {
            	pstmt.setNull(12, Types.FLOAT);
            }
            if(pTickerBean.getDivident_yield()!=null){
            	pstmt.setDouble(13, pTickerBean.getDivident_yield());
            }else {
            	pstmt.setNull(13, Types.FLOAT);
            }
            
            if(pTickerBean.getAfter_hours_last_trade_time_formatted()!=null){
            	pstmt.setString(14, pTickerBean.getAfter_hours_last_trade_time_formatted());
            }else {
            	pstmt.setNull(14, Types.VARCHAR);
            }
           
            pstmt.executeUpdate();
            c.putConnection(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	try{
        		c.putConnection(conn);
        	} catch(Exception e){
        		e.printStackTrace();
        	}

        }
    }
	
}
