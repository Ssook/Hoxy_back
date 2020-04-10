package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Util.DatabaseUtil;

public class WasteDAO {

	@SuppressWarnings("unchecked")
	public JSONArray waste_type_info(JSONObject request_waste_info) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt;
		
		JSONArray jsonArray = new JSONArray();

		String waste_type_area_no = (request_waste_info.get("waste_type_area_no")).toString();
		String waste_type_name = (request_waste_info.get("waste_type_name")).toString();
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select * from waste_type where waste_type_area_no = ? and waste_type_name = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(waste_type_area_no));
			pstmt.setString(2, waste_type_name);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("waste_type_no", rs.getString("waste_type_no"));
				jsonObject.put("waste_type_size", rs.getString("waste_type_size"));
				jsonObject.put("waste_type_fee", rs.getString("waste_type_fee"));
				jsonArray.add(jsonObject);
				jsonObject = null;
			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		}
		return jsonArray;
	}
	
	public String insertApplyInfo(JSONObject apply_info) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO apply_info(apply_info_name, apply_info_address, apply_info_phone, apply_info_waste_type_no, apply_info_fee, apply_info_code, apply_info_user_no)"
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, (apply_info.get("apply_info_name")).toString());
			pstmt.setString(2, (apply_info.get("apply_info_address")).toString());
			pstmt.setString(3, (apply_info.get("apply_info_phone")).toString());
			pstmt.setString(4, (apply_info.get("apply_info_waste_type_no")).toString());
			pstmt.setString(5, (apply_info.get("apply_info_fee")).toString());
			pstmt.setString(6, (apply_info.get("apply_info_code")).toString());
			pstmt.setString(7, (apply_info.get("apply_info_user_no")).toString());
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			DatabaseUtil.data_close(pstmt, conn);
		}
		return rst;
	}
}
