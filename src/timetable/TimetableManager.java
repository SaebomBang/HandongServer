package timetable;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class TimetableManager {
	
	private String id;
	private String pw;
	
	public TimetableManager(){
		super();
	}
	
	public TimetableManager(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	
	public Vector<Timetable> getData(){
	
		TimetableCrawler crawler = new TimetableCrawler();
		InputStream result = crawler.getTimeInfo(id, pw);
		TimetableParser parser = new TimetableParser(result);
		Vector<Timetable> tVector = new Vector<Timetable>();
		
		List<Map<String, String>> timeMap = parser.getAllTable();
		for(int i=0 ; i<timeMap.size();i++){
			tVector.add(new Timetable(timeMap.get(i).get("day"),timeMap.get(i).get("period"),timeMap.get(i).get("subject"),timeMap.get(i).get("prof"),timeMap.get(i).get("place")));
		//길이 체크를 해주어야 함. (못받아 왔을때를 대비하여) if (parser.getLength() >= 1000) {} If success login, it returns more than 1000 characters
		}
		return tVector;
	}
}
