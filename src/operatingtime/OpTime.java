package operatingtime;

/**
 * @author YoungKwang Han
 * @email juanaevv@nate.com
 * @classname OperatingTime.java
 * @package HandongServer.src.operatingtime
 * @date 2014. 12. 30. 3:19 pm
 * @purpose : 
 *
 * @comment : 
 * 
 * 
 *
 */

public class OpTime {
	private String name;
	private String runtime;
	private String note;
	private String ver;

	public OpTime(){
		super();
	}

	public OpTime(String name, String runtime, String note, String ver){
		this.name = name;
		this.runtime = runtime;
		this.note = note;
		this.ver = ver;
		
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getRuntime(){
		return runtime;
	}

	public void setRuntime(String runtime){
		this.runtime = runtime;
	}

	public String getNote(){
		return note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getVer(){
		return ver;
	}
	
	public void setVer(String ver){
		this.ver = ver;
	}
}