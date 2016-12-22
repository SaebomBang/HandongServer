package resourceNotice;

import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;

public class ResourceContentsManager {
	private String id;
	private String pw;
	
	public ResourceContentsManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public String getContents(String url){
		String contentsMap = null;
		
		ResourceContentsCrawler crawler = new ResourceContentsCrawler(url);
		InputStream result = crawler.getContentsInfo(id, pw);
		ResourceContentsParser parser= new ResourceContentsParser(result);
		contentsMap = parser.getContentsViewer();
		
		return contentsMap;
	}
}
