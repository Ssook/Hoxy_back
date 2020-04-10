<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@page import="DAO.WasteDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
  	String json = request.getParameter("json_apply_info");
   
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(json);
   	JSONObject jsonObj = (JSONObject)obj;
   	
   	WasteDAO wasteDAO = new WasteDAO();
	
   	String rst = wasteDAO.insertApplyInfo(jsonObj);
    out.println(rst);	
%>

