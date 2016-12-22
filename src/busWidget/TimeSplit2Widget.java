package busWidget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSplit2Widget {
	public TimeSplit2Widget() {
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

	public String currentTime(String getTime) throws ParseException {
		if (getTime.contains(":")) {
			long currentT = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("dd HH:mm");
			String str = dayTime.format(new Date(currentT));
			String day = str.substring(0,3);
			String criteria1 = str.substring(3,5);
			String criteria2 = getTime.split(":")[0];
			Date now = dayTime.parse(str);
			Date to = null;
			if(criteria1.equals("00")||criteria1.equals("01")||criteria1.equals("02")){
				if(criteria2 .equals("00")||criteria2.equals("01")||criteria2.equals("02")){
					getTime= day+getTime;
					to = dayTime.parse(getTime);
					if (now.before(to) || now.equals(to))
						return "a";
					else
						return "b";
				}
				else
					return "b";
			}
			else{
				if(criteria2.equals("00")||criteria2.equals("01")||criteria2.equals("02"))
					return "a";
				getTime=day+getTime;
				to=dayTime.parse(getTime);
				if (now.before(to) || now.equals(to))
					return "a";
				else
					return "b";	
			}
		}
		else
			return "-";
	}


}
