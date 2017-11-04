package calc24;

import calc.utils.CalcRulesEnums;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author banhujiu
 * @date 2017/10/27 0027 14:37
 */
public class Calc24Model<T extends Comparable<T>> {
	private List<T> nums;

	private List<CalcRulesEnums> rulesEnumsList;

	private List<String> formula;

	private Calc24Model() {

	}

	public List<T> getNums() {
		return nums;
	}

	public void setNums(List<T> nums) {
		this.nums = new ArrayList<>();
		this.nums.addAll(nums);
		this.nums.sort(Comparator.naturalOrder());
	}

	public List<String> getFormula() {
		return formula;
	}

	public void setFormula(List<String> formula) {
		this.formula = formula;
	}

	public List<CalcRulesEnums> getRulesEnumsList() {
		return rulesEnumsList;
	}

	public void setRulesEnumsList(List<CalcRulesEnums> rulesEnumsList) {
		this.rulesEnumsList = new ArrayList<>();
		this.rulesEnumsList.addAll(rulesEnumsList);
	}

	public static <T extends Comparable<T>> Calc24Model<T> initByIntegerList(List<T> integers, List<CalcRulesEnums> rulesEnumsList) {
		if (integers == null || rulesEnumsList == null) {
			throw new NullPointerException();
		}
		if (integers.size() != CalcConf.num) {
			throw new RuntimeException("传入数据必须是" + CalcConf.num + "位");
		}
		if (rulesEnumsList.size() != CalcConf.num - 1) {
			throw new RuntimeException("传入运算符必须是" + (CalcConf.num - 1) + "位");
		}
		Calc24Model<T> model = new Calc24Model<>();
		model.setNums(integers);
		model.setRulesEnumsList(rulesEnumsList);
		return model;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Calc24Model<?> model = (Calc24Model<?>) o;

		return nums != null ? nums.equals(model.nums) : model.nums == null;
	}

	@Override
	public int hashCode() {
		return nums != null ? nums.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Calc24Model{" + "nums=" + nums +
				", rulesEnumsList=" + rulesEnumsList +
				", formula=" + formula +
				'}';
	}


}
