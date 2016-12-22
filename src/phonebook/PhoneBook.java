package phonebook;

/**
 * @author YoungKwang Han
 * @email juanaevv@nate.com
 * @classname PhoneBook.java
 * @package HandongServer.src.phonebook
 * @date 2014. 12. 29. 7:04 pm
 * @purpose : 
 *
 * @comment : 
 * 
 * 
 *
 */

public class PhoneBook {
	private String nameKor;
	private String number;
	private String cat;
	private String ver;

	public PhoneBook(){
		super();
	}

	public PhoneBook(String nameKor, String number, String cat, String ver){
		this.nameKor = nameKor;
		this.number = number;
		this.cat = cat; //건물별로 구분
		this.ver = ver;
		
	}

	public String getNameKor(){
		return nameKor;
	}

	public void setNameKor(String nameKor){
		this.nameKor = nameKor;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getCat(){
		return cat;
	}
	
	public void setCat(String cat){
		this.cat = cat;
	}
	
	public String getVer(){
		return ver;
	}
	
	public void setVer(String ver){
		this.ver = ver;
	}
}
