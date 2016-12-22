<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="haksik.*" %>



<%
	
	request.setCharacterEncoding("UTF-8");

	HaksikManager hsManager = new HaksikManager();
	Haksik hs = hsManager.getHaksik();

	out.print("<haksik>");

	out.print("<date>");
	out.print("<![CDATA["+hsManager.getDate()+"]]>");
	out.print("</date>");

	out.print("<kotebre>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getKotebre()[0]+"]]>");
	out.print("</menu>");
	if(hs.getKotebre()[0].equals("")||hs.getKotebre()[0].equals("휴무")){
		out.print("<price>");
		out.print("<![CDATA["+""+"]]>");
		out.print("</price>");
	}
	else{
		out.print("<price>");
		out.print("<![CDATA["+hs.getKotebre()[1]+"원"+"]]>");
		out.print("</price>");
	}
	out.print("</kotebre>");
	
	out.print("<kotelun>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getKotelun()[0]+"]]>");
	out.print("</menu>");
	
	if(hs.getKotelun()[0].equals("")||hs.getKotelun()[0].equals("휴무")){
		out.print("<price>");
		out.print("<![CDATA["+""+"]]>");
		out.print("</price>");
	}
	else{
		out.print("<price>");
		out.print("<![CDATA["+hs.getKotelun()[1]+"원"+"]]>");
		out.print("</price>");
	}
	
	out.print("</kotelun>");

	out.print("<kotedin>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getKotedin()[0]+"]]>");
	out.print("</menu>");
	
	if(hs.getKotedin()[0].equals("")||hs.getKotedin()[0].equals("휴무")){
		out.print("<price>");
		out.print("<![CDATA["+""+"]]>");
		out.print("</price>");
	}
	else{
		out.print("<price>");
		out.print("<![CDATA["+hs.getKotedin()[1]+"원"+"]]>");
		out.print("</price>");
	}
	
	out.print("</kotedin>");

	out.print("<fryfry>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getFryfry()[0]+"]]>");
	out.print("</menu>");
	out.print("<price>");
	out.print("<![CDATA["+hs.getFryfry()[1]+"]]>");
	out.print("</price>");
	out.print("</fryfry>");

	out.print("<noodle>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getNoodle()[0]+"]]>");
	out.print("</menu>");
	out.print("<price>");
	out.print("<![CDATA["+hs.getNoodle()[1]+"]]>");
	out.print("</price>");
	out.print("</noodle>");

	out.print("<hao>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getHao()[0]+"]]>");
	out.print("</menu>");
	out.print("<price>");
	out.print("<![CDATA["+hs.getHao()[1]+"]]>");
	out.print("</price>");
	out.print("</hao>");

	out.print("<grace>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getGrace()[0]+"]]>");
	out.print("</menu>");
	out.print("<price>");
	out.print("<![CDATA["+hs.getGrace()[1]+"]]>");
	out.print("</price>");
	out.print("</grace>");

	out.print("<mix>");
	out.print("<menu>");
	out.print("<![CDATA["+hs.getMix()[0]+"]]>");
	out.print("</menu>");
	out.print("<price>");
	out.print("<![CDATA["+hs.getMix()[1]+"]]>");
	out.print("</price>");
	out.print("</mix>");		
	
	out.print("</haksik>");

%>