<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.net.*" %>
<%@ page import="moms.*"%>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	
	MomsManager mManager = new MomsManager();
	Vector<Moms> mVector = mManager.getData();
	
	//서버와 클라이언트 버전이 같으면 굳이 업데이트할 필요없으므로 false
	out.print("<moms>");

		for(int i=0; i < mVector.size() ;i++){
			
			Moms mom = (Moms)mVector.get(i);
			
			out.print("<menu>");
			out.print("<date>");
			out.print("<![CDATA["+mom.getDate()+"]]>");
			out.print("</date>");

			out.print("<breakfast>");
			
			out.print("<name>");
			out.print("<![CDATA["+mom.getBreakfast()+"]]>");
			out.print("</name>");
			if(mom.getBreakfast().equals("연휴")||mom.getBreakfast().equals("")){
				out.print("<price>");
				out.print("");
				out.print("</price>");
			}
			else{
				out.print("<price>");
				out.print("3,500원");
				out.print("</price>");
			}
			out.print("</breakfast>");
			

		
			out.print("<lunNdin>");
			
			out.print("<name>");
			out.print("<![CDATA["+mom.getLunNdin()+"]]>");
			out.print("</name>");

			if(mom.getBreakfast().equals("연휴")||mom.getBreakfast().equals("")){
				out.print("<price>");
				out.print("");
				out.print("</price>");
			}
			else{
				out.print("<price>");
				out.print("3,500원");
				out.print("</price>");
			}

			out.print("</lunNdin>");
			out.print("</menu>");
		}

	
	out.print("</moms>");
	
%>