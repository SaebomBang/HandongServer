package haksik;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class HaksikManager {
	
	private String date ;
	private String menu[] ;
	private String price[] ;
	private Haksik hs ;
	private List<Map<String,String>> haksikMenu = null;
	private HaksikParser haksikParser = null;
	
	public HaksikManager(){
		hs = new Haksik();
		haksikMenu = new ArrayList<Map<String,String>>();
		haksikParser = new HaksikParser();
		menu = new String[8];
		price = new String[8];
	}
	
	public Haksik getHaksik(){
	
		haksikMenu = haksikParser.getHaksikData();
		
		for(int i=0 ; i< 8 ; i++){
			menu[i]=haksikMenu.get(i).get("menu");
		}
		hs.setMenu(menu[0],menu[1],menu[2],menu[3],menu[4],menu[5],menu[6],menu[7]);
		
		for(int i=0 ; i< 8 ; i++){
			price[i]=haksikMenu.get(i).get("price");
		}
		hs.setPrice(price[0],price[1],price[2],price[3],price[4],price[5],price[6],price[7]);
		return hs;
	}
	
	public String getDate(){
		date = haksikParser.getDate();
		return date;
	}

	
	
}


