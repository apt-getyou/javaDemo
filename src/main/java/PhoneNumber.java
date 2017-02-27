import java.util.HashMap;
import java.util.Map;

/**
 * 覆盖equals时总要覆盖hashCode
 *
 * @author 刘博文
 * @date 2017/2/27 0027 14:20
 */
public final class PhoneNumber {
	private final short areaCode;

	private final short prefix;

	private final short lineNumber;

	private final boolean aBoolean;

	private final long aLong;

	public PhoneNumber(int areaCode, int prefix, int lineNumber, boolean aBoolean, long aLong) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line number");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
		this.aBoolean = aBoolean;
		this.aLong = aLong;
	}

	private static void rangeCheck(int arg, int max, String name) {
		if (arg < 0 || arg > max) {
			throw new IllegalArgumentException(name + ":" + arg);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PhoneNumber that = (PhoneNumber) o;

		return areaCode == that.areaCode
				&& prefix == that.prefix
				&& lineNumber == that.lineNumber
				&& aBoolean == that.aBoolean
				&& aLong == that.aLong;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (int) areaCode;
		result = 31 * result + (int) prefix;
		result = 31 * result + (int) lineNumber;
		result = 31 * result + (aBoolean ? 1 : 0);
		result = 31 * result + (int) (aLong ^ (aLong >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "PhoneNumber{" +
				"areaCode=" + areaCode +
				", prefix=" + prefix +
				", lineNumber=" + lineNumber +
				", aBoolean=" + aBoolean +
				", aLong=" + aLong +
				'}';
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> map = new HashMap<>();
		map.put(new PhoneNumber(707, 867, 5309, false, 1L), "Jenny");

		String name = map.get(new PhoneNumber(707, 867, 5309, false, 1L));
		if (name == null) {
			System.out.print("null\n");//若不覆盖hashCode方法，则输出此行
		} else {
			System.out.print(name + "\n");
		}

		PhoneNumber pn = new PhoneNumber(707, 867, 5309, false, 1L);
		System.out.print(pn.toString() + "\n");
	}
}
