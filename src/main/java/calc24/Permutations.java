package calc24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
	public <E extends Comparable<E>> List<List<E>> permute(List<E> list) {
		Set<List<E>> resultSet = new HashSet<>();
		//start from an empty list
		resultSet.add(new ArrayList<>());
		for (E aNum : list) {
			//list of list in current iteration of the array list
			List<List<E>> current = new ArrayList<>();
			for (List<E> l : resultSet) {
				addIndex(aNum, current, l);
			}
			resultSet = new HashSet<>(current);
		}
		List<List<E>> result = new ArrayList<>(resultSet);
		sortResult(result);
		return result;
	}

	/**
	 * 可重排列
	 */
	public <E extends Comparable<E>> List<List<E>> permutationWithRepetition(List<E> list, int size) {
		Set<List<E>> resultSet = new HashSet<>();
		resultSet.add(new ArrayList<>());
		for (int i = 0; i < size; i++) {
			//list of list in current iteration of the array list
			List<List<E>> current = new ArrayList<>();
			for (List<E> l : resultSet) {
				for (E aNum : list) {
					addIndex(aNum, current, l);
				}
			}
			resultSet = new HashSet<>(current);
		}
		List<List<E>> result = new ArrayList<>(resultSet);
		sortResult(result);
		return result;
	}

	private <E extends Comparable<E>> void sortResult(List<List<E>> result) {
		result.sort((o1, o2) -> {
			int size = Math.min(o1.size(), o2.size());
			for (int i = 0; i < size; i++) {
				if (o1.get(i).compareTo(o2.get(i)) == 0) {
					continue;
				}
				return o1.get(i).compareTo(o2.get(i));
			}
			return 0;
		});
	}

	private <E extends Comparable<E>> void addIndex(E aNum, List<List<E>> current, List<E> l) {
		// # of locations to insert is largest index + 1
		for (int j = 0; j < l.size() + 1; j++) {
			// + add list[i] to different locations
			// 添加数据，有个位置的概念，特殊位置之后的数据，依次往后移动。
			l.add(j, aNum);
			List<E> temp = new ArrayList<>(l);
			current.add(temp);
			// - remove list[i] add
			l.remove(j);
		}
	}
}
