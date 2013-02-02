package shatteringstone.sandbox.patterns;

//The Coffee Interface defines the functionality of Coffee implemented by decorator
interface Coffee {
	public double getCost(); // returns the cost of the coffee
	public String getIngredients(); // returns the ingredients of the coffee
}

//implementation of a simple coffee without any extra ingredients
class SimpleCoffee implements Coffee {
	public double getCost() {
		return 1;
	}

	public String getIngredients() {
		return "Coffee";
	}
}

//abstract decorator class - note that it implements Coffee interface
abstract class CoffeeDecorator implements Coffee {
	protected final Coffee decoratedCoffee;
	protected String ingredientSeparator = ", ";

	public CoffeeDecorator(Coffee decoratedCoffee) {
		this.decoratedCoffee = decoratedCoffee;
	}

	public double getCost() { // implementing methods of the interface
		return decoratedCoffee.getCost();
	}

	public String getIngredients() {
		return decoratedCoffee.getIngredients();
	}
}

//Decorator Milk that mixes milk with coffee
//note it extends CoffeeDecorator
class Milk extends CoffeeDecorator {
	public Milk(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	public double getCost() { // overriding methods defined in the abstract superclass
		return super.getCost() + 0.5;
	}

	public String getIngredients() {
		return super.getIngredients() + ingredientSeparator + "Milk";
	}
}

//Decorator Whip that mixes whip with coffee
//note it extends CoffeeDecorator
class Whip extends CoffeeDecorator {
	public Whip(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	public double getCost() {
		return super.getCost() + 0.7;
	}

	public String getIngredients() {
		return super.getIngredients() + ingredientSeparator + "Whip";
	}
}

//Decorator Sprinkles that mixes sprinkles with coffee
//note it extends CoffeeDecorator
class Sprinkles extends CoffeeDecorator {
	public Sprinkles(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	public double getCost() {
		return super.getCost() + 0.2;
	}

	public String getIngredients() {
		return super.getIngredients() + ingredientSeparator + "Sprinkles";
	}
}

public class Decorator2 {
	public static final void main(String[] args) {
		Coffee c = new SimpleCoffee();
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());

		c = new Milk(c);
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());

		c = new Sprinkles(c);
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());

		c = new Whip(c);
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());

		// Note that you can also stack more than one decorator of the same type
		c = new Sprinkles(c);
		System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
	}

}