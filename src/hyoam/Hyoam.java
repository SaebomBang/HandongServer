package hyoam;

public class Hyoam {
	private String date ;
	private String menu;
	private String price;
	
	public Hyoam(){
		super();
	}
	
	public Hyoam(String date,String menu, String price){
		this.date = date;
		this.menu = menu;
		this.price = price;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setMenu(String menu){
		this.menu = menu;
	}
	
	public String getMenu(){
		return menu;
	}
	
	public void setPrice(String price){
		this.price = price;
	}
	
	public String getPrice(){
		return price;
	}
	
}
