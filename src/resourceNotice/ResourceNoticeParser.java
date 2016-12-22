package resourceNotice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class ResourceNoticeParser {
	private Source source = null;
	private int length = 0;
	Element tempTable= null;
	
	public ResourceNoticeParser(){
		super();
	}
	
	public ResourceNoticeParser(InputStream input) {
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
	
	
	public List<Map<String,String>> getResourceList(){
		
		List<Map<String, String>> ResourceList = new ArrayList<Map<String, String>>();
		Map<String,String>listMap = null;
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		List<Element> strong = source.getAllElements(HTMLElementName.STRONG);
		List<Element> a;
		Element tempTable = null;
		Element tempTable2 = null;
		int count1 = 0, count2 =0;
		String value = "false";
		
		for (Element t : table) {
			String find = t.getAttributeValue("width");
			if (find!=null&&find.equals("100%")){
				tempTable = t;
				break;
			}
		}
		
		for (Element s : strong){
			String find = s.getContent().toString().trim();
			
		}
		count1++;
		
		for(Element t: table){
			String find = t.getAttributeValue("class");
			if(find!=null&&find.equals("tab")){
				tempTable2 = t;
				break;
			}
		}
		
		a = tempTable2.getAllElements(HTMLElementName.A);
		count2 = a.size();
		
		if(count2>=10)
			value = "true";
		
		String page = Integer.toString(count1);
		
		List<Element> trs = tempTable.getAllElements(HTMLElementName.TR);
		for(int i =1; i < trs.size()-1; i++){
			listMap = new HashMap<String,String>();
			String num=trs.get(i).getFirstElement(HTMLElementName.A).getContent().toString().trim();
			String title=trs.get(i).getAllElements(HTMLElementName.A).get(1).getContent().toString().replaceAll("&#8203;","").replaceAll("<img src=\"./images/ico_re.gif\" border=\"0\" ","").replaceAll("<img src=\"/2012_images/public/icon_plus.gif\" border=\"0\" align=\"absmiddle\">","").trim();
			String date=trs.get(i).getAllElements(HTMLElementName.TD).get(4).getFirstElement(HTMLElementName.DIV).getContent().toString().trim();
			String url = trs.get(i).getAllElements(HTMLElementName.A).get(1).getAttributeValue("href");
			listMap.put("num",num);
			listMap.put("title", title);
			listMap.put("date", date);
			listMap.put("url", url);
			listMap.put("page",page);
			listMap.put("isNext", value);
			ResourceList.add(listMap);
		}
				
		return ResourceList;
	}
}
