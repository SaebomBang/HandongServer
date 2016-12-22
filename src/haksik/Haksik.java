package haksik;

public class Haksik {
	//name[0] = menu, name[1] = price
	private static String date;
	private String kotebre[] = new String[2];
	private String kotelun[] = new String[2];
	private String kotedin[] = new String[2];
	private String fryfry[] = new String[2];
	private String noodle[] = new String[2];
	private String hao[]  = new String[2];
	private String grace[] = new String[2];
	private String mix[] = new String[2];

	public Haksik(){
		super();
	}
	

	public void setMenu(String bre1, String lun1, String din1, String fry1, String noo1, String hao1, String grace1, String mix1){
		kotebre[0] = bre1;
		kotelun[0] = lun1;
		kotedin[0] = din1; 
		fryfry[0] = fry1;
		noodle[0] = noo1;
		hao[0]=hao1;
		grace[0] = grace1;
		mix[0] = mix1;
			
	}
	
	public void setPrice(String bre2, String lun2, String din2, String fry2, String noo2, String hao2, String grace2, String mix2){
		kotebre[1] = bre2;
		kotelun[1] = lun2;
		kotedin[1] = din2; 
		fryfry[1] = fry2;
		noodle[1] = noo2;
		hao[1]=hao2;
		grace[1] = grace2;
		mix[1] = mix2;
			
	}
	
	public static String getDate(){
		return date;
	}
	
	public void setDate(String date){
		Haksik.date = date;
	}

	public String[] getKotebre(){
		return kotebre;
	}

	public void setKotebre(String[] kotebre){
		this.kotebre = kotebre;
	}

	public String[] getKotelun(){
		return kotelun;
	}

	public void setKotelun(String[] kotelun){
		this.kotelun = kotelun;
	}

	public String[] getKotedin(){
		return kotedin;
	}
	
	public void setKotedin(String[] kotedin){
		this.kotedin = kotedin;
	}
	
	public String[] getFryfry(){
		return fryfry;
	}
	
	public void setFryfry(String[] fryfry){
		this.fryfry = fryfry;
	}
	
	public String[] getNoodle(){
		return noodle;
	}
	
	public void setNoodle(String[] noodle){
		this.noodle = noodle;
	}
	
	public String[] getHao(){
		return hao;
	}
	
	public void setHao(String[] hao){
		this.hao = hao;
	}
	
	public String[] getGrace(){
		return grace;
	}
	
	public void setGrace(String[] grace){
		this.grace = grace;
	}
	
	public String[] getMix(){
		return mix;
	}
	
	public void setMix(String[] mix){
		this.mix = mix;
	}
	
	
}
