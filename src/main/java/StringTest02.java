public class StringTest02 {
	
	public static void main(String[] args) {
		String a = new StringBuilder("中国").append("很大").toString();
		System.out.println(a.intern() == a);
		String b = new StringBuilder("ja").append("va").toString();
		System.out.println(b.intern() == b);
	}

	//result
	/**
	 * true
	 * false
	 */
}