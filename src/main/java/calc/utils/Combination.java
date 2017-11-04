package calc.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author banhujiu
 * @date 2017/11/4 0004 19:00
 */
public class Combination {
	public static <E extends Comparable<E>> List<List<E>> combination(List<E> list) {
		List<List<E>> permute = Permutations.permute(list);
		Set<List<E>> result = new LinkedHashSet<>();
		for (List<E> es : permute) {
			es.sort(Comparator.naturalOrder());
			result.add(es);
		}
		return new ArrayList<>(result);
	}

	/**
	 * 可重组合
	 */
	public static <E extends Comparable<E>> List<List<E>> combinationWithRepetition(List<E> list, int size) {
		List<List<E>> permute = Permutations.permutationWithRepetition(list, size);
		Set<List<E>> resultLSet = new LinkedHashSet<>();
		for (List<E> es : permute) {
			es.sort(Comparator.naturalOrder());
			resultLSet.add(es);
		}
		return new ArrayList<>(resultLSet);
	}
}
