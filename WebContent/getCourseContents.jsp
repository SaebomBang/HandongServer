<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="courseNotice.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String link = request.getParameter("link");
	
	CourseContentsManager conManager = new CourseContentsManager(id,pw);

	if(link != null){
	
		String contents = conManager.getContents(link);
		
		out.print("<cNotice>");
		out.print("<contents>");
		out.print("<![CDATA["+contents+"]]>");
		out.print("</contents>");
		out.print("</cNotice>");

	}
	
	else{
		
		out.print("<cNotice>");
		out.print("<contents>");
		out.print("올바른 link를 입력하세요.\n");
		out.print("</contents>");
		out.print("</cNotice>");	
		
	}

	
%>