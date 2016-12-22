<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="yasickMenu_2015.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	String storeName = "burgerhills";

	//Client에서 받아올 버전
	String vResult;
	String versionC = request.getParameter("version");

	//Server의 버전
	YasickMenuList manager = new YasickMenuList();
	String versionS = manager.getVersionByDB(storeName);

	//기본작업
	Vector<yasickMenu_2015> testVec = new Vector<yasickMenu_2015>();
	testVec = manager.getAllYasickMenu(storeName);

	//버전비교
	if (versionC == null)
		vResult = "print";
	else if (versionS.equals(versionC))
		vResult = "noChange";
	else
		vResult = "change";

	//client의 버전이 null이면 모든 소스를 print
	if (vResult.equals("print")) {
		out.print("<yasick>");

		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		for (int i = 0; i < testVec.size(); i++) {
			yasickMenu_2015 tempTest = (yasickMenu_2015) testVec.get(i);
			if (i == 0) {
				out.print("<storeInfo>");
				out.print("<holiday>");
				out.print(tempTest.getHoliday());
				out.print("</holiday>");

				out.print("<runTime>");
				out.print(tempTest.getRunTime());
				out.print("</runTime>");

				out.print("<special>");
				out.print(tempTest.getSpecial());
				out.print("</special>");

				out.print("</storeInfo>");
			}
			if (i >= 0 && i <= 3) {
				out.print("<mainMenu>");

				out.print("<photo>");
				out.print("https://hgughost.com/HandongServer/yasick/img/" +storeName+ "/"
						+ URLEncoder.encode(tempTest.getName(), "utf-8")
						+ ".JPG");
				out.print("</photo>");

				out.print("<name>");
				out.print(tempTest.getName());
				out.print("</name>");

				out.print("<price>");
				out.print(tempTest.getPrice());
				out.print("</price>");

				out.print("</mainMenu>");
			} else {
				out.print("<allMenu>");

				out.print("<name>");
				out.print(tempTest.getName());
				out.print("</name>");

				out.print("<price>");
				out.print(tempTest.getPrice());
				out.print("</price>");

				out.print("<set>");
				out.print(tempTest.getSets());
				out.print("</set>");
				out.print("</allMenu>");
			}
		}
		out.print("<catalog>");
		out.print("https://hgughost.com/HandongServer/yasick/img/" +storeName+ "/"
				+ URLEncoder.encode("전단지", "utf-8") + ".JPG");
		out.print("</catalog>");
		out.print("</yasick>");
	}
	//그렇지 않으면 change/noChange만 보냄
	else {
		out.print("<yasick>");

		out.print("<vResult>");
		out.print(vResult);
		out.print("</vResult>");

		out.print("</yasick>");
	}
%>