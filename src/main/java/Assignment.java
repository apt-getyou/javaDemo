import model.Tank;

/**
 * @author banhujiu
 * @date 2017/8/27 0027 21:21
 */
public class Assignment {

	/**
	 * Tank类成员level允许直接修改
	 */
	public static void main(String[] args) {
		Tank t1 = new Tank();
		Tank t2 = new Tank();
		t1.level = 9;
		t2.level = 47;

		System.out.print(t1.level + "========" + t2.level + "\n");

		t1 = t2;

		System.out.print(t1.level + "========" + t2.level + "\n");

		t1.level = 27;

		System.out.print(t1.level + "=======" + t2.level + "\n");
	}

}
