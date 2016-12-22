package hguShop;

public class HguShop {
	private int location;
	private int cat;
	private String name;
	private String contents;
	private String coordinate;
	private String url;
	private String phone;

	public HguShop(){
		super();
	}

	public HguShop(int location, int cat, String name, String contents, String coordinate, String url, String phone){
		this.location = location;
		this.cat = cat;
		this.name = name; 
		this.contents = contents;
		this.coordinate = coordinate;
		this.url = url;
		this.phone = phone;
	}

	public int getLocation(){
		return location;
	}

	public void setLocation(int location){
		this.location = location;
	}

	public int getCat(){
		return cat;
	}

	public void setCat(int cat){
		this.cat = cat;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getContents(){
		return contents;
	}
	
	public void setContents(String contents){
		this.contents = contents;
	}
	
	public String getCoordinate(){
		return coordinate;
	}
	
	public void setCoordinate(String coordinate){
		this.coordinate = coordinate;
	}
	
	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}
	
	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}
}
