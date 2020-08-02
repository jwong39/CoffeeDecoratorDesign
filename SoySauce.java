public class SoySauce extends CoffeeDecorator {

	private double cost = 0.5;
	
	SoySauce(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addSoy();
	}
	
	private double addSoy() {
//		System.out.println(" + Soy Sauce: $1.20");
		
		return cost;
	}
}