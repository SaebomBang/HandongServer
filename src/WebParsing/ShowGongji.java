package WebParsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class ShowGongji {
	private Source source = null;

	public ShowGongji(InputStream input) {
		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse();
	}

	public List<gongji> parseReadGongji() {
		List<gongji> gong = new ArrayList<gongji>();
		String find = "";
		Element dataForm = null;

		List<Element> tds = source.getAllElements(HTMLElementName.TD);
		for (Element e : tds) {
			find = e.getAttributeValue("class");
			if (find != null && find.equals("readText BoardContent")) {
				dataForm = e;
				break;
			}
		}
		String forms = dataForm.getChildElements().toString();
		gongji g = new gongji();
		g.setContents(forms);
		gong.add(g);
		
		return gong;
	}

	public List<gongji> getAttachFile() {
		List<gongji> gong = new ArrayList<gongji>();
		String find = "";
		Element dataForm = null;
		String Fileurl = null;

		List<Element> tds = source.getAllElements(HTMLElementName.TD);
		for (Element e : tds) {
			find = e.getAttributeValue("class");
			if (find != null && find.equals("listBody tdAttach")) {
				dataForm = e;
				List<Element> trs = dataForm.getAllElements(HTMLElementName.TR);
				for (int i = 0; i < trs.size(); i++) {
					Fileurl = "https://hisnet.handong.edu"
							+ trs.get(i).getChildElements().get(1).getContent()
									.toString().split("=\"")[1].split("\">")[0];
					gongji g = new gongji();
					g.setAttachFileURL(Fileurl);
					gong.add(g);
				}
			}
		}
		return gong;
	}
}
