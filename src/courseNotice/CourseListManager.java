package courseNotice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CourseListManager {
	
	private String id;
	private String pw;
	
	public CourseListManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public Vector<CourseList> getCourseList(String semester, String gwamok, String page){
		
		List<Map<String, String>> allList = new ArrayList<Map<String,String>>();
		Vector<CourseList> courseList = new Vector<CourseList>();
		CourseListCrawler crawler = new CourseListCrawler(semester,gwamok,page);
		InputStream result = crawler.getListInfo(id,pw);
		CourseListParser parser = new CourseListParser(result);
		allList = parser.getAllList();
		
		for( int i=0; i< allList.size(); i++){
			courseList.addElement(new CourseList(allList.get(i).get("num"),allList.get(i).get("title"),allList.get(i).get("date"),allList.get(i).get("url"),allList.get(i).get("page"),allList.get(i).get("isNext")));
		}
		
		return courseList;
	}
}
