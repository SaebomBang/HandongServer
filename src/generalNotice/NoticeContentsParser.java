package generalNotice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class NoticeContentsParser {
	private Source source = null;
	private int length = 0;
	
	public NoticeContentsParser(){
		super();
	}
	
	public NoticeContentsParser(InputStream input) {
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
	
	public String getContentsViewer(){
		String contents;
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		List<Element> td =source.getAllElements(HTMLElementName.TD);
		List<Element> attach = null;
		Element tempTable=null;
		Element tempFile=null;
		Element img = null;
		int i=0;
		
		for(Element t: table){
			String find = t.getAttributeValue("width");
			if(i<2 && find!=null && find.equals("100%")){
				tempTable = t;
				i++;
			}
		}
		
		contents = tempTable.toString().replaceAll("\r","").replaceAll("\n","").replaceAll("\t","");
		
		for(Element d: td){
			String find = d.getAttributeValue("class");
			if(find!=null && find.equals("listBody tdAttach")){
				tempFile = d.getFirstElement(HTMLElementName.TABLE);
				break;
			}
		}
		
	
		if(tempFile!=null){
			attach = tempFile.getAllElements(HTMLElementName.A);
			
			for(Element at :attach){
				String find1 = at.getAttributeValue("href");
				String find2 = null;
				if(find1!=null&&(!find1.contains("http://"))){
					find2="http://hisnet.handong.edu"+find1;
					contents=contents.replace(find1,find2);
				}
			}
		}
		
		img = tempTable.getFirstElement(HTMLElementName.IMG);
		if(img != null){
			String find1 = img.getAttributeValue("src");
			if(!find1.contains("http://")){
				String find2 = "http://hisnet.handong.edu"+find1;
				contents=contents.replace(find1,find2);
			}
		}
		
		contents="<html> <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> <tr> <td valign=\"top\"> <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"> <LINK REL='stylesheet' TYPE='text/css' HREF='http://hisnet.handong.edu/myboard/skin/css.css'>" + contents;
		contents=contents+"</td> </tr> </table> </html>";
		
		return contents;
	}
}
