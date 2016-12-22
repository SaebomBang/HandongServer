package courseNotice;

import java.io.IOException;
import java.lang.StringBuffer;
import java.io.InputStream;
import java.util.List;

import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.Element;

public class SemesterParser {
	private Source source = null;
	private int length ;
	
	public SemesterParser(){
		super();
	}
	
	public int getLength(){
		return length;
	}
	
	public String getSemester(String id, String pw){
	
		SemesterCrawler crawler= new SemesterCrawler();
		InputStream result = crawler.getSemesterInfo(id,pw);
		Element tempForm = null;
		String semester = "";
		
		try {
			source = new Source(result);
		} catch (IOException e) {
			e.printStackTrace();
			}
		source.fullSequentialParse();
		length = source.length();
		
		
		
		List<Element> forms = source.getAllElements(HTMLElementName.FORM);
		for(Element f : forms){
			String find = f.getAttributeValue("name");
			if(find!=null && find.equals("form_box1")){
				tempForm = f;
				System.out.println("1231231231");
				break;
			}
		}
		
		
		List<Element> options = tempForm.getAllElements(HTMLElementName.OPTION);
		for(Element o : options){
			int find = o.getAttributes().getCount();
			if(find==2){
				semester += o.getAttributeValue("value");	
			}
		}
		StringBuffer sb = new StringBuffer(semester);
		sb.insert(4,"0");
		semester = sb.toString();
		
		return semester;
	}

	
}
