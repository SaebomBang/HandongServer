<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	out.print("<contents>");
	
	out.print("<comment>");
	out.print("원룸하면 또순이 !!\n또순이하면 원룸 !!\n");
	out.print("</comment>");
	
	out.print("<phonenum>");
	out.print("010-4441-6001");
	out.print("</phonenum>");

	out.print("<url>");
	out.print("http://cafe.daum.net/cellorecord");
	out.print("</url>");
	
	out.print("</contents>");
	
%>