package hyoam;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.io.InputStream;

public class HyoamManager {
	public HyoamManager(){
		super();
	}
	
	public List<Vector<Hyoam>> getHyoam(){
		
		
		HyoamCrawler crawler= new HyoamCrawler();
		String id = "juanaevv";
		String pw = "007vpxmfk";
		List<Map<String,String>> hyoamMenu = new ArrayList<Map<String,String>>();
		Vector<Hyoam> hVector = null;
		List<Vector<Hyoam>> weekHyoam = new ArrayList<Vector<Hyoam>>();
		
		try{
		InputStream result = crawler.getMenuInfo(id,pw);
		HyoamParser parser = new HyoamParser(result);
		hyoamMenu = parser.getAllMenu();
		for (int i =0; i<hyoamMenu.size(); i++ ){
			if(i%11==0){
				hVector = new Vector<Hyoam>();
				weekHyoam.add(hVector);
			}
			hVector.addElement(new Hyoam(hyoamMenu.get(i).get("date"),hyoamMenu.get(i).get("menu"),hyoamMenu.get(i).get("price")));
			
		}
		
		}catch(NullPointerException e1){
			System.out.println(e1.toString() + "에러 발생");
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		return weekHyoam;
	}
	
}
