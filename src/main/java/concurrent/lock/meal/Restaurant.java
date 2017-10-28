package concurrent.lock.meal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 15:24
 */
public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}
