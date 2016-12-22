<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.net.*" %>
<%@ page import="timetable.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	TimetableManager tManager = new TimetableManager(id,password);
	Vector<Timetable> tVector = tManager.getData();
	
	//서버와 클라이언트 버전이 같으면 굳이 업데이트할 필요없으므로 false
	out.print("<timeTable>");

		for(int i=0; i < tVector.size() ;i++){
			
			Timetable table = (Timetable)tVector.get(i);
			
			out. print ("<schedule>");
			
			out.print("<day>");
			out.print("<![CDATA["+table.getDay()+"]]>");
			out.print("</day>");
			
			out.print("<period>");
			out.print("<![CDATA["+table.getPeriod()+"]]>");
			out.print("</period>");

			out.print("<subject>");
			out.print("<![CDATA["+table.getSubject()+"]]>");
			out.print("</subject>");

			out.print("<place>");
			out.print("<![CDATA["+table.getPlace()+"]]>");
			out.print("</place>");

			out.print("<prof>");
			out.print("<![CDATA["+table.getProf()+"]]>");
			out.print("</prof>");

			out.print("</schedule>");
		}

	
	out.print("</timeTable>");
	
%>