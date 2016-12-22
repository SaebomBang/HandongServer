package hgutube;

public class Hgutube {
	private String category;
	private String url;
	private String title;
	private String writer;
	private String time;

	public Hgutube(){
		super();
	}

	public Hgutube(String category, String url, String title, String writer, String time){
		this.category = category;
		this.url = url;
		this.title = title; 
		this.writer = writer;
		this.time = time;
	}
	
	public String getCat(){
		return category;
	}

	public void setCat(String category){
		this.category = category;
	}
	
	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}
	
	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public String getWriter(){
		return writer;
	}

	public void setWriter(String writer){
		this.writer = writer;
	}
	
	public String getTime(){
		return time;
	}

	public void setTime(String time){
		this.time = time;
	}
	
}