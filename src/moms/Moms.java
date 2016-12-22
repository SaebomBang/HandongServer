package moms;

public class Moms {
	
	private String date ;
	private String breakfast;
	private String lunNdin;
	
	public Moms(){
		super();
	}
	
	public Moms(String date,String br, String lNd){
		this.date = date;
		this.breakfast = br;
		this.lunNdin = lNd;
	}
	
	public void setBreakfast(String br){
		this.breakfast = br;
	}
	
	public String getBreakfast(){
		return breakfast;
	}
	
	public void setLunNdin(String lNd){
		this.lunNdin = lNd;
	}
	
	public String getLunNdin(){
		return lunNdin;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
}
