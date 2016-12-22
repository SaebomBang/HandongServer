<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");
	String ver = request.getParameter("version");
	String serverVer = "1511";
	String check = "change";


	if(ver == null){

		out.print("<Busimage>");

		out.print("<version>");
		out.print(serverVer);
		out.print("</version>");
		
		out.print("<name2>");
		out.print("imgbus/한독 세차장.jpg");
		out.print("</name2>");

		out.print("<name3>");
		out.print("imgbus/하나로 클럽.jpg");
		out.print("</name3>");

		out.print("<name4>");
		out.print("imgbus/e1 맞은편.jpg");
		out.print("</name4>");

		out.print("<name5>");
		out.print("imgbus/장흥초 맞은편.jpg");
		out.print("</name5>");

		out.print("<name6>");
		out.print("imgbus/바로크 가구점 맞은편.jpg");
		out.print("</name6>");

		out.print("<name7>");
		out.print("imgbus/주민센터 맞은편.jpg");
		out.print("</name7>");

		out.print("<name8>");
		out.print("imgbus/해맞이 그린빌.jpg");
		out.print("</name8>");

		out.print("<name9>");
		out.print("imgbus/명지탕 맞은편.jpg");
		out.print("</name9>");

		out.print("<name10>");
		out.print("imgbus/두호동 농협.jpg");
		out.print("</name10>");

		out.print("<name11>");
		out.print("imgbus/항구 우체국.jpg");
		out.print("</name11>");

		out.print("<name12>");
		out.print("imgbus/광유주유소.jpg");
		out.print("</name12>");

		out.print("<name13>");
		out.print("imgbus/기쁨의 교회.jpg");
		out.print("</name13>");

		out.print("<name14>");
		out.print("imgbus/육거리.jpg");
		out.print("</name14>");

		out.print("</Busimage>");

	}

	else{

		if (serverVer.equals(ver))
			check = "noChange";

		if(check.equals("noChange")){

			out.print("<Busimage>");

			out.print("<vResult>");
			out.print("noChange");
			out.print("</vResult>");

			out.print("</Busimage>");
		
		}

		else{
		
			out.print("<Busimage>");

			out.print("<vResult>");	
			out.print("change");
			out.print("</vResult>");

			out.print("</Busimage>");
		
		}
	}
%>