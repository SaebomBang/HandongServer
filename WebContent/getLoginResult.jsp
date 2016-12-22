<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="login.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	boolean result ;

	Login lg = new Login();
	result = lg.isLoginAvailable(id,pw);

	if(result){
		out.print("<result>");
		out.print("true");
		out.print("</result>");
	}
	
	else{
		out.print("<result>");
		out.print("false");
		out.print("</result>");
	}

	
%>