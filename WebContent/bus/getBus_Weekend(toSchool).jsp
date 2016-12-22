<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="tempBus.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");

	//Client에서 받아올 버전
	String vResult;
	String versionC = request.getParameter("version");

	//Server의 버전
	joomalToSchool_VersionCheck BusVersion = new joomalToSchool_VersionCheck();
	String versionS = BusVersion.getVersionByDB();

	//기본작업
	joomalToSchool_BusManager busManager = new joomalToSchool_BusManager();
	Vector<tempBus> testVec = new Vector<tempBus>();
	testVec = busManager.getAllBus();

	//버전비교
	if (versionC == null)
		vResult = "print";
	else if (versionS.equals(versionC))
		vResult = "noChange";
	else
		vResult = "change";

	//client의 버전이 null이면 모든 소스를 print
	if (vResult.equals("print")) {
		out.print("<WeekendBus>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		for (int i = 0; i < testVec.size(); i++) {
			tempBus tempTest = (tempBus) testVec.get(i);
			if(i != 0 && !tempTest.getTzone().equals(" "))
				out.print("</tZone>");
			if (!tempTest.getTzone().equals(" ")) {
				out.print("<tZone tzone='" + tempTest.getTzone() + "'>");

				out.print("<timesplit>");
				out.print("<![CDATA[" + tempTest.getAmpm() + "]]>");
				out.print("</timesplit>");
				out.print("<tzone>");
				out.print("<![CDATA[" + tempTest.getTzone() + "]]>");
				out.print("</tzone>");
			}
			out.print("<Bus>");

			out.print("<six>");
			out.print("<![CDATA[" + tempTest.getSix() + "]]>");
			out.print("</six>");
			out.print("<hwan>");
			out.print("<![CDATA[" + tempTest.getHwan() + "]]>");
			out.print("</hwan>");
			out.print("<school>");
			out.print("<![CDATA[" + tempTest.getSchool() + "]]>");
			out.print("</school>");

			out.print("</Bus>");
		}
		out.print("</tZone>");
		out.print("</WeekendBus>");
	}
	//그렇지 않으면 change/noChange만 보냄
	else {
		out.print("<WeekendBus>");

		out.print("<vResult>");
		out.print(vResult);
		out.print("</vResult>");

		out.print("</WeekendBus>");
	}
%>