package resourceNotice;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class ResourceNoticeManager {
	private String id;
	private String pw;
	
	public ResourceNoticeManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public Vector<ResourceList> getResourceList(String semester, String gwamok, String page){
		
		List<Map<String, String>> allList = null;
		Vector<ResourceList> ResourceList = new Vector<ResourceList>();
		ResourceNoticeCrawler crawler = new ResourceNoticeCrawler(semester,gwamok,page);
		InputStream result = crawler.getListInfo(id,pw);
		ResourceNoticeParser parser = new ResourceNoticeParser(result);
		allList = parser.getResourceList();
		
		for( int i=0; i< allList.size(); i++){
			ResourceList.addElement(new ResourceList(allList.get(i).get("num"),allList.get(i).get("title"),allList.get(i).get("date"),allList.get(i).get("url"),allList.get(i).get("page"),allList.get(i).get("isNext")));
		}
		
		return ResourceList;
	}
}
