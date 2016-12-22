package generalNotice;

import java.io.InputStream;

public class NoticeContentsManager {
	private String id;
	private String pw;
	
	public NoticeContentsManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public String getWebContents(String url){
		String webContents = null;
		
		NoticeContentsCrawler ncc = new NoticeContentsCrawler(url);
		InputStream result = ncc.getContentsInfo(id, pw);
		NoticeContentsParser ncp = new NoticeContentsParser(result);
		webContents = ncp.getContentsViewer();
		
		return webContents;
	}
}
