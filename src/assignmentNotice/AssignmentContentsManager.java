package assignmentNotice;

import java.io.InputStream;
//import java.util.Map;

public class AssignmentContentsManager {
	private String id;
	private String pw;
	
	public AssignmentContentsManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public String getContents(String url){
		String contents = null;
		
		AssignmentContentsCrawler crawler = new AssignmentContentsCrawler(url);
		InputStream result = crawler.getContentsInfo(id, pw);
		AssignmentContentsParser parser= new AssignmentContentsParser(result);
		contents = parser.getContentsViewer();
		
		return contents;
	}
}
