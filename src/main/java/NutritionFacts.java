/**
 * 多个构造器参数时可考虑使用构建器
 * @author banhujiu
 * @date 2017/2/28 0028 18:36
 */
public class NutritionFacts {
	private final int servingSize;

	private final int servings;

	private final int calories;

	private final int fat;


	//构建器
	public static final class Builder {
		private int servingSize;
		private int servings;
		private int calories;
		private int fat;

		private Builder() {
		}

		public static Builder aNutritionFacts() {
			return new Builder();
		}

		public Builder withServingSize(int servingSize) {
			this.servingSize = servingSize;
			return this;
		}

		public Builder withServings(int servings) {
			this.servings = servings;
			return this;
		}

		public Builder withCalories(int calories) {
			this.calories = calories;
			return this;
		}

		public Builder withFat(int fat) {
			this.fat = fat;
			return this;
		}

		public Builder but() {
			return aNutritionFacts().withServingSize(servingSize).withServings(servings).withCalories(calories).withFat(fat);
		}

		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	private NutritionFacts(Builder builder){
		servings = builder.servings;
		calories = builder.calories;
		servingSize = builder.servingSize;
		fat = builder.fat;
	}

	@Override
	public String toString() {
		return "NutritionFacts{" +
				"servingSize=" + servingSize +
				", servings=" + servings +
				", calories=" + calories +
				", fat=" + fat +
				'}';
	}

	public static void main(String[] args) {
		NutritionFacts nutrition = new NutritionFacts.Builder().withCalories(12).withCalories(13).build();
		System.out.print(nutrition);
	}
}
