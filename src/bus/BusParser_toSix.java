package bus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class BusParser_toSix {
	public URL Url;
	public HttpURLConnection con; 
	public InputStream ret;

	public BusParser_toSix() {
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

	public List<Bus> Parserer(InputStream input) {
		Source source = null;
		List<Bus> bus = new ArrayList<Bus>();
		TimeSplit ts = new TimeSplit();
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
			Bus b = new Bus();
			String school = null, hwan = null, six = null;

			// version check
			if (i == 2) {
				if (b.getVesion() == null) {
					b.setVersion("1511");
					b.setSiz(Integer.toString(trs.size()));
				} else if (!b.getSiz().equals(Integer.toString(trs.size()))) {
					int aa = Integer.parseInt(b.getVesion());
					aa = aa + 1;
					b.setVersion(Integer.toString(aa));
					b.setSiz(Integer.toString(trs.size()));
				}
				bus.add(b);
			}

			// morning cars
			else if ((trs.get(i).getChildElements().size() == 3)
					&& (i == trs.size() - 1)) {
				school = trs.get(i).getChildElements().get(0).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(1).getContent()
						.toString();
				six = trs.get(i).getChildElements().get(2).getContent()
						.toString();

				int a = hwan.indexOf(":");
				b.setTimeSplit(ts.ampm(hwan.substring(0, a)));

				school = ts.spt(school);
				hwan = ts.spt(hwan);
				six = ts.spt(six);
				
				

				a = hwan.indexOf(":");
				b.setTZone(school.substring(0, a));
				bus.add(b);
			}

			//
			else if ((trs.get(i).getChildElements().size() == 4)
					&& (i == trs.size() - 1)) {
				school = trs.get(i).getChildElements().get(0).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(1).getContent()
						.toString();
				six = trs.get(i).getChildElements().get(2).getContent()
						.toString();
				school = ts.spt(school);
				hwan = ts.spt(hwan);
				six = ts.spt(six);

				int a = school.indexOf(":");
				b.setTimeSplit(ts.ampm(school.substring(0, a)));
			
				b.setSchool(school);
				b.setHwan(hwan);

				a = school.indexOf(":");
				b.setTZone(school.substring(0, a));
				bus.add(b);
			}

		
			else if (trs.get(i).getChildElements().size() == 3) {
			}

			// normal bus
			else if (trs.get(i).getChildElements().size() == 5) {
				school = trs.get(i).getChildElements().get(0).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(1).getContent()
						.toString();
				six = trs.get(i).getChildElements().get(2).getContent()
						.toString();

				int a = school.indexOf(":");
				b.setTimeSplit(ts.ampm(school.substring(0, a)));

				school = ts.spt(school);
				hwan = ts.spt(hwan);
				six = ts.spt(six);
				

				a = school.indexOf(":");
				b.setTZone(school.substring(0, a));
				bus.add(b);
			}
		}
		return bus;
	}
}