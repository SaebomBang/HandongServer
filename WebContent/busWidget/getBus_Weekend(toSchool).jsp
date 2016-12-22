<?xml version="1.0" encoding="utf-8"?>
<%@page import="java.io.InputStream"%>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="busWidget.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String addr = "http://smart.handong.edu/service/index.php/main/lookup_lists/MjEzMDAzMzg=/v4/";

	//기본작업
	BusWidget_toSix parser = new BusWidget_toSix();
	InputStream craw = parser.Crawler(addr);

	ArrayList<BusWidget> bus = (ArrayList<BusWidget>) parser.Parserer(craw);
	BusWidget b = new BusWidget();
	int count=0;
	
	if(bus.size()<3)
		count=bus.size();
	else
		count=3;
	
	out.print("<WeekdayBus>");

	for (int i = 0; i < count; i++) {
		b = bus.get(i);

		out.print("<Bus>");

		out.print("<school>");
		out.print("<![CDATA[" + b.getSchool() + "]]>");
		out.print("</school>");

		out.print("<hwan>");
		out.print("<![CDATA[" + b.getHwan() + "]]>");
		out.print("</hwan>");

		out.print("<six>");
		out.print("<![CDATA[" + b.getSix() + "]]>");
		out.print("</six>");

		out.print("</Bus>");
	}
	out.print("</WeekdayBus>");
%>