<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="hguShop.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	HguShopManager hsManager = new HguShopManager();
	Vector<HguShop> hsVector = hsManager.getHguShop();
	String ver = request.getParameter("version");
	String serverVer = hsManager.getVersion();
	String check = "change";


	if(ver == null){

		out.print("<hguShop>");

		out.print("<version>");
		out.print(serverVer);
		out.print("</version>");
		
		for(int i = 0 ; i < hsVector.size() ; i++){

			HguShop hs = (HguShop)hsVector.get(i);
			
			out.print("<hguShopList>");

			out.print("<location>");
			out.print(hs.getLocation());
			out.print("</location>");
		
			out.print("<category>");
			out.print(hs.getCat());
			out.print("</category>");

			out.print("<name>");
			out.print(hs.getName());
			out.print("</name>");

			out.print("<contents>");
			out.print(hs.getContents());
			out.print("</contents>");
			
			out.print("<coordinate>");
			out.print(hs.getCoordinate());
			out.print("</coordinate>");
			
			out.print("<url>");
			out.print(hs.getUrl());
			out.print("</url>");
			
			out.print("<phone>");
			out.print(hs.getPhone());
			out.print("</phone>");

			out.print("</hguShopList>");
			
		}
		out.print("</hguShop>");

	}

	else{

		if (serverVer.equals(ver))
			check = "noChange";

		if(check.equals("noChange")){

			out.print("<hguShop>");

			out.print("<vResult>");
			out.print("noChange");
			out.print("</vResult>");

			out.print("</hguShop>");
		
		}

		else{
		
			out.print("<hguShop>");

			out.print("<vResult>");	
			out.print("change");
			out.print("</vResult>");

			out.print("</hguShop>");
		
		}
	}
%>