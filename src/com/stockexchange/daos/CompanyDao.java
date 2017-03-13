package com.stockexchange.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.stockexchange.entities.CompanyBean;
import com.stockexchange.utilities.ConnectionPool;

public class CompanyDao {

	public static final String PSTMTCOMMA = "?,";
	
    ConnectionPool c = null;
    Connection conn = null;

    public void create(CompanyBean pCompanyBean) {
        try {
            if (c == null) {
                c = ConnectionPool.getInstance();
                c.initialize();
            }
            conn = (Connection) c.getConnection();
            StringBuilder sql = new StringBuilder("insert into company (symbol,name,market_cap,sector,industry,logged_date,last_updated_date,rowstate)  values(");
            if(pCompanyBean.getSector()!=null){
            	sql.append(PSTMTCOMMA);
            }
            if(pCompanyBean.getName()!=null){
            	sql.append(PSTMTCOMMA);
            }
            if(pCompanyBean.getMarket_cap()!=null){
            	sql.append(PSTMTCOMMA);
            }
            if(pCompanyBean.getSector()!=null){
            	sql.append(PSTMTCOMMA);
            }
            if(pCompanyBean.getIndustry()!=null){
            	sql.append(PSTMTCOMMA);
            }
            sql.append("now(),now(),1) ");
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
            if(pCompanyBean.getSector()!=null){
            	pstmt.setString(1, pCompanyBean.getSymbol());
            }
            if(pCompanyBean.getName()!=null){
            	pstmt.setString(2, pCompanyBean.getName());
            }
            if(pCompanyBean.getMarket_cap()!=null){
            	pstmt.setDouble(3, pCompanyBean.getMarket_cap());
            }
            if(pCompanyBean.getSector()!=null){
            	pstmt.setString(4, pCompanyBean.getSector());
            }
            if(pCompanyBean.getIndustry()!=null){
            	pstmt.setString(5, pCompanyBean.getIndustry());
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
//
//    public ArrayList<BookingRooms> findAll() {
//        ArrayList<BookingRooms> al = new ArrayList<BookingRooms>();
//        try {
//            if (c == null) {
//                c = ConnectionPool.getInstance();
//                c.initialize();
//            }
//            conn = (Connection) c.getConnection();
//            String sql = "select bookingid,roomno from BookingRooms";
//            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            ResultSet rs = (ResultSet) pstmt.executeQuery();
//            while (rs.next()) {
//                BookingRooms brBean = new BookingRooms();
//                brBean.setBookingId(rs.getInt("bookingid"));
//                brBean.setRoomNo(rs.getInt("roomno"));
//                al.add(brBean);
//            }
//            c.putConnection(conn);
//        } catch (Exception e) {
//            System.out.println("Exception " + e);
//        } finally {
//            c.putConnection(conn);
//
//        }
//        return al;
//    }
//
//    public ArrayList<Integer> findAll(int bookingid) {
//        ArrayList<Integer> al = new ArrayList<Integer>();
//        try {
//            if (c == null) {
//                c = ConnectionPool.getInstance();
//                c.initialize();
//            }
//            conn = (Connection) c.getConnection();
//            String sql = "select roomno from BookingRooms where bookingid=" + bookingid;
//            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            ResultSet rs = (ResultSet) pstmt.executeQuery();
//            while (rs.next()) {
//                al.add(rs.getInt("roomno"));
//            }
//            c.putConnection(conn);
//        } catch (Exception e) {
//            System.out.println("Exception " + e);
//        } finally {
//            c.putConnection(conn);
//
//        }
//        return al;
//    }
//
//    public ArrayList<Integer> findAllBookings(int bookingid) {
//        ArrayList<Integer> al = new ArrayList<Integer>();
//        try {
//            if (c == null) {
//                c = ConnectionPool.getInstance();
//                c.initialize();
//            }
//            conn = (Connection) c.getConnection();
//            String sql = "select id from BookingRooms where bookingid=" + bookingid;
//            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            ResultSet rs = (ResultSet) pstmt.executeQuery();
//            while (rs.next()) {
//                BookingRooms brBean = new BookingRooms();
//                al.add(rs.getInt("id"));
//            }
//            c.putConnection(conn);
//        } catch (Exception e) {
//            System.out.println("Exception " + e);
//        } finally {
//            c.putConnection(conn);
//
//        }
//        return al;
//    }
//
//    public ArrayList<Integer> findAllBookedRooms(int roomno) {
//        ArrayList<Integer> al = new ArrayList<Integer>();
//        try {
//            if (c == null) {
//                c = ConnectionPool.getInstance();
//                c.initialize();
//            }
//            conn = (Connection) c.getConnection();
//            String sql = "select bookingid from BookingRooms where roomno=" + roomno;
//            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            ResultSet rs = (ResultSet) pstmt.executeQuery();
//            while (rs.next()) {
//                BookingRooms brBean = new BookingRooms();
//                al.add(rs.getInt("bookingid"));
//            }
//            c.putConnection(conn);
//        } catch (Exception e) {
//            System.out.println("Exception " + e);
//        } finally {
//            c.putConnection(conn);
//
//        }
//        return al;
//    }
//
//    public void remove(int id) {
//        try {
//            if (c == null) {
//                c = ConnectionPool.getInstance();
//                c.initialize();
//            }
//            conn = (Connection) c.getConnection();
//            String sql = "delete from BookingRooms where id=?";
//            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            pstmt.setInt(1, id);
//            pstmt.executeUpdate();
//            c.putConnection(conn);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//
//            c.putConnection(conn);
//
//
//        }
//    }
	
}





