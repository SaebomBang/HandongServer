<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="professor_2015.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");

	//Client에서 받아올 버전
	String vResult;
	String versionC = request.getParameter("version");

	//Server의 버전
	professorManager_2015 testmanager = new professorManager_2015();
	professorVersionCheck_2015 professorVersionChecker = new professorVersionCheck_2015();
	String versionS = professorVersionChecker.getVersionByDB();

	//기본작업
	Vector<professor_2015> testVec = new Vector<professor_2015>();
	testVec = testmanager.getAllProfessor();

	//버전비교
	if (versionC == null)
		vResult = "print";
	else if (versionS.equals(versionC))
		vResult = "noChange";
	else
		vResult = "change";

	//client의 버전이 null이면 모든 소스를 print
	if (vResult.equals("print")) {
		out.print("<professor>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		for (int i = 0; i < testVec.size(); i++) {
			professor_2015 tempTest = (professor_2015) testVec.get(i);

			out.print("<information>");

			out.print("<name>");
			out.print(tempTest.getName());
			out.print("</name>");

			out.print("<major>");
			out.print(tempTest.getMajor());
			out.print("</major>");

			out.print("<phone>");
			out.print(tempTest.getPhone());
			out.print("</phone>");

			out.print("<email>");
			out.print(tempTest.getEMail());
			out.print("</email>");

			out.print("<office>");
			out.print(tempTest.getOffice());
			out.print("</office>");

			out.print("</information>");
		}
		out.print("</professor>");
	}
	//그렇지 않으면 change/noChange만 보냄
	else {
		out.print("<professor>");

		out.print("<vResult>");
		out.print(vResult);
		out.print("</vResult>");

		out.print("</professor>");
	}
%>