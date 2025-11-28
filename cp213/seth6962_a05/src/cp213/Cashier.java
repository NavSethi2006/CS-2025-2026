package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-09-07
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");

	// your code here
	boolean loop = true;
	
	Order order = new Order();
	MenuItem item;

	
	Scanner scan = new Scanner(System.in);
	String menustr = "Welcome to WLU Foodorama!\r\n"
			+ "\r\n"
			+ "Menu:\r\n"
			+ " 1) hot dog      $ 1.25\r\n"
			+ " 2) hamburger    $ 2.00\r\n"
			+ " 3) cheeseburger $ 2.75\r\n"
			+ " 4) fries        $ 1.75\r\n"
			+ " 5) poutine      $ 3.75\r\n"
			+ " 6) pizza        $10.00\r\n"
			+ " 7) drink        $ 1.50\r\n"
			+ "Press 0 when done.\r\n"
			+ "Press any other key to see the menu again.";
	
	System.out.println(menustr);
	int command;
	int amount;
	while(loop) {
		item = null;
		System.out.print("Command");
		try {
		 	command = scan.nextInt();
		 	System.out.print("How many do you want?");
			amount = scan.nextInt();
		} catch(Exception e) {
			System.out.println("Not a vaild Number");
			continue;
		}
		
		
		
		switch(command) {
		case 0:
			loop = false;
			break;
		case 1:
			item = new MenuItem("hot dog", 1.25);
			break;
		case 2:
			item = new MenuItem("hamburger", 2.00);
			break;
		case 3:
			item = new MenuItem("cheeseburger", 2.75);
			break;
		case 4:
			item = new MenuItem("fries", 1.75);
			break;
		case 5:
			item = new MenuItem("poutine", 3.75);
			break;
		case 6:
			item = new MenuItem("pizza", 10.00);
			break;
		case 7:
			item = new MenuItem("drink", 1.50);
			break;
		default:
			System.out.println(menustr);
			continue;
		}
		order.add(item, amount);
		
	}
	
	System.out.println("Receipt");
	
	System.out.println(order.toString());

	return order;
    }
}