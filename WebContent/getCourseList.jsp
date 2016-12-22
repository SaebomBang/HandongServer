<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="courseNotice.*" %>

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
	//모든 과목들을 긁어오는 과정에서는 jsp에서 semester을 정해서 넘겨 준다. 그러나 과목 공지사항 리스트에서는 자동으로 학기가 hisnet에서 정의된 학기로 되어있다.
	//CourseListManager에 정의되어있음
	
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
		
		CourseListManager clManager = new CourseListManager(id,pw);
		Vector<CourseList> clVector = clManager.getCourseList(semester,gwamok,num);
		
		CourseList cl;
		
		out.print("<cNotice>");
		
		
		for(int i=0; i<clVector.size() ; i++){
			cl = clVector.get(i);
			
			out.print("<article>");
			
			out.print("<num>");
			out.print("<![CDATA["+cl.getNum()+"]]>");
			out.print("</num>");
			
			out.print("<title>");
			out.print("<![CDATA["+cl.getTitle()+"]]>");
			out.print("</title>");
			
			out.print("<date>");
			out.print("<![CDATA["+cl.getDate()+"]]>");
			out.print("</date>");
			
			out.print("<url>");
			out.print("<![CDATA["+cl.getUrl()+"]]>");
			out.print("</url>");
			
			out.print("<page>");
			out.print("<![CDATA["+cl.getPage()+"]]>");
			out.print("</page>");
			
			out.print("<isNext>");
			out.print("<![CDATA["+cl.getIsNext()+"]]>");
			out.print("</isNext>");
			
			out.print("</article>");
			
		}
		
		out.print("</cNotice>");	
		
	}

	
%>