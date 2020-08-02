import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//Basic Coffee: 3.99
//Cream: .5
//Sugar: .5
//Extra Shot: 1.20
//Wasabi: 1.20
//Soy Sauce: 1.20
class CoffeeDecoratorTest {
	@Test
	void checkWasabi() {
		Coffee order = new Wasabi(new BasicCoffee());
		double cost = order.makeCoffee();
		assertEquals(4.49, cost, "It does not equal: " + 4.49);
	}
	@Test
	void checkExtraShot() {
		Coffee order = new ExtraShot(new BasicCoffee());
		double cost = order.makeCoffee();
		assertEquals(5.19, cost, "It does not equal: " + 5.19);
	}
	@Test
	void checkSoySauce() {
		Coffee order = new SoySauce(new BasicCoffee());
		double cost = order.makeCoffee();
		assertEquals(4.49, cost, "It does not equal: " + 4.49);
	}
	@Test
	void checkCream() {
		Coffee order = new Cream(new BasicCoffee());
		double cost = order.makeCoffee();
		assertEquals(4.49, cost, "It does not equal: " + 4.49);
	}
	@Test
	void checkSugar() {
		Coffee order = new Sugar(new BasicCoffee());
		double cost = order.makeCoffee();
		assertEquals(4.49, cost, "It does not equal: " + 4.49);
	}
	@Test
	void checkTwoAddons() {
		Coffee order = new Cream(new Sugar(new BasicCoffee()));
		double cost = order.makeCoffee();
		assertEquals(4.99, cost, "It does not equal: " + 4.99);
	}
	@Test
	void checkAllAddons() {
		Coffee order = new ExtraShot(new SoySauce(new Wasabi(new Cream(new Sugar(new BasicCoffee())))));
		double cost = order.makeCoffee();
		assertEquals(7.19, cost, "It does not equal: " + 7.19);
	}
	@Test
	void checkDuplicate() {
		Coffee order = new Cream(new Cream(new BasicCoffee()));
		double cost = order.makeCoffee();
		assertEquals(4.99, cost, "It does not equal: " + 4.99);
	}
	@Test
	void checkDelete() {
		Coffee order = new Cream(new Cream(new BasicCoffee()));
		order = new BasicCoffee();
		double cost = order.makeCoffee();
		assertEquals(3.99, cost, "It does not equal: " + 3.99);
	}
	@Test
	void checkDeleteMakeAnother() {
		Coffee order = new Cream(new Cream(new BasicCoffee()));
		order = new BasicCoffee();
		order = new Wasabi(new SoySauce(new BasicCoffee()));
		double cost = order.makeCoffee();
		assertEquals(4.99, cost, "It does not equal: " + 4.99);
	}

}
