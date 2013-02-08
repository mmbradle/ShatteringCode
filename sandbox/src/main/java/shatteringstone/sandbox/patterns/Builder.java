package shatteringstone.sandbox.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shatteringstone.sandbox.patterns.Pizza.HawaiianPizzaBuilder;
import shatteringstone.sandbox.patterns.Pizza.PizzaBuilder;
import shatteringstone.sandbox.patterns.Pizza.SpicyPizzaBuilder;

/** "Product" */
class Pizza {
	private final String dough;
	private final String sauce;
	private final String[] toppings;

	private Pizza(PizzaBuilder builder) {
		dough = builder.getDough();
		sauce = builder.getSauce();
		toppings = builder.getToppings();
	}	

	@Override
	public String toString() {
		return "Pizza [dough=" + dough + ", sauce=" + sauce + ", toppings="
				+ Arrays.toString(toppings) + "]";
	}

	/** "Abstract Builder" */
	abstract static class PizzaBuilder {
		private String dough;
		private String sauce;
		private List<String> toppings = new ArrayList<String>();

		public Pizza getPizza() {
			return new Pizza(this); 
		}

		public String getDough() {
			return dough;
		}

		public void setDough(String dough) {
			this.dough = dough;
		}

		public String getSauce() {
			return sauce;
		}

		public void setSauce(String sauce) {
			this.sauce = sauce;
		}

		public String[] getToppings() {
			return toppings.toArray(new String[toppings.size()]);
		}

		public void addTopping(String topping) {
			this.toppings.add(topping);
		}

		public Pizza getResult() {
			return new Pizza(this);
		}

		public abstract void buildDough();
		public abstract void buildSauce();
		public abstract void buildTopping();

	}

	/** "ConcreteBuilder" */
	static class HawaiianPizzaBuilder extends PizzaBuilder {
		public void buildDough()   { 
			setDough("thin"); 
		}
		
		public void buildSauce()   { 
			setSauce("mild"); 
		}
		
		public void buildTopping() { 
			addTopping("ham"); 
			addTopping("pineapple"); 
		}
	}

	/** "ConcreteBuilder" */
	static class SpicyPizzaBuilder extends PizzaBuilder {
		public void buildDough()   { 
			setDough("pan baked"); 
		}
		
		public void buildSauce()   { 
			setSauce("hot"); 
		}

		public void buildTopping() { 
			addTopping("pepperoni"); 
			addTopping("salami"); 
			addTopping("hot peppers"); 
			addTopping("onions"); 
		}
	}
}

/** "Director" */
class PizzaDirector {
	public Pizza constructPizza(PizzaBuilder pizzaBuilder) {
		pizzaBuilder.buildDough();
		pizzaBuilder.buildSauce();
		pizzaBuilder.buildTopping();
		return pizzaBuilder.getResult();
	}
}

/** The client: A customer ordering a pizza. */
public class Builder {
	public static void main(String[] args) {
		PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
		PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();
		PizzaDirector waiter = new PizzaDirector();
		
		Pizza pizza1 = waiter.constructPizza(hawaiianPizzaBuilder);
		Pizza pizza2 = waiter.constructPizza(spicyPizzaBuilder);

		System.out.println("Waiter built pizza: " + pizza1);
		System.out.println("Waiter built pizza: " + pizza2);
	}
}