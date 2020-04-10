package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Util.DatabaseUtil;

public class BoardDAO {
	@SuppressWarnings("unchecked")
	public JSONArray selectBoardList(String boardTag) throws Exception {
		JSONArray jsonArray = new JSONArray();
		ResultSet rs = null;
		try {
			
			if(boardTag.equals("전체")) {
				String sql = "select * from board";
				rs = DatabaseUtil.executeQuery(sql);
			}
			else {
				String sql = "select * from board where board_tag = ?";
				rs = DatabaseUtil.executeQuery(sql,boardTag);	
			}
				
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("no", rs.getString("board_no"));
				jsonObject.put("title", rs.getString("board_title"));
				jsonObject.put("ctnt", rs.getString("board_ctnt"));
				jsonObject.put("tag", rs.getString("board_tag"));
				jsonObject.put("reg_date", rs.getString("board_reg_date"));
				jsonObject.put("reg_user", rs.getString("board_reg_user"));
				jsonArray.add(jsonObject);
				jsonObject = null;

			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		}
		return jsonArray;
	}
	
	public String insertBoard(JSONObject board) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

				String sql = "insert INTO board(board_title, board_ctnt, board_tag, board_reg_date, board_reg_user)"
						+ "values (?, ?, ?, NOW(), ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, (board.get("title")).toString());
				pstmt.setString(2, (board.get("ctnt")).toString());
				pstmt.setString(3, (board.get("tag")).toString());
				//pstmt.setString(4, (board.get("reg_date")).toString());
				pstmt.setString(4, (board.get("reg_user")).toString());
		
				pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			DatabaseUtil.data_close(pstmt, conn);
		}
		return rst;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray selectBoardReivew(String board_no) throws Exception {
		JSONArray jsonArray = new JSONArray();

		try {
			String sql = "select * from board_review WHERE board_review_board_no = ?";

			ResultSet rs = DatabaseUtil.executeQuery(sql, board_no);
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("review_no", rs.getString("board_review_review_no"));
				jsonObject.put("reg_user", rs.getString("board_review_reg_user"));
				jsonObject.put("reg_date", rs.getString("board_review_reg_date"));
				jsonObject.put("ctnt", rs.getString("board_review_ctnt"));
				jsonArray.add(jsonObject);
				jsonObject = null;
			}
			
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		}
		return jsonArray;
	}
	
	
	public String insertBoardReview(JSONObject boardReview) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO board_review(board_review_board_no, board_review_reg_user, board_review_reg_date, board_review_ctnt)"
					+ "values (?, ?, NOW(), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt((boardReview.get("board_area_no")).toString()));
			pstmt.setString(2, (boardReview.get("board_review_reg_user")).toString());
			pstmt.setString(3, (boardReview.get("board_review_ctnt")).toString());
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = "fail";
		} finally {
			DatabaseUtil.data_close(pstmt, conn);
		}
		return rst;
	}
}
