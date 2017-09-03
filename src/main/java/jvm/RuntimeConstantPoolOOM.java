package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘博文
 * @date 2017/9/2 0002 15:34
 * VM Args : -XX:PermSize=1M -XX:MaxPermSize=1M
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		// jdk 1.6 false
		// jdk 1.7 true

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		// jdk 1.6 false
		// jdk 1.7 false

		//出现上面输出的原因是 jdk1.6时，inter方法会新

		List<String> list = new ArrayList<>();
		int i = 1;
		while (true){
			String intern = String.valueOf(++i).intern();
			System.out.println(intern);
			list.add(intern);
		}
		/*
		使用jdk1.6及之前的版本，因为常量池分配在永久代内，所以以上代码会导致内存溢出（方法区内存）
		jdk1.7及以后的版本，运行上面的代码就不会得到相同的结果，循环会一直进行下去，常量池里只会记录
		对象的引用，对象还是存放在java堆里
		 */
	}
}
