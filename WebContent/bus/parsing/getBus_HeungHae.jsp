<?xml version="1.0" encoding="utf-8"?>
<%@page import="java.io.InputStream"%>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="bus.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String addr = "http://smart.handong.edu/service/index.php/main/lookup_lists/MjEzMDAzMzg=/v6";

	//기본작업
	BusParser_HeungHae parser = new BusParser_HeungHae();
	InputStream craw = parser.Crawler(addr);

	ArrayList<Bus> bus = (ArrayList<Bus>) parser
			.Parserer(craw);
	Bus b = new Bus();

	//Client's version
	String vResult = "print";
	String versionC = request.getParameter("version");

	//Server의 버전
	b = bus.get(0);
	String versionS = b.getVesion();

	if (versionC == null)
		vResult = "print";
	else if (versionC.equals(versionS))
		vResult = "noChange";
	else if (!versionC.equals(versionS))
		vResult = "change";

	if (vResult.equals("print")) {
		out.print("<HeungHaeBus>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		for (int i = 1; i < bus.size(); i++) {
			b = bus.get(i);
			out.print("<Bus>");

			out.print("<timesplit>");
			out.print("<![CDATA[" + b.getTimeSplit() + "]]>");
			out.print("</timesplit>");

			out.print("<tZone>");
			out.print("<![CDATA[" + b.getTZone() + "]]>");
			out.print("</tZone>");

			out.print("<times>");
			out.print("<![CDATA[" + b.getTimes() + "]]>");
			out.print("</times>");

			out.print("<lotari>");
			out.print("<![CDATA[" + b.getLotari() + "]]>");
			out.print("</lotari>");

			out.print("<HGU>");
			out.print("<![CDATA[" + b.getHGU() + "]]>");
			out.print("</HGU>");

			out.print("<gokgang>");
			out.print("<![CDATA[" + b.getGokgang() + "]]>");
			out.print("</gokgang>");

			out.print("<heungHae>");
			out.print("<![CDATA[" + b.getHeungHae() + "]]>");
			out.print("</heungHae>");

			out.print("</Bus>");
		}
		out.print("</HeungHaeBus>");
	} else {
		out.print("<WeekdayBus>");
		out.print("<vResult>");
		out.print(vResult);
		out.print("</vResult>");
		out.print("</WeekdayBus>");
	}
%>