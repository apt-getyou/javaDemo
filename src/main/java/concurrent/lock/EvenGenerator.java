package concurrent.lock;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 21:30
 */
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;//递增不具有原子性
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
