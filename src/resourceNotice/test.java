package resourceNotice;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceContentsManager conManager = new ResourceContentsManager("tmdwo618", "a1032327");
		String contents = conManager.getContents("list.php?Board=KYOM_PDS");
	
		System.out.println(contents);
	}

}
