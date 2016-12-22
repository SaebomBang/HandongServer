package haksik;

import java.io.InputStream;

import net.htmlparser.jericho.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;


/**
 * Crawler for Website
 * used for HISNET crawling
 */
public class HaksikParser {
	
	private String menuUrl = "http://hisnet.handong.edu/login/login.php";
    private Source source = null;
    private List<Element> tds1 = null;
    private List<Element> tds2 = null;
    private Map<String, String> menuMap = null;
    private List<Map<String, String>> allMenu = new ArrayList<Map<String, String>>();
	
	
	public HaksikParser(){
		InputStream result = null;
    	try {
            URL url = new URL(menuUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            result = conn.getInputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
      
        try {
			source = new Source(result);
			source.fullSequentialParse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getDate(){
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = gc.getTime();
		String str = sf.format(time);
		return str.substring(4,8);
	}
	
	
	public List<Map<String,String>> getHaksikData(){
		Element[] trs = new Element[20];	
		List<Element> trList1 = source.getAllElements(HTMLElementName.TR);
		Element tr = null;
		for (Element t1 : trList1){//table을 갖고오기 위해 특징이 있는 tr을 찾는다.
			String find1 = t1.getAttributeValue("id");
			if( find1!=null&&find1.equals("tr_box11_001")){
				tr = t1;
				break;
			}
		}
		tds1= tr.getAllElements(HTMLElementName.TD);
		String breakfast = tds1.get(5).getContent().toString().replaceAll("<BR>", "\n").trim();
		String lunch = tds1.get(6).getContent().toString().replaceAll("<BR>", "\n").trim();//끝에 엔터가 입력 되어있음.
		String dinner = tds1.get(7).getContent().toString().replaceAll("<BR>", "\n").trim();
		menuMap = new HashMap<String, String>();
		menuMap.put("menu",breakfast);
		menuMap.put("price","2800");
		allMenu.add(menuMap);
		menuMap = new HashMap<String, String>();
		menuMap.put("menu",lunch);
		menuMap.put("price","2800");
		allMenu.add(menuMap);
		menuMap = new HashMap<String, String>();
		menuMap.put("menu",dinner);
		menuMap.put("price","2800");
		allMenu.add(menuMap);
		
		// Korean table 이외의 메뉴를 가져오는 부분.
		int i=2;
		List<Element> trList2 = source.getAllElements(HTMLElementName.TR);
		for(Element t2 : trList2){
			String find2 = t2.getAttributeValue("id");
			String add=Integer.toString(i);
			if( find2!= null && find2.equals("tr_box11_00"+add)){
				trs[i-2] = t2;
				i++;
				
			}	
		}
   	
		// FryFry = trs[0], NoodleRoad = trs[1], Hao = trs[2], GraceGarden = trs[3], MixRice = trs[4]
		String menu, price ;
		
		for(i=0;i<5;i++){
			menuMap = new HashMap<String, String>();
			tds2 = trs[i].getAllElements(HTMLElementName.TD);
			menu = tds2.get(1).getContent().toString().replaceAll("<BR>","\n").trim();
			price = tds2.get(2).getContent().toString().replaceAll("<BR>","\n").trim();
			menuMap.put("menu",menu);
			menuMap.put("price",price);
			allMenu.add(menuMap);    		//allMenu에서 (0번인자부터 7까지 순서대로, kote1, kote2, kote3, FryFry, NoodleRoad, Hao, GraceGarden, MixRice).
		}
    	
	return allMenu;
    
	}
	
	
}
    
    