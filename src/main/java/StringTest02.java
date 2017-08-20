public class StringTest02 {

	public static void main(String[] args) {
		String a = new StringBuilder("中国").append("很大").toString();
		System.out.println(a.intern() == a);
		String b = new StringBuilder("ja").append("va").toString();
		System.out.println(b.intern() == b);


		String c = "中国" + "很大";
		System.out.println(c.intern() == c);
		String d = "ja" + "va";
		System.out.println(d.intern() == d);
	}

	//result
	/**
	 * true
	 * false
	 */
}