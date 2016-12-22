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

public class BusParser_HeungHae {
	public URL Url;
	public HttpURLConnection con; // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 connection
	public InputStream ret; // 占쏙옙占쏙옙占쌔쇽옙 占쌀쏙옙 占쏙옙占쏙옙 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙

	public BusParser_HeungHae() {
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
				con.setDoOutput(true); // 占쏙옙쩍占싣�占쏙옙 占쏙옙占� 占쏙옙占쏙옙占싹곤옙 占쏙옙
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
		List<Bus> bus = new ArrayList<Bus>(); // 占시곤옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙占쏙옙 List
		TimeSplit ts = new TimeSplit();

		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse(); // 占쏙옙占쌜븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占승그듸옙占쏙옙 parse

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
			String times, lotari, HGU, gokgang, heungHae;
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

			if ((trs.get(i).getChildElements().size() < 2)) {
				System.out.println("Sorry there is no bus.");
			}

			else {
				times = trs.get(i).getChildElements().get(0).getContent()
						.toString();
				lotari = trs.get(i).getChildElements().get(1).getContent()
						.toString();
				HGU = trs.get(i).getChildElements().get(2).getContent()
						.toString();
				gokgang = trs.get(i).getChildElements().get(3).getContent()
						.toString();
				heungHae = trs.get(i).getChildElements().get(4).getContent()
						.toString();

				int a = lotari.indexOf(":");
				b.setTimeSplit(ts.ampm(lotari.substring(0, a)));

				lotari = ts.spt(lotari);
				HGU = ts.spt(HGU);
				gokgang = ts.spt(gokgang);
				heungHae = ts.spt(heungHae);

				b.setTimes(times);
				b.setLotari(lotari);
				b.setHGU(HGU);
				b.setGokgang(gokgang);
				b.setHeunHae(heungHae);

				a = lotari.indexOf(":");
				b.setTZone(lotari.substring(0, a));
				bus.add(b);
			}
		}
		return bus;
	}
}