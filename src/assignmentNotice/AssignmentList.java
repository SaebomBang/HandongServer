package assignmentNotice;

public class AssignmentList {
	private String num;
	private String title;
	private String date;
	private String url;
	private String status;
	
	public AssignmentList(String num, String title, String date, String url, String status){
		this.num = num;
		this.title = title;
		this.date= date;
		this.url = url;
		this.status = status;
	}
	
	public String getNum(){
		return num;
	}
	
	public void setNum(String num){
		this.num = num;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
