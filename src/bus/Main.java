/*package bus;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String addr = "http://smart.handong.edu/service/index.php/main/lookup_lists/MjEzMDAzMzg=/v4/";

		// 기본작업
		BusParser_toSchool parser = new BusParser_toSchool();
		InputStream craw = parser.Crawler(addr);

		ArrayList<Bus> bus = (ArrayList<Bus>) parser.Parserer(craw);

		Bus b = new Bus();
		int beforeInt = 0;
		for (int i = 0; i < bus.size(); i++) {
			b = bus.get(i);

			int afterInt = Integer.parseInt(b.getTZone());
			if (i == 1) {
				System.out.print("<tZone tzone='" + b.getTZone() + "'>");

				System.out.print("<timesplit>");
				System.out.print("<![CDATA[" + b.getTimeSplit() + "]]>");
				System.out.print("</timesplit>");

				System.out.print("<tzone>");
				System.out.print("<![CDATA[" + b.getTZone() + "]]>");
				System.out.print("</tzone>");
			}
			beforeInt = Integer.parseInt(b.getTZone());
			System.out.print("<Bus>");

			System.out.print("<six>");
			System.out.print("<![CDATA[" + b.getSchool() + "]]>");
			System.out.print("</six>");

			System.out.print("<hwan>");
			System.out.print("<![CDATA[" + b.getHwan() + "]]>");
			System.out.print("</hwan>");

			System.out.print("<school>");
			System.out.print("<![CDATA[" + b.getSix() + "]]>");
			System.out.print("</school>");

			System.out.print("</Bus>");

		}
	}
}*/