package assignmentNotice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class AssignmentListParser {
	private Source source = null;
	private int length = 0;
	
	public AssignmentListParser(){
		super();
	}
	
	public AssignmentListParser(InputStream input) {
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
	
	public List<Map<String,String>> getAllList(){
		
		List<Map<String, String>> allList = new ArrayList<Map<String, String>>();
		Map<String,String>listMap = null;
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		Element tempTable = null;
		
		for (Element t : table) {
			String find = t.getAttributeValue("id");
			if (find!=null&&find.equals("att_list")){
				tempTable = t;
				break;
			}
		}
		
		List<Element> trs = tempTable.getAllElements(HTMLElementName.TR);
		
		for(int i =1; i < trs.size(); i+=2){
			listMap = new HashMap<String,String>();
			String num=trs.get(i).getFirstElement(HTMLElementName.SPAN).getContent().toString().trim();
			String title=trs.get(i).getAllElements(HTMLElementName.TD).get(1).getFirstElement(HTMLElementName.A).getContent().toString().replaceAll("&#8203;","").replaceAll("<img src=\"./images/ico_re.gif\" border=\"0\" alt=\"답변표시\">","").replaceAll("<img src=\"/2012_images/public/icon_plus.gif\" border=\"0\" align=\"absmiddle\">","").trim();
			String date=trs.get(i).getAllElements(HTMLElementName.TD).get(2).getFirstElement(HTMLElementName.DIV).getContent().toString().trim();
			String check=trs.get(i).getAllElements(HTMLElementName.TD).get(3).getFirstElement(HTMLElementName.DIV).getContent().toString().trim();
			String status=null;
			if(check.length()>4){
				status = trs.get(i).getAllElements(HTMLElementName.TD).get(2).getAllElements(HTMLElementName.DIV).get(1).getContent().toString().trim();
			}
			String url = trs.get(i).getAllElements(HTMLElementName.TD).get(1).getFirstElement(HTMLElementName.A).getAttributeValue("href");
			listMap.put("num",num);
			listMap.put("title", title);
			listMap.put("date", date);
			listMap.put("status", status);
			listMap.put("url", url);
			allList.add(listMap);
		}
		return allList;
	}
}
