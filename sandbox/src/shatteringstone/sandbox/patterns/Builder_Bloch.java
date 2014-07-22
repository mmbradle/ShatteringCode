package shatteringstone.sandbox.patterns;

import shatteringstone.sandbox.patterns.NutritionFacts.NutritionFactsBuilder;

class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbs;

    @Override
    public String toString() {
        return "NutritionFacts [servingSize=" + this.servingSize + ", servings=" + this.servings + ", calories="
                + this.calories + ", fat=" + this.fat + ", sodium=" + this.sodium + ", carbs=" + this.carbs + "]";
    }

    /** Use this style of builder to improve telescoping parameters on constructor */
    public static class NutritionFactsBuilder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbs = 0;

        public NutritionFactsBuilder(final int servingSize, final int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public NutritionFactsBuilder calories(final int val) {
            this.calories = val;
            return this;
        }

        public NutritionFactsBuilder fat(final int val) {
            this.fat = val;
            return this;
        }

        public NutritionFactsBuilder sodium(final int val) {
            this.sodium = val;
            return this;
        }

        public NutritionFactsBuilder carbs(final int val) {
            this.carbs = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(final NutritionFactsBuilder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbs = builder.carbs;
    }
}

public class Builder_Bloch {
    public static void main(final String... args) {
        NutritionFacts facts = new NutritionFactsBuilder(3, 4).calories(5).fat(6).build();
        System.out.println(facts);

        NutritionFacts facts2 = new NutritionFactsBuilder(3, 4).fat(600).build();
        System.out.println(facts2);
    }
}
