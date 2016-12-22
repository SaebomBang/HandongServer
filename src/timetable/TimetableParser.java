package timetable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class TimetableParser {
	private Source source = null;
	private int length = 0;
	
	public TimetableParser(){
		super();
	}
	
	public TimetableParser(InputStream input) {
		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//source.fullSequentialParse();
		length = source.length();
	}
	
	public int getLength() {
		return length;
	}
	
	public List<Map<String,String>> getAllTable(){
		List<Map<String, String>> allTable = new ArrayList<Map<String, String>>();
		Map<String, String>tableMap = null;
		
	
		Element table1 = source.getAllElements(HTMLElementName.TABLE).get(9);//table 24��°��
		Element tr = null;
		
		String day = null;
		String period = null;
		String subject = null;
		String prof = null;
		String place = null;
		int j =0;
		for(int i = 0; i< 13; i++)
		{
			
				try{//��� �� Ȥ�� ����
					  try{
			        	for(j = 0; j < 7; j++)
			        	{
			        		tr = table1.getAllElements(HTMLElementName.TR).get(i+1);
					        
					        Element td = tr.getAllElements(HTMLElementName.TD).get(2);
					        subject = td.getTextExtractor().toString();
					        
					        td = tr.getAllElements(HTMLElementName.TD).get(6);
					        prof = td.getTextExtractor().toString();
					        
					        td = tr.getAllElements(HTMLElementName.TD).get(8);
					        place =  td.getTextExtractor().toString();
					        
					        td = tr.getAllElements(HTMLElementName.TD).get(7);
					        String allContent =  td.getTextExtractor().toString();
							
					        String[] splitContent = allContent.split(",");
					        splitContent[0] = splitContent[0].substring(1);
					     
			        		day = splitContent[j].substring(0,1);
			        		period = splitContent[j].substring(1,2);
			
			        		System.out.println(day);
				        		if(day.equals("��"))
				        			day = "1";
				        		else if(day.equals("ȭ"))
				        			day = "2";
				        		else if(day.equals("��"))
				        			day = "3";
				        		else if(day.equals("��"))
				        			day = "4";
				        		else if(day.equals("��"))
				        			day = "5";
				        		else if(day.equals("��"))
				        			day = "6";
			        		tableMap = new HashMap<String,String>();
			        		
							//String day = Integer.toString(i+1);//��ȭ�������
							//String period = Integer.toString(i);//1234556789
						   
							//System.out.println(day[j] + period[j] + subject + prof + place);
							//day + period +
							tableMap.put("day", day);
							tableMap.put("period", period);
							tableMap.put("subject", subject);
							tableMap.put("prof", prof);
							tableMap.put("place", place);
							allTable.add(tableMap);
							System.out.println(tableMap);
						
			        	}
			        }catch(IndexOutOfBoundsException ex )
				    {  
				    
				    } 
					
			        
					
					}	
				catch(IndexOutOfBoundsException ex )
			    {  
				      
				      
				       
			    } 
			
		}
		return allTable;
	}
	
	
}
