package resourceNotice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class ResourceContentsParser {
	private Source source = null;
	private int length = 0;
	
	public ResourceContentsParser(){
		super();
	}
	
	public ResourceContentsParser(InputStream input) {
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
	
	
	
	/*
	public Map<String,String> getAllContents(){
		
		Map<String,String>allContents = new HashMap<String,String>();
		List<Element> span = source.getAllElements(HTMLElementName.SPAN);
		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		List<Element> td = source.getAllElements(HTMLElementName.TD);
		List<Element> tempSpan = new ArrayList<Element>();
		Element contentsTable = null;
		Element fileTable = null;
		String file = null;
		
		for(Element s : span){
			if(s.getAttributeValue("style")!=null)
				tempSpan.add(s);
		}
		
		for(Element t: table){
			String find = t.getAttributeValue("cellpadding");
			if(find!=null && find.equals("10")){
				contentsTable = t;
				break;
			}
		}
		
		for(Element d: td){
			file = d.getAttributeValue("class");
			if(file!=null && file.equals("listBody tdAttach")){
				fileTable = d.getFirstElement(HTMLElementName.TABLE);
				break;
			}
		}
		
		String title = tempSpan.get(1).getContent().toString().replaceAll("<img src=\"/2012_images/public/icon_plus.gif\" border=\"0\" align=\"absmiddle\">","").trim();
		String date = tempSpan.get(3).getContent().toString().trim();
		String writer = tempSpan.get(5).getContent().toString().trim();
		String contents = contentsTable.getFirstElement(HTMLElementName.TD).getContent().toString().replaceAll("</p>","\n\n").replaceAll("<p>","").replaceAll("<b>","").replaceAll("</b>", "").replaceAll("<br>","\n").replaceAll("&nbsp;","").trim();
		allContents.put("title",title);
		allContents.put("date",date);
		allContents.put("writer",writer);
		allContents.put("contents", contents);
		
		if(file!=null){
			String f_url = fileTable.getFirstElement(HTMLElementName.A).getAttributeValue("href").trim();
			String f_name = fileTable.getFirstElement(HTMLElementName.A).getContent().toString().split("<")[0].trim();
			allContents.put("fileUrl",f_url);
			allContents.put("fileName",f_name);
		}
		else{
			allContents.put("fileUrl","");
			allContents.put("fileName","");
		}
		
		return allContents;
	}
	*/
}
