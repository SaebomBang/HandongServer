<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.net.*" %>
<%@ page import="operatingtime.*" %>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	// Client에서 받아오는 version 정보
	String versionC = request.getParameter("version");
	OpTimeManager opTimeManager = new OpTimeManager();
	Vector<OpTime>opTimeVec = opTimeManager.getOpTime();
	
	// Server에 있는 버전 체크
	String versionS = opTimeManager.getVersion();
	String check = "change";
	
	//서버와 클라이언트 버전이 같으면 굳이 업데이트할 필요없으므로 false
	if(versionC == null){
		out.print("<optime>");
		out.print("<version>");
		out.print(versionS);
		out.print("</version>");

		for(int i=0; i< opTimeVec.size();i++){
			OpTime tempOpTime = (OpTime)opTimeVec.get(i);
			
			out.print("<info>");
			
			out.print("<name>");
			out.print(tempOpTime.getName());
			out.print("</name>");

			out.print("<runtime>");
			out.print(tempOpTime.getRuntime());
			out.print("</runtime>");

			out.print("<note>");
			out.print(tempOpTime.getNote());
			out.print("</note>");

			out.print("</info>");
		}
		out.print("</optime>");
	}

	else{

		if (versionS.equals(versionC))
			check = "noChange";

		if(check.equals("noChange")){

			out.print("<optime>");

			out.print("<vResult>");
			out.print("noChange");
			out.print("</vResult>");

			out.print("</optime>");
		
		}

		else{
		
			out.print("<optime>");

			out.print("<vResult>");	
			out.print("change");
			out.print("</vResult>");

			out.print("</optime>");
		
		}
	}
%>