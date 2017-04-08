import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSystem {
	
	private Scanner s = new Scanner(System.in);
	
	/**
	 * holds the list of commands based on the menu system; index = (selection - 1)
	 */
	public static final String[] COMMANDLIST = {
			"date",	// current date/time
			"uptime", // uptime
			"free", // memusage
			"netstat", // netstat
			"who", // current users
			"ps -aux"	// running processes
	};
	
	public final String ERROR_MESSAGE = "Sorry, input must be 1-7. Please try again.";
	
	/**
	 * returns an integer value based on the selection the user entered (1-7)
	 * @return 1-7; (1-6 host statistic selection); (7 -> quit)
	 */
	public int imposeMenu(){
		
		int selection = 0;
		
		// loop menu until valid value is selected
		do{
			String menuString = String.format("%n%nWhat would you like to poll the server with?%n" +
					"1. Host's current Date and Time%n" +
					"2. Host's uptime%n" +
					"3. Host's memory usage%n" +
					"4. Host's Netstat%n" +
					"5. Host's current users%n" +
					"6. Host's running processes%n" +
					"7. Quit");
			System.out.println(menuString);
			
			try{
				selection = s.nextInt();
			
				if(selection < 8
					&& selection > 0){
					return selection;
				}else{
					System.out.println(this.ERROR_MESSAGE);
				}
				
			}catch(InputMismatchException e){
				System.out.println(this.ERROR_MESSAGE);
				selection = 0;
				s.nextLine();
			}
			
		}while(true);
		
		
	}

}
