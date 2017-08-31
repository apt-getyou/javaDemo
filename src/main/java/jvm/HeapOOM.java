package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘博文
 * @date 2017/8/31 0031 22:59
 * -Xms20m -Xmx20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	static class OOMObject{

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true){
			list.add(new OOMObject());
		}
	}
}
