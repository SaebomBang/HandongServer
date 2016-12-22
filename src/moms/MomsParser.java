package moms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;


/**
 * web parser class
 * parse data from web pages
 * 
 * work with WEBCrawler class
 * WEBCrawler passes crawled data to WEBParser
 * and WEBParser parses meaningful data and return it
 */
public class MomsParser {
	private Source source = null;
	private int length = 0;
	
	public MomsParser(InputStream input) {
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

	// parsing personal info
	// and return it as map format data
	public List<Map<String, String>> getAllMenu() {
		
		List<Map<String, String>> allMenu = new ArrayList<Map<String, String>>();
		Map<String,String>Moms = null;
		Element trs[] = new Element[10];
		
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
		int tempSize = tempTr.size()-2;
		for(int i = 0 ; i<tempSize; i++){
			trs[i] = tempTr.get(i+2);
		}
		
		for(int i =0; i<tempSize;i++){
			Moms = new HashMap<String,String>();
			
		    String date = trs[i].getChildElements().get(0).getContent().toString().replaceAll("<br>", " ").replaceAll("<p class=\"style8\">","").replaceAll("</p>","").replaceAll(System.getProperty("line.separator"), "").trim();
			String breakfast = trs[i].getChildElements().get(1).getContent().toString().replaceAll("<br />", "\n").replaceAll("<p class=\"style3\">","").replaceAll(System.getProperty("line.separator"), "").trim();
			String lunNdin = trs[i].getChildElements().get(2).getContent().toString().replaceAll("<br />", "\n").replaceAll("<p class=\"style3\">","").replaceAll(System.getProperty("line.separator"), "").trim();
			Moms.put("date", date);
			Moms.put("breakfast", breakfast);
			Moms.put("lunNdin", lunNdin);
			allMenu.add(Moms);
			
		}
		
		return allMenu;
	}
	
}

