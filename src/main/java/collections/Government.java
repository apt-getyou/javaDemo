package collections;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 20:23
 */
public class Government implements Generator<String> {
	String[] foundation = ("ss aa ee ff").split(" ");

	private int index;

	@Override
	public String next() {
		return foundation[index++];
	}

	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>(new CollectionData<>(new Government(), 4));
		set.addAll(CollectionData.list(new Government(), 4));
		System.out.println(set);
	}
}
