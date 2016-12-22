<?xml version="1.0" encoding="utf-8"?>
<%@page import="java.io.InputStream"%>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="studentNum.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String IdC = request.getParameter("id");
	String PwC = request.getParameter("password");
	
	WEBCrawlerNum wc = new WEBCrawlerNum();
	InputStream getstudentnum = wc.getStuNum(IdC, PwC);

	WEBParserNum webparser = new WEBParserNum(getstudentnum);
	ArrayList<StuNum> number = (ArrayList<StuNum>) webparser.parseStudentNum();
	StuNum s = new StuNum();

	out.println("<StudentNumber>");
		s = number.get(0);
		out.print("<![CDATA[" + s.getStuNum() + "]]>");
	
	out.println("</StudentNumber>");
%>