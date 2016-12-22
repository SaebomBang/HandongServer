package assignmentNotice;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class AssignmentListManager {
	private String id;
	private String pw;
	
	public AssignmentListManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	public Vector<AssignmentList> getAssignmentList(String semester, String gwamok){
		
		List<Map<String, String>> allList = null;
		Vector<AssignmentList> AssignmentList = new Vector<AssignmentList>();
		AssignmentListCrawler crawler = new AssignmentListCrawler(semester,gwamok);
		InputStream result = crawler.getListInfo(id,pw);
		AssignmentListParser parser = new AssignmentListParser(result);
		allList = parser.getAllList();
		
		for( int i=0; i< allList.size(); i++){
			AssignmentList.addElement(new AssignmentList(allList.get(i).get("num"),allList.get(i).get("title"),allList.get(i).get("date"),allList.get(i).get("url"),allList.get(i).get("status")));
		}
		
		return AssignmentList;
	}
}
