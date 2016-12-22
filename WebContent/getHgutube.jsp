<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="hgutube.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	HgutubeManager hManager = new HgutubeManager();
	Vector<Hgutube> hVector = hManager.getHgutube();
	String ver = request.getParameter("version");
	String serverVer = hManager.getVersion();
	String check = "change";


	if(ver == null){

		out.print("<hgutube>");

		out.print("<version>");
		out.print(serverVer);
		out.print("</version>");
		
		for(int i = 0 ; i < hVector.size() ; i++){

			Hgutube h = (Hgutube)hVector.get(i);
			
			out.print("<hgutubeList>");

			out.print("<category>");
			out.print("<![CDATA["+h.getCat()+"]]>");
			out.print("</category>");
		
			out.print("<url>");
			out.print("<![CDATA["+h.getUrl()+"]]>");
			out.print("</url>");

			out.print("<title>");
			out.print("<![CDATA["+h.getTitle()+"]]>");
			out.print("</title>");

			out.print("<writer>");
			out.print("<![CDATA["+h.getWriter()+"]]>");
			out.print("</writer>");
			
			out.print("<time>");
			out.print("<![CDATA["+h.getTime()+"]]>");
			out.print("</time>");

			out.print("</hgutubeList>");
			
		}
		
		out.print("</hgutube>");

	}

	else{

		if (serverVer.equals(ver))
			check = "noChange";

		if(check.equals("noChange")){

			out.print("<hgutube>");

			out.print("<vResult>");
			out.print("noChange");
			out.print("</vResult>");

			out.print("</hgutube>");
		
		}

		else{
		
			out.print("<hgutube>");

			out.print("<vResult>");	
			out.print("change");
			out.print("</vResult>");

			out.print("</hgutube>");
		
		}
	}
%>