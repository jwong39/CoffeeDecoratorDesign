import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CoffeeShop extends Application {
	BorderPane pane;
	Button startBtn, deleteBtn, displayBtn, creamBtn, 
			extraBtn, sugarBtn, wasabiBtn, soyBtn;
	VBox menuButtons;
	HBox addonButtons;
	TextArea myList;
	OrderCoffee orderCoffee = new OrderCoffee();
	DecimalFormat df = new DecimalFormat("###.##"); // Only want 2 decimal places.
	int choice;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Thtarbuckth!");
		pane = new BorderPane();
		pane.setStyle("-fx-background-color: green;");
		
		// Declare Menu Buttons
		startBtn = new Button("Start Order");
		startBtn.setDisable(false);
		deleteBtn = new Button("Delete Order");
		deleteBtn.setDisable(true); // Cant delete if I havent started
		displayBtn = new Button("Display Order");
		displayBtn.setDisable(true); // Cant display if I havent started
		
		// Declare Addon Buttons
		creamBtn = new Button("Cream");
		extraBtn = new Button("Extra Shot");
		sugarBtn = new Button("Sugar");
		wasabiBtn = new Button("Wasabi");
		soyBtn = new Button("Soy Sauce");
		// default all my buttons to disabled with helper
		switchBool(true);
	
		// Declare myList
		myList = new TextArea();
		myList.setEditable(false);
		pane.setCenter(myList);
		
		// Addon Button Actions
		creamBtn.setOnAction(e->{ // Cream Button
			orderCoffee.add(1);
			myList.appendText(" + Cream: $.50\n");
		});
		sugarBtn.setOnAction(e->{ // Sugar Button
			orderCoffee.add(2);
			myList.appendText(" + Sugar: $.50\n");
		});
		extraBtn.setOnAction(e->{ // Extra Shot Button
			orderCoffee.add(3);
			myList.appendText(" + Extra Shot: $1.20\n");
		});
		wasabiBtn.setOnAction(e->{ // Wasabi Button
			orderCoffee.add(4);
			myList.appendText(" + Wasabi: $.50\n");
		});
		soyBtn.setOnAction(e->{ // Soy Button
			orderCoffee.add(5);
			myList.appendText(" + Soy Sauce: $.50\n");
		});
		
		// Start Button Action
		startBtn.setOnAction(e->{
			orderCoffee = new OrderCoffee();
			deleteBtn.setDisable(false);
			displayBtn.setDisable(false);
			
			// I can't press start again until I press delete
			startBtn.setDisable(true); 
			
			// When I start the order I can now display my order
			displayBtn.setDisable(false);
			
			// Turn on all the addon buttons once Start is pressed
			switchBool(false);
			myList.appendText("Black Coffee: $3.99\n");
//			System.out.println("Black Coffee: $3.99\n");
			
		});
		
		// Display Button Action
		displayBtn.setOnAction(e->{
			double total = orderCoffee.makeOrder();
			
			// I can only get the total once
			displayBtn.setDisable(true);
			
			// Disable all buttons so user doesn't add more
			switchBool(true);
			myList.appendText("Total: " + df.format(total));
//			System.out.println("Total: " + total);
		});
		
		// Delete Button Action
		deleteBtn.setOnAction(e->{
			startBtn.setDisable(false);
			deleteBtn.setDisable(true);
			displayBtn.setDisable(true);
			// Disable all buttons so that they HAVE to press start
			switchBool(true);
			// Clear list
			myList.clear();
		});
		
		// Menu Buttons
		menuButtons = new VBox(5, startBtn, deleteBtn, displayBtn);
		pane.setTop(menuButtons);
		
		// Addon Buttons
		addonButtons = new HBox(5, creamBtn, extraBtn, sugarBtn, soyBtn, wasabiBtn);
		pane.setBottom(addonButtons);
		
		// Scene and PrimaaryStage
		Scene scene = new Scene(pane,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Helper Function: Sets all addon buttons to on or off
	public void switchBool(boolean input) {
		creamBtn.setDisable(input);
		extraBtn.setDisable(input);
		sugarBtn.setDisable(input);
		soyBtn.setDisable(input);
		wasabiBtn.setDisable(input);
	}
	
	// Class that makes the order
	public class OrderCoffee
	{
		private Coffee order;
		
		public OrderCoffee(){
			order = new BasicCoffee();
		}
		// Adds addons
		public void add(int choice) {
			if (choice == 1) {
				order = new Cream(order);
			}
			else if (choice == 2) {
				order = new Sugar(order);
			}
			else if (choice == 3) {
				order = new ExtraShot(order);
			}
			else if (choice == 4) {
				order = new SoySauce(order);
			}
			else if (choice == 5) {
				order = new Wasabi(order);
			}
		}
		
		// Build the order and returns the cost
		public double makeOrder() {
			double cost = order.makeCoffee();
			// round cost to 2 decimal places
			return cost;
		}
		
	}
}
