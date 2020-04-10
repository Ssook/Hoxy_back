<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.BoardDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%

request.setCharacterEncoding("UTF-8");
String value = request.getParameter("boardTag");
	
	BoardDAO board = new BoardDAO();
	JSONArray list = board.selectBoardList(value);
	out.println(list.toString());

%>