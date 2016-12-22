<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="mainNotice.*" %>

<%
	// main 공지의 version 수정
	request.setCharacterEncoding("UTF-8");

	MainNoticeManager mnManager = new MainNoticeManager();
	MainNotice mn = mnManager.getMainNotice();

	String serverVer = mn.getDate(); // 이 곳을 수정

	out.print("<version>");
	out.print(serverVer);
	out.print("</version>");

%>