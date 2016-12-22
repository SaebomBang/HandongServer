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
	TimeSplit2Widget timesplit = new TimeSplit2Widget();
	
	//기본작업
	BusWidget_toSchool parser = new BusWidget_toSchool();
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

		out.print("<six>");
		out.print("<![CDATA[" + timesplit.spt(b.getSix()) + "]]>");
		out.print("</six>");

		out.print("<hwan>");
		out.print("<![CDATA[" + timesplit.spt(b.getHwan()) + "]]>");
		out.print("</hwan>");

		out.print("<school>");
		out.print("<![CDATA[" + timesplit.spt(b.getSchool()) + "]]>");
		out.print("</school>");

		out.print("</Bus>");
		
	}
	out.print("</WeekdayBus>");
%>