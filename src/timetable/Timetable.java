package timetable;

public class Timetable {
	private String day;
	private String period;
	private String subject;
	private String place;
	private String prof;
	
	public Timetable(String day,String period, String subject, String prof, String place){
		this.day = day;
		this.period = period;
		this.subject = subject;
		this.prof = prof;
		this.place = place;	
	}
	
	public String getDay(){
		return day;
	}
	
	public void setDay(String day){
		this.day = day;
	}
	
	public String getPeriod(){
		return period;
	}
	
	public void setPeriod(String period){
		this.period = period;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public String getPlace(){
		return place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
	public String getProf(){
		return prof;
	}
	
	public void setProf(String prof){
		this.prof = prof;
	}
	
}
