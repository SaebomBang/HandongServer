<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="generalNotice.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String link = request.getParameter("link");
	String web = null;
	NoticeContentsManager ncm = new NoticeContentsManager(id,pw);
	web = ncm.getWebContents(link);

	if(link != null){
	
		out.print("<Notice>");
		out.print("<contents>");
		out.print("<![CDATA["+web+"]]>");
		out.print("</contents>");
		out.print("</Notice>");

	}
	
	else{
		
		out.print("<Notice>");
		out.print("<contents>");
		out.print(" 정확한 URL을 입력하세요.\n");
		out.print("</contents>");
		out.print("</Notice>");	
		
	}

	
%>