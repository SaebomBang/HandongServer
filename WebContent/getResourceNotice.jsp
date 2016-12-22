<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="resourceNotice.*" %>
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
	String num = request.getParameter("page");
	
	if(semester == null){
		SemesterParser semester_p = new SemesterParser();
		semester = semester_p.getSemester(id,pw);
	}
	
	if((gwamok == null) || (num == null)){
	
		AllCourseCrawler allCrawler = new AllCourseCrawler(semester);
		AllCourseParser allParser = new AllCourseParser(allCrawler.getCourseInfo(id, pw));
		String[] allCourse = allParser.getAllCourse();
		
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
		
		ResourceNoticeManager rnManager = new ResourceNoticeManager(id,pw);
		Vector<ResourceList> rlVector = rnManager.getResourceList(semester,gwamok,num);
		ResourceList rl;
		
		
		out.print("<cNotice>");
		
		for(int i=0; i<rlVector.size() ; i++){
			rl = rlVector.get(i);
			
			out.print("<article>");
			
			out.print("<num>");
			out.print("<![CDATA["+rl.getNum()+"]]>");
			out.print("</num>");
			
			out.print("<title>");
			out.print("<![CDATA["+rl.getTitle()+"]]>");
			out.print("</title>");
			
			out.print("<date>");
			out.print("<![CDATA["+rl.getDate()+"]]>");
			out.print("</date>");
			
			out.print("<url>");
			out.print("<![CDATA["+rl.getUrl()+"]]>");
			out.print("</url>");
			
			out.print("<page>");
			out.print("<![CDATA["+rl.getPage()+"]]>");
			out.print("</page>");
			
			out.print("<isNext>");
			out.print("<![CDATA["+rl.getIsNext()+"]]>");
			out.print("</isNext>");
			
			out.print("</article>");
			
		}
		out.print("</cNotice>");
		
	}

	
%>