package calc.utils;

/**
 * @author banhujiu
 * @date 2017/11/13 0013 17:15
 */
public class NumberUtils {
	private static final String number = "0123456789";

	public static boolean checkCharIsNumber(Character source){
		if(source == null){
			throw new NullPointerException();
		}
		return number.indexOf(source) != -1;
	}


	public static boolean checkStringIsNumber(String source){
		if (source == null){
			throw new NullPointerException();
		}
		char[] chars = source.toCharArray();
		for (char aChar : chars) {
			if (!NumberUtils.checkCharIsNumber(aChar)) {
				return false;
			}
		}
		return true;
	}
}
