<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="Delivery_2015.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");

	//Client에서 받아올 버전
	String vResult;
	String versionC = request.getParameter("version");
	
	//Server의 버전
	DeliveryManager_2015 deliverymanager = new DeliveryManager_2015();
	DeliveryVersionCheck_2015 deliveryVersionChecker = new DeliveryVersionCheck_2015();
	String versionS = deliveryVersionChecker.getVersionByDB();

	//기본작업
	Vector<delivery_2015> testVec = new Vector<delivery_2015>();
	testVec = deliverymanager.getAllDelivery();
	
	//버전비교
	if(versionC == null)
		vResult = "print";
	else if (versionS.equals(versionC))
		vResult = "noChange";
	else
		vResult = "change";

	//client의 버전이 null이면 모든 소스를 print
	if (vResult.equals("print")) {
		out.print("<delivery>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");
		
		for (int i = 0; i < testVec.size(); i++) {
			delivery_2015 tempTest = (delivery_2015) testVec.get(i);

			out.print("<information>");

			out.print("<id>");
			out.print(tempTest.getId());
			out.print("</id>");

			out.print("<name>");
			out.print(tempTest.getName());
			out.print("</name>");

			out.print("<phone>");
			out.print(tempTest.getPhone());
			out.print("</phone>");

			out.print("<runTime>");
			out.print(tempTest.getRunTime());
			out.print("</runTime>");

			out.print("<category>");
			out.print(tempTest.getCategory());
			out.print("</category>");

			out.print("</information>");
		}
		out.print("</delivery>");
	}
	//그렇지 않으면 change/noChange만 보냄
	else{
		out.print("<delivery>");

		out.print("<vResult>");
		out.print("<![CDATA[" + vResult + "]]>");
		out.print("</vResult>");
		
		out.print("</delivery>");
	}
		
%>