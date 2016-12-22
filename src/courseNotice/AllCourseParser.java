package courseNotice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;

public class AllCourseParser {
	
	private Source source = null;
	private int length = 0;
	Element tempTable= null;
	
	public AllCourseParser(){
		super();
	}
	
	public AllCourseParser(InputStream input) {
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
	
	
	public String[] getAllCourse(){
		
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		for(Element t : table){
			String find = t.getAttributeValue("id");
			if( find != null && find.equals("att_list")){
				tempTable = t;
				break;
			}
		}
		
		List<Element> trs = tempTable.getAllElements(HTMLElementName.TR);
		
		if (trs.size() < 2)
			return null;
		
		String[] allCourse = new String[trs.size()-1];
		
		for(int i=1; i<trs.size(); i++){
			String code = trs.get(i).getAllElements(HTMLElementName.TD).get(1).getContent().toString().replaceAll("<font color=\"#666666\">","").replaceAll("</font>","").trim();
			String group = trs.get(i).getAllElements(HTMLElementName.TD).get(5).getContent().toString().replaceAll("<font color=\"#666666\">","").replaceAll("</font>","").trim();
			String name = trs.get(i).getAllElements(HTMLElementName.TD).get(2).getContent().toString().replaceAll("<font color=\"#666666\">","").replaceAll("</font>","").trim();
			
			allCourse[i-1]=code+"-"+group+". "+name;
		}
		
		return allCourse;
	}
	
}
