<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="phonebook.*" %>

<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	PhoneBookManager pbManager = new PhoneBookManager();
	Vector<PhoneBook> pbVector = pbManager.getPhoneBook();
	String ver = request.getParameter("version");
	String serverVer = pbManager.getVersion();
	String check = "change";


	if(ver == null){

		out.print("<PhoneBook>");

		out.print("<version>");
		out.print(serverVer);
		out.print("</version>");
		
		for(int i = 0 ; i < pbVector.size() ; i++){

			PhoneBook pb = (PhoneBook)pbVector.get(i);
			
			out.print("<phoneList>");

			out.print("<name>");
			out.print(pb.getNameKor());
			out.print("</name>");
		
			out.print("<phone>");
			out.print("054-260-"+pb.getNumber());
			out.print("</phone>");

			out.print("<category>");
			out.print(pb.getCat());
			out.print("</category>");

			out.print("</phoneList>");
			
		}
		out.print("</PhoneBook>");

	}

	else{

		if (serverVer.equals(ver))
			check = "noChange";

		if(check.equals("noChange")){

			out.print("<PhoneBook>");

			out.print("<vResult>");
			out.print("noChange");
			out.print("</vResult>");

			out.print("</PhoneBook>");
		
		}

		else{
		
			out.print("<PhoneBook>");

			out.print("<vResult>");	
			out.print("change");
			out.print("</vResult>");

			out.print("</PhoneBook>");
		
		}
	}
%>



		