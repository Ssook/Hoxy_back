<%@page import="DAO.WasteDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.WasteDAO"%>
<%@page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%
try{
	request.setCharacterEncoding("UTF-8");
  	String json = request.getParameter("json_waste_type_info");
   
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(json);
   	JSONObject jsonObj = (JSONObject)obj;

 	WasteDAO wasteDAO = new WasteDAO();
	JSONArray list = wasteDAO.waste_type_info(jsonObj);
	out.println(list.toString());
} catch (ClassNotFoundException e) {
} catch(Exception e) {
}
%>


