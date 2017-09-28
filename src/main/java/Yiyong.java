/**
 * 测试引用
 * @author banhujiu
 * @date 2017/8/27 0027 21:09
 */
public class Yiyong {
	public static void main(String[] args) {
		Integer a = 7;
		Integer b = a;
		a++;
		System.out.println("a:" + a + "\n");
		System.out.println("b:" + b + "\n");


		int c = 9;
		int d = 11;
		d=c;
		c++;
		System.out.println("c:" + c +"\n");
		System.out.println("d:" + d + "\n");
	}
}
