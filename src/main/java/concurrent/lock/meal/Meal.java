package concurrent.lock.meal;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 15:20
 */
public class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}
