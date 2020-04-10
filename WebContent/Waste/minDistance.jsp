<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="Util.Util"%>
<%
	Class.forName("com.mysql.jdbc.Driver");

	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("currentlocation");
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(get_param);
	JSONObject jsonObj = (JSONObject) obj;

	Util util = new Util();
	String NearSmokingArea_info = Util.getMinDistance(jsonObj.get("lat").toString(), jsonObj.get("lng").toString()).toString();
	out.println(NearSmokingArea_info);
%>
