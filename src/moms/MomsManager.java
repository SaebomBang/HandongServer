package moms;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import moms.MomsCrawler;
import moms.MomsParser;


// Business logic for login procedure
// handle login procedure through HISNET system
public class MomsManager {

	public MomsManager(){
		super();
	}

	public Vector<Moms> getData(){
		
		MomsCrawler crawler = new MomsCrawler();
		String id = "juanaevv";
		String pw = "007vpxmfk";
		List<Map<String, String>> MomsMenu = null;
		Vector<Moms> mVector = new Vector<Moms>();
		// do HISNET login and get Result
		InputStream result = crawler.getMenuInfo(id, pw);//id, pw 내가 작성
		MomsParser parser = new MomsParser(result);
		//길이 체크를 해주어야 함. (못받아 왔을때를 대비하여) if (parser.getLength() >= 1000) {} If success login, it returns more than 1000 characters
		MomsMenu = parser.getAllMenu(); 
		for (int i =0; i<MomsMenu.size(); i++ ){
			mVector.addElement(new Moms (MomsMenu.get(i).get("date"),MomsMenu.get(i).get("breakfast"),MomsMenu.get(i).get("lunNdin")));
		}
		
		return mVector;
	}

	
	
}