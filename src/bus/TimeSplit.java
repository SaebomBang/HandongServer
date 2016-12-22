package bus;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TimeSplit {
	public TimeSplit() {
	}

	public String spt(String time) {
		if (time.contains(":")) {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			Date date = null;

			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String formattedTime = sdf.format(date);

			return formattedTime;
		} else
			return time;
	}

	public String ampm(String time) {
		int timeInt = Integer.parseInt(time);
		if (timeInt >= 0 && timeInt < 11)
			return "am";
		else
			return "pm";
	}

	public String tZoneCompare(String before, String after) {
		// this method is compare 2 string
		if (before.equals(after))
			return null;
		else
			return after;
	}
}
