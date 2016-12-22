package courseNotice;

import java.io.InputStream;

public class CourseContentsManager {
	private String id;
	private String pw;
	
	public CourseContentsManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public String getContents(String url){
		String web = null;
		
		CourseContentsCrawler crawler = new CourseContentsCrawler(url);
		InputStream result = crawler.getContentsInfo(id, pw);
		CourseContentsParser parser= new CourseContentsParser(result);
		web = parser.getContentsViewer();
		
		return web;
	}
	
}
