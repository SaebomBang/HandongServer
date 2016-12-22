package WebParsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

/**
 * web parser class parse data from web pages
 * 
 * work with WEBCrawler class WEBCrawler passes crawled data to WEBParser and
 * WEBParser parses meaningful data and return it
 */
public class WEBParser {
	private Source source = null;

	public WEBParser(InputStream input) {
		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse();
	}

	public List<gongji> parseJuyoInfoName() {
		List<gongji> gong = new ArrayList<gongji>();

		List<Element> table = source.getAllElements(HTMLElementName.TR);
		String find = "";

		Element dataForm = null;

		for (Element e : table) {
			find = e.getAttributeValue("class");
			if (find != null && find.equals("tr_notice")) {
				dataForm = e;
				List<Element> trs = dataForm.getChildElements();
				String num = trs.get(0).getFirstElement(HTMLElementName.A).getContent().toString().trim();
				String title = trs.get(1).getFirstElement(HTMLElementName.A).getContent().toString().replaceAll("&#8203;","").replaceAll("<img src=\"./images/ico_re.gif\" border=\"0\" alt=\"답변표시\">","").replaceAll("<img src=\"/2012_images/public/icon_plus.gif\" border=\"0\" align=\"absmiddle\">","").trim();
				String gongjiURL = "https://hisnet.handong.edu/myboard/"
						+ trs.get(1).getContent().toString().split("=\"")[1]
								.split("\">")[0];

				gongji g = new gongji();
				g.setTitle(title);
				g.setNum(num);
				g.setGongjiURL(gongjiURL);
				gong.add(g);
			}
		}
		return gong;
	}

	public boolean isStringNum(String strValue) {
		try {
			Integer.parseInt(strValue);
		} catch (Exception NumberFormatException) {
			return false;
		}
		return true;
	}

	public List<gongji> parseIlbanInfoName() {
		List<gongji> gong = new ArrayList<gongji>();
		List<Element> trss = source.getAllElements(HTMLElementName.TR);
		String find = null;
		int a = 0;
		Element dataForm = null;

		for (Element e : trss) {
			if (e.getChildElements().size() <= 5) {
			} else {
				find = e.getChildElements().get(0).getContent().toString()
						.split(">")[1].split("<")[0];
				if (find.isEmpty()) {
					find = e.getChildElements().get(0).getContent().toString()
							.split("=\">")[1].split("<")[0];
					a++;
					if (isStringNum(find)) {
						dataForm = e;
						List<Element> trs = dataForm.getChildElements();
						String num=trs.get(0).getFirstElement(HTMLElementName.A).getContent().toString().trim();
						String title = trs.get(1).getFirstElement(HTMLElementName.A).getContent().toString().replaceAll("&#8203;","").replaceAll("<img src=\"./images/ico_re.gif\" border=\"0\" alt=\"답변표시\">","").replaceAll("<img src=\"/2012_images/public/icon_plus.gif\" border=\"0\" align=\"absmiddle\">","").trim();
						String gongjiURL = "https://hisnet.handong.edu/myboard/"
								+ trs.get(1).getContent().toString()
										.split("=\"")[1].split("\">")[0];
						
						gongji g = new gongji();
						g.setTitle(title);
						g.setNum(num);
						g.setGongjiURL(gongjiURL);
						gong.add(g);
						if (a == 30)
							break;
					}
				}
			}
		}
		return gong;
	}
}
