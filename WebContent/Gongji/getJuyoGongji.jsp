<?xml version="1.0" encoding="utf-8"?>
<%@page import="java.io.InputStream"%>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="WebParsing.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String IdC = request.getParameter("id");
	String PwC = request.getParameter("password");
	
	WEBCrawler wc = new WEBCrawler();
	InputStream getgongji = wc.getJuyoGongjiInfo(IdC, PwC);

	WEBParser webparser = new WEBParser(getgongji);
	ArrayList<gongji> gong = (ArrayList<gongji>) webparser
	.parseJuyoInfoName();
	gongji g = new gongji();
	out.println("<JuyoGongji>");
	for (int i = 0; i < gong.size(); i++) {
		g = gong.get(i);
		
		out.print("<Information>");
		
		out.print("<Title>");
		out.print("<![CDATA[" + g.getTitle() + "]]>");
		out.print("</Title>");
		
		out.print("<Num>");
		out.print("<![CDATA[" + g.getNum() + "]]>");
		out.print("</Num>");

		out.print("<URL>");
		out.print("<![CDATA[" + g.getGongjiURL() + "]]>");
		out.print("</URL>");
		
		out.print("</Information>");
	}
	out.println("</JuyoGongji>");
%>