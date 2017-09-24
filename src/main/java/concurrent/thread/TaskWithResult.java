package concurrent.thread;

import java.util.concurrent.Callable;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 16:46
 */
public class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}
}
