package shatteringstone.sandbox.patterns;

import shatteringstone.sandbox.patterns.NutritionFacts.NFBuilder;

class NutritionFacts {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbs;
	
	@Override
	public String toString() {
		return "NutritionFacts [servingSize=" + servingSize + ", servings="
				+ servings + ", calories=" + calories + ", fat=" + fat
				+ ", sodium=" + sodium + ", carbs=" + carbs + "]";
	}

	/** Use this style of builder to improve telescoping parameters on constructor */
	public static class NFBuilder {
		// Required parameters
		private int servingSize;
		private int servings;
		
		// Optional parameters
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbs = 0;
		
		public NFBuilder ( int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		
		public NFBuilder calories(int val) { calories = val; return this; }
		public NFBuilder fat(int val) { fat = val; return this; }
		public NFBuilder sodium(int val) { sodium = val; return this; }
		public NFBuilder carbs(int val) { carbs = val; return this; }
		
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}
	
	private NutritionFacts(NFBuilder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbs = builder.carbs;
	}
}

public class Builder_Bloch {
	public static void main(String... args) {
		NFBuilder builder = new NFBuilder(3, 4);
		builder.calories(4);
		NutritionFacts facts = builder.build();
		System.out.println(facts);
	}
}
