<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="assignmentNotice.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String link = request.getParameter("link");
	
	AssignmentContentsManager assignManager = new AssignmentContentsManager(id,pw);
	String contents = null;
	
	if(link != null){
	
		contents = assignManager.getContents(link);
		
		out.print("<cNotice>");
		
		out.print("<contents>");
		out.print("<![CDATA["+contents+"]]>");
		out.print("</contents>");
		
		out.print("</cNotice>");

	}
	
	else{
		
		out.print("<cNotice>");
		out.print("<contents>");
		out.print("불러오는 도중 오류가 발생했습니다.\n");
		out.print("</contents>");
		out.print("</cNotice>");	
		
	}

	
%>