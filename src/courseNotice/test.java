package courseNotice;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SemesterParser semester_p = new SemesterParser();
		String semester = semester_p.getSemester("dudals5018", "znfksh");
		System.out.println(semester);
	}

}
