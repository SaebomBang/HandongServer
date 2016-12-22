package bus;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class BusParser_toSchool {
	public URL Url; // URL 占쎈쐻占쎈짗占쎌굲筌ｏ옙
	public HttpURLConnection con; // 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 connection
	public InputStream ret; // 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈솋占쎈닰占쎌굲 占쎈쐻占쏙옙占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲筌ｋ떣�앾옙猷욑옙��占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲

	public BusParser_toSchool() {
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
				con.setDoOutput(true); // 占쎈쐻占쎈짗占쎌굲冶⑤씢�앾옙�띶뜝�뀀쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�좑옙 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣�ⓦ끉��占쎈쐻占쎈짗占쎌굲
				ret = con.getInputStream();
			} catch (Exception e) {
				System.err.println(e);
				return null;
			}
		}
		return ret;
	}

	public List<Bus> Parserer(InputStream input)
			throws UnsupportedEncodingException, ParseException {
		Source source = null;
		List<Bus> bus = new ArrayList<Bus>();
		List<Bus> listedBus = new ArrayList<Bus>();
		TimeSplit ts = new TimeSplit();

		try {
			source = new Source(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		source.fullSequentialParse(); // 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈솙�됱빘�뺧옙�앾옙猷욑옙��占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뱟域밸챶踰앾옙�뺧옙�앾옙猷욑옙��parse

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
		for (int i = 1; i < trs.size(); i++) {
			Bus b = new Bus();
			String six, hwan, school;

			// version check
			if (i == 1) {
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

			// �좎럥由곁뼨轅명�鸚룸쨪彛뺧옙留⒴첎占�
			else if ((i == trs.size() - 1)
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

				int a = hwan.indexOf(":");
				b.setTimeSplit(ts.ampm(hwan.substring(0, a)));

				school = ts.spt(school);
				hwan = ts.spt(hwan);
				six = ts.spt(six);
 
				if (school.contains("占쎌젟占쎌뜚")) {
				} else {
					

					b.setSchool(school);
					b.setHwan(hwan);

					a = hwan.indexOf(":");
					b.setTZone(hwan.substring(0, a));

					bus.add(b);
				}
			}

			// �좎럩逾х뛾�녾섭揶쏅㉡�숃キ占�
			else if ((trs.get(i).getChildElements().size() == 5)) {
				int a = 0;
				six = trs.get(i).getChildElements().get(2).getContent()
						.toString();
				hwan = trs.get(i).getChildElements().get(3).getContent()
						.toString();
				school = trs.get(i).getChildElements().get(4).getContent()
						.toString();
				if ((school.contains("-")) && (hwan.contains("-"))) {
				} else {
					if (hwan.contains(":")) {
						a = hwan.indexOf(":");
						b.setTimeSplit(ts.ampm(hwan.substring(0, a)));
					} else if (six.contains(":")) {
						a = six.indexOf(":");
						b.setTimeSplit(ts.ampm(six.substring(0, a)));
					}
					school = ts.spt(school);
					hwan = ts.spt(hwan);
					six = ts.spt(six);

					if (school.contains("占쎌젟占쎌뜚")) {
					} else {
						b.setSchool(school);
						b.setSix(six);
						b.setHwan(hwan);

						if (six.contains(":")) {
							a = six.indexOf(":");
							b.setTZone(six.substring(0, a));
						} else if (hwan.contains(":")) {
							a = hwan.indexOf(":");
							b.setTZone(hwan.substring(0, a));
						}
					}
					bus.add(b);
				}
			}
			/* 占쎈츟雅뚯럥而�틠占�占쎄숲占쎈연占쎌뿳占쎈뮉椰꾬옙 占쎈퉸野껉퀬鍮먫퉪�용┛ */
			Bus first = new Bus();
			Bus second = new Bus();
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			listedBus = bus;
			for (int z = 0; z < 3; z++) {
				for (int j = 1; j < listedBus.size() - 1; j++) {
					first = listedBus.get(j);
					second = listedBus.get(j + 1);

					Date firstTime = null;
					Date secondTime = null;

					if (first.getTimeSplit().equals("pm")
							&& second.getTimeSplit().equals("pm")) {
						if (!first.getTZone().equals("12")) {
							firstTime = formatter.parse(first.getHwan());
							secondTime = formatter.parse(second.getHwan());

							if (firstTime.after(secondTime)) {
								listedBus.set(j, second);
								listedBus.set(j + 1, first);
							}
						}
					}

					else if (first.getTimeSplit().equals("am")
							&& second.getTimeSplit().equals("am")) {
						if (first.getTZone().equals("12")
								&& second.getTZone().equals("12")) {
							firstTime = formatter.parse(first.getHwan());
							secondTime = formatter.parse(second.getHwan());

							if (firstTime.after(secondTime)) {
								listedBus.set(j, second);
								listedBus.set(j + 1, first);
							}
						}
					}

					if (first.getTimeSplit().equals("pm")
							&& second.getTimeSplit().equals("pm")) {
						if (first.getTZone().equals("12") && second.getTZone().equals("12")) {
							firstTime = formatter.parse(first.getHwan());
							secondTime = formatter.parse(second.getHwan());

							if (firstTime.after(secondTime)) {
								listedBus.set(j, second);
								listedBus.set(j + 1, first);
							}
						}
					}
					
					if (first.getTimeSplit().equals("am")
							&& second.getTimeSplit().equals("am")) {
						if (!first.getTZone().equals("12") && !second.getTZone().equals("12")) {
							firstTime = formatter.parse(first.getHwan());
							secondTime = formatter.parse(second.getHwan());

							if (firstTime.after(secondTime)) {
								listedBus.set(j, second);
								listedBus.set(j + 1, first);
							}
						}
					}
				}
			}

			bus = listedBus;

		}
		return bus;
	}
}