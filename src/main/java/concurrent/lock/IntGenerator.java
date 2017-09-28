package concurrent.lock;

/**
 * @author banhujiu
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
