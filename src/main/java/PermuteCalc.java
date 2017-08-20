/**
 * @author 刘博文
 * @date 2017/6/3 0003 16:33
 */
public class PermuteCalc {
	private static void permute(String str) {
		if (str == null) {
			return;
		}
		char[] strArray = str.toCharArray();
		PermuteCalc.permute(strArray, 0, strArray.length - 1);
	}

	private static void permute(char[] chars, int low, int high) {
		int i;
		if (low == high) {
			StringBuilder cout = new StringBuilder();
			for (i = 0; i <= high; i++) {
				cout.append(chars[i]);
			}
			System.out.println(cout);
		} else {
			for (i = low; i <= high; i++) {
				char temp = chars[low];
				chars[low] = chars[i];
				chars[i] = temp;
				permute(chars, low + 1, high);
				temp = chars[low];
				chars[low] = chars[i];
				chars[i] = temp;
			}
		}
	}

	public static void main(String[] args) {
		String test = "abc";
		PermuteCalc.permute(test);
	}
}
