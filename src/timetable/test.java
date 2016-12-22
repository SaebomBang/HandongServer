package timetable;

import java.util.Vector;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimetableManager tManager = new TimetableManager("tmdwo618","a1032327");
		Vector<Timetable> tVector = tManager.getData();
		
		for(int i=0; i < tVector.size() ;i++){
			
			Timetable table = (Timetable)tVector.get(i);
	
			System.out.println("<schedule>");
	
			System.out.println("<day>");
			System.out.println("<![CDATA["+table.getDay()+"]]>");
			System.out.println("</day>");
	
			System.out.println("<period>");
			System.out.println("<![CDATA["+table.getPeriod()+"]]>");
			System.out.println("</period>");

			System.out.println("<subject>");
			System.out.println("<![CDATA["+table.getSubject()+"]]>");
			System.out.println("</subject>");

			System.out.println("<place>");
			System.out.println("<![CDATA["+table.getPlace()+"]]>");
			System.out.println("</place>");

			System.out.println("<prof>");
			System.out.println("<![CDATA["+table.getProf()+"]]>");
			System.out.println("</prof>");

			System.out.println("</schedule>");
		}

	
		System.out.println("</timeTable>");
	}

}
