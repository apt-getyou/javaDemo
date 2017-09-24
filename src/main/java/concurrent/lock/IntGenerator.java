package concurrent.lock;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 21:18
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}
