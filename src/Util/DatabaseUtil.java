package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DBConnection;

public class DatabaseUtil {
	public static ResultSet executeQuery(String sql) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet executeQuery(String sql,String value_1) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value_1);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet executeQuery(String sql,int value_1) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, value_1);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ResultSet executeQuery(String sql,String value_1, String value_2) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value_1);
		pstmt.setString(2, value_2);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static void executeUpdate(String sql,String value_1) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value_1);
		pstmt.executeUpdate();
	}
	
	public static void executeUpdate(String sql,String value_1,String value_2) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value_1);
		pstmt.setString(2, value_2);
		pstmt.executeUpdate();
	}
	
	public static void executeUpdate(String sql, String value_1, int value_2) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value_1);
		pstmt.setInt(2, value_2);
		pstmt.executeUpdate();
	}
	
	
	public static void data_close(PreparedStatement pstmt, Connection conn, ResultSet rs) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
		if (rs != null)
			rs.close();
	}

	public static void data_close(PreparedStatement pstmt, Connection conn) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

}
