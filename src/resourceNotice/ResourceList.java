package resourceNotice;

public class ResourceList {
	private String num;
	private String title;
	private String date;
	private String page;
	private String url;
	private String isNext;
	
	public ResourceList(String num, String title, String date, String url, String page, String isNext){
		this.num = num;
		this.title = title;
		this.date= date;
		this.url = url;
		this.page = page;
		this.isNext = isNext;
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
	
	public String getPage(){
		return page;
	}
	
	public void setPage(String page){
		this.page = page;
	}
	
	public String getIsNext(){
		return isNext;
	}
	
	public void setIsNext(String isNext){
		this.isNext = isNext;
	}
	
}
