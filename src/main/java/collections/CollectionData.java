package collections;

import java.util.ArrayList;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 20:26
 */
public class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> generator, int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(generator.next());
		}
	}

	public static <T> CollectionData<T> list(Generator<T> generator, int quantity) {
		return new CollectionData<>(generator, quantity);
	}
}
