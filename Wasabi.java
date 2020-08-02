
public class Wasabi extends CoffeeDecorator {

	private double cost = 0.5;
	
	Wasabi(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addWasabi();
	}
	
	private double addWasabi() {
//		System.out.println(" + Wasabi: $1.20");
		
		return cost;
	}
}