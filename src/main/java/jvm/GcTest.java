package jvm;

/**
 * @author banhujiu
 * @date 2017/9/3 0003 20:00
 */
@SuppressWarnings("ALL")
public class GcTest {
	private static final int _1MB = 1024 * 1024;


	/**
	 * VM Args ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	public static void testAllocation() {

		byte[] allocation1, allocation2, allocation3, allocation4;

		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB];
	}

	/**
	 * VM Args ： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 *  		-XX:PretenureSizeThreshold=314578
	 */
	public static void testPretenureSizeThreshold() {
		byte[] allocation = new byte[4 * _1MB]; // 直接分配在老年代
	}

	public static void main(String[] args) {
		testAllocation();
		testPretenureSizeThreshold();
	}

}
