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
	String addr = "http://smart.handong.edu/service/index.php/main/lookup_lists/MjEzMDAzMzg=/v5/";

	//기본작업
	BusParser_toSchool parser = new BusParser_toSchool();
	InputStream craw = parser.Crawler(addr);

	ArrayList<Bus> bus = (ArrayList<Bus>) parser.Parserer(craw);
	Bus b = new Bus();

	//Client's version
	String vResult = "print";
	String versionC = request.getParameter("version");

	//Server의 버전
	b = bus.get(0);
	String versionS = "7";
	String sizeS = b.getSiz();

	if (versionC == null)
		vResult = "print";
	else if (versionC.equals(versionS))
		vResult = "noChange";
	else if (!versionC.equals(versionS))
		vResult = "change";

	if (vResult.equals("print")) {
		out.print("<WeekendBus>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		int beforeInt = 0;
		for (int i = 1; i < bus.size(); i++) {
			b = bus.get(i);
			int afterInt = Integer.parseInt(b.getTZone());
			if(beforeInt != 12){
				if(beforeInt > afterInt){
					afterInt = beforeInt;
				}
			}
			if (i == 1) {
				out.print("<tZone tzone='" + b.getTZone() + "'>");

				out.print("<timesplit>");
				out.print("<![CDATA[" + b.getTimeSplit() + "]]>");
				out.print("</timesplit>");

				out.print("<tzone>");
				out.print("<![CDATA[" + b.getTZone() + "]]>");
				out.print("</tzone>");
			}

			else if (beforeInt == afterInt) {
			}

			else {
				out.print("</tZone>");
				out.print("<tZone tzone='" + b.getTZone() + "'>");

				out.print("<timesplit>");
				out.print("<![CDATA[" + b.getTimeSplit() + "]]>");
				out.print("</timesplit>");

				out.print("<tzone>");
				out.print("<![CDATA[" + b.getTZone() + "]]>");
				out.print("</tzone>");
			}

			beforeInt = afterInt;
			out.print("<Bus>");

			out.print("<six>");
			out.print("<![CDATA[" + b.getSix() + "]]>");
			out.print("</six>");

			out.print("<hwan>");
			out.print("<![CDATA[" + b.getHwan() + "]]>");
			out.print("</hwan>");

			out.print("<school>");
			out.print("<![CDATA[" + b.getSchool() + "]]>");
			out.print("</school>");

			out.print("</Bus>");

			if (i == bus.size() - 1) {
				out.print("</tZone>");
			}
		}
		out.print("</WeekendBus>");
	} else {
		out.print("<WeekendBus>");
		out.print("<vResult>");
		out.print(vResult);
		out.print("</vResult>");
		out.print("</WeekendBus>");
	}
%>