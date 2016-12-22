package hyoam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class HyoamParser {
	private Source source = null;
	private int length = 0;
	private int day = 0;
	public HyoamParser(InputStream input) {
		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse();
		length = source.length();
	}
	
	public int getLength() {
		return length;
	}
	
	public int getDay(){
		return day;
	}
	

public List<Map<String, String>> getAllMenu() {
		
		List<Map<String, String>> allMenu = new ArrayList<Map<String, String>>();
		Map<String,String>hyoam = null;
		Element trs[][] = new Element[10][15];
		
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		Element tempTable = null;
		
		for (Element t : table) {
			String find = t.getAttributeValue("width");
			if (find!=null&&find.equals("750"))
				tempTable = t;
		}
		if (tempTable == null)
			return null;
		
		List<Element>tempTr = tempTable.getAllElements(HTMLElementName.TR);
		int tempSize = tempTr.size();
		day = tempSize/12;
		for(int i = 0, j=0 ; i<tempSize; i++){
			if((i%12)==0){
				j++;
			}
			trs[j-1][(i%12)] = tempTr.get(i);	
		}
		
		for(int i =0; i<(tempSize/12); i++){
		    String date = trs[i][0].getChildElements().get(0).getContent().toString().replaceAll("<br>", " ").replaceAll("<p class=\"style8\">","").replaceAll("</p>","").replaceAll(System.getProperty("line.separator"), "").trim();
		    for(int j =1 ; j<12; j++){
		    	hyoam = new HashMap<String,String>();
				String menu= trs[i][j].getChildElements().get(0).getContent().toString().replaceAll("<br>", " ").replaceAll("<p class=\"style8\">","").replaceAll("</p>","").trim();
				String price = trs[i][j].getChildElements().get(1).getContent().toString().replaceAll("<br>", " ").replaceAll("<p class=\"style8\">","").replaceAll("</p>","").trim();
				hyoam.put("date", date);
				hyoam.put("menu", menu);
				hyoam.put("price", price);
				allMenu.add(hyoam);
			}
		}
		
		return allMenu;
	}
}
