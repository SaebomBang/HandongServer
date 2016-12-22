package mainNotice;

public class MainNotice {
	private String date;
	
	public MainNotice(){
		super();
	}

	public MainNotice(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}
}
