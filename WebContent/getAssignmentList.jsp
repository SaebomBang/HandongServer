<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="assignmentNotice.*" %>
<%@ page import="courseNotice.SemesterParser" %>
<%@ page import="courseNotice.SemesterCrawler" %>
<%@ page import="courseNotice.AllCourseCrawler" %>
<%@ page import="courseNotice.AllCourseParser" %>


<%
	// Phonebook의 내용을 xml형식으로 파싱해주는 jsp 파일
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String semester = request.getParameter("semester");
	String gwamok = request.getParameter("code");
	
	if(semester == null){
		SemesterParser semester_p = new SemesterParser();
		semester = semester_p.getSemester(id,pw);
	}
	
	if(gwamok == null){
	
		AllCourseCrawler allCrawler = new AllCourseCrawler(semester);
		AllCourseParser allParser = new AllCourseParser(allCrawler.getCourseInfo(id, pw));
		String[] allCourse = allParser.getAllCourse();
		
		//null exception 처리를 해주어야함 .getAllCourse 함수와 getCourseInfo에 대한.
		if(allCourse == null){
			
			out.print("<allCourse>");
			
			out.print("<semester>");
			out.print("<![CDATA["+semester.substring(5,6) +"]]>");
			out.print("</semester>");
			
			out.print("<courseList>");
			out.print("<course>");
			out.print("현 학기 수강중인 과목이 없습니다.\n");
			out.print("</course>");
			out.print("</courseList>");
			
			out.print("</allCourse>");
		}
		
		else{
			out.print("<allCourse>");
			
			out.print("<semester>");
			out.print("<![CDATA["+semester.substring(5,6) +"]]>");
			out.print("</semester>");
			out.print("<courseList>");
			for(int i=0; i < allCourse.length ; i++){
				out.print("<course>");
				out.print("<![CDATA["+allCourse[i]+"]]>");
				out.print("</course>");	
			}
			out.print("</courseList>");
			out.print("</allCourse>");
		}
		
	}
	
	else{
		
		AssignmentListManager anManager = new AssignmentListManager(id,pw);
		Vector<AssignmentList> alVector = anManager.getAssignmentList(semester,gwamok);
		
		out.print("<aNotice>");
		
		for(int i=0; i<alVector.size() ; i++){
			AssignmentList al = alVector.get(i);
			
			out.print("<article>");
			
			out.print("<num>");
			out.print("<![CDATA["+al.getNum()+"]]>");
			out.print("</num>");
			
			out.print("<title>");
			out.print("<![CDATA["+al.getTitle()+"]]>");
			out.print("</title>");
			
			out.print("<date>");
			out.print("<![CDATA["+al.getDate()+"]]>");
			out.print("</date>");
			
			out.print("<url>");
			out.print("<![CDATA["+al.getUrl()+"]]>");
			out.print("</url>");
			
			out.print("<status>");
			out.print("<![CDATA["+al.getStatus()+"]]>");
			out.print("</status>");
			
			out.print("</article>");
			
		}
		
		out.print("</aNotice>");	
		
	}

	
%>