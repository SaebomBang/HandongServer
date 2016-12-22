<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%
	// apk의 버전과 업데이트 된 버전을 확인
	request.setCharacterEncoding("UTF-8");

	String ver = request.getParameter("version");
	String serverVer = "6.1.4";
	String check = "noChange";


	if(!ver.equals(serverVer)){
		check = "change";

		out.print("<version>");
		out.print(check);
		out.print("</version>");

	}

	else{

		out.print("<version>");
		out.print(check);
		out.print("</version>");
	
	}
%>

