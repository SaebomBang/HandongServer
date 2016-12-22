<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.net.*" %>
<%@ page import="hyoam.*" %>

<%
	//한글 ENCODING을 위한 설정
	request.setCharacterEncoding("UTF-8");
	// Client에서 받아오는 version 정보
	HyoamManager hManager = new HyoamManager();
	List<Vector<Hyoam>> weekHyoam = hManager.getHyoam();
	
	try{
	out.print("<hyoam>");
	
	for(int i =0 ; i <weekHyoam.size(); i++){
		Vector<Hyoam> hVector = weekHyoam.get(i);
		
		out.print("<menu>");
	//서버와 클라이언트 버전이 같으면 굳이 업데이트할 필요없으므로 false
		out.print("<date>");
		out.print("<![CDATA["+hVector.get(0).getDate()+"]]>");
		out.print("</date>");
		
		out.print("<special>");
			
		out.print("<unit>");
		
		//</b>제거 작업
		out.print("<name>");
		if(hVector.get(0).getMenu().contains("<b>"))
			out.print("<![CDATA["+hVector.get(0).getMenu().split("<b>")[0]+"]]>");
		else
			out.print("<![CDATA["+hVector.get(0).getMenu()+"]]>");
		out.print("</name>");
		
		/*
		//영광오빠 코드
		out.print("<name>");
		out.print("<![CDATA["+hVector.get(0).getMenu()+"]]>");
		out.print("</name>");
		*/
		out.print("<price>");
		out.print("<![CDATA["+hVector.get(0).getPrice()+"]]>");
		out.print("</price>");
		
		out.print("</unit>");

		out.print("</special>");
		
		out.print("<normal>");
		
		for (int j =1 ; j <11; j++){		
			
			out.print("<unit>");
			
			out.print("<name>");
			out.print("<![CDATA["+hVector.get(j).getMenu()+"]]>");
			out.print("</name>");

			out.print("<price>");
			out.print("<![CDATA["+hVector.get(j).getPrice()+"]]>");
			out.println("</price>");
			
			out.print("</unit>");
			
		}
		
		out.print("</normal>");
		out.print("</menu>");
		
	}
	
	out.print("</hyoam>");
	
	}catch(IndexOutOfBoundsException e){
		System.out.println(e.toString() + "에러 발생");
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
%>