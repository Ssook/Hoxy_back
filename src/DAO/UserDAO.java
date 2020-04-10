package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Util.DatabaseUtil;

public class UserDAO {
	public String insertLogin(JSONObject login_info) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";
		if(existUser((login_info.get("user_info_no")).toString())){
			return "fail";
		}
		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO user_info(user_info_no, user_info_id)"
					+ "values (?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, (login_info.get("user_info_no")).toString());
			pstmt.setString(2, (login_info.get("user_info_id")).toString());

			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = "fail";
		} finally {
			DatabaseUtil.data_close(pstmt, conn, rs);
		}
		return rst;
	}
	
	public boolean existUser(String user_info_no) throws Exception {
		try {
			String sql = "select * from user_info WHERE user_info_no = ?";

			ResultSet rs = DatabaseUtil.executeQuery(sql, user_info_no);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		}
		return false;
	}
}
