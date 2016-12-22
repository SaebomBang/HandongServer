package busWidget;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class BusWidget_toSchool {
	public URL Url;
	public HttpURLConnection con;
	public InputStream ret;

	public BusWidget_toSchool() {
	}

	public InputStream Crawler(String Address) {
		try {
			Url = new URL(Address);
		} catch (Exception e) {
			System.err.println(e);
		}

		if (Address == null || Address.length() == 0) {
			System.err.println("address setup->setUrl(String str)");
			return null;
		} else {
			try {
				con = (HttpURLConnection) Url.openConnection();
				con.setDoOutput(true);
				ret = con.getInputStream();
			} catch (Exception e) {
				System.err.println(e);
				return null;
			}
		}
		return ret;
	}

	public List<BusWidget> Parserer(InputStream input) throws ParseException {
		Source source = null;
		List<BusWidget> bus = new ArrayList<BusWidget>();
		TimeSplit2Widget timesplit = new TimeSplit2Widget();

		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse();

		List<Element> table = source.getAllElements(HTMLElementName.TABLE);
		Element form = null;
		String find = "";

		for (Element e : table) {
			find = e.getAttributeValue("cellpadding");
			if (find != null && find.equals("1")) {
				form = e;
				break;
			}
		}
		if (form == null)
			return null;

		List<Element> trs = form.getAllElements(HTMLElementName.TR);
		for (int i = 2; i < trs.size(); i++) {
			BusWidget b = new BusWidget();
			String six, hwan, school;
			if ((i == trs.size() - 1)
					&& (trs.get(i).getChildElements().size() == 3)) {
			}

			// morning cars
			else if (trs.get(i).getChildElements().size() == 3) {
				six = trs.get(i).getChildElements().get(0).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(1).getContent()
						.toString();
				school = trs.get(i).getChildElements().get(2).getContent()
						.toString();

				if (timesplit.currentTime(school).equals("a")||timesplit.currentTime(hwan).equals("a")||timesplit.currentTime(six).equals("a")) {
					school = timesplit.spt(school);
					hwan = timesplit.spt(hwan);
					six = timesplit.spt(six);
					
					
				}
			}

			else if ((trs.get(i).getChildElements().size() == 5)) {
				six = trs.get(i).getChildElements().get(2).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(3).getContent()
						.toString();
				school = trs.get(i).getChildElements().get(4).getContent()
						.toString();
				
				if (timesplit.currentTime(six).equals("a")||timesplit.currentTime(hwan).equals("a")||timesplit.currentTime(school).equals("a")){
					school = timesplit.spt(school);
					hwan = timesplit.spt(hwan);
					six = timesplit.spt(six);

					if ((school.contains("-")) && (hwan.contains("-"))
							&& (six.contains("-"))) {
					} else {
						b.setSix(six);
						b.setHwan(hwan);
						b.setSchool(school);

						bus.add(b);
					}
				}
			}

		}
		return bus;
	}
}