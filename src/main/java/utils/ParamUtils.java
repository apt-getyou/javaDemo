package utils;

/**
 * @author 刘博文
 * @date 2017/9/14 0014 14:28
 */
public class ParamUtils {

	public static <T, E extends RuntimeException> T requireNonNull(T t, String msg, Class<E> exceptionClass) {
		if (t == null || (t instanceof String && "".equals(t))) {
			E exception;
			try {
				exception = exceptionClass.getConstructor(String.class).newInstance(msg);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("反射初始化异常");
			}
			throw exception;
		}
		return t;
	}

	public static <T> T requireNonNull(T t, String msg) {
		if (t == null || (t instanceof String && "".equals(t))) {
			throw new RuntimeException(msg);
		}
		return t;
	}

}
