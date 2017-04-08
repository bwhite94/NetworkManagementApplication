
public class MainClient {

	public static void main(String[] args) {
		
		String hostName = "192.168.100.105";
		int portNum = 0;
		int numThreads = 1;
		
		/*if(args.length < 1){
			System.out.println("Please enter a hostname to connect to. Eg: 192.168.100.105");
			System.exit(0);
		}
		if(args.length >= 1){
			hostName = args[0];
		}
		if(args.length >= 2){
			numThreads = Integer.parseInt(args[1]);
		}*/
		
		if(args.length == 3){
			hostName = args[0];
			portNum = Integer.parseInt(args[1]);
			numThreads = Integer.parseInt(args[2]);
		} else if(args.length == 2) {
			hostName = args[0];
			portNum = Integer.parseInt(args[1]);
		} else {
			System.err.println("Try: java MainClient <host name> <port number> <optional number of threads>");
			System.exit(1);
		}
		
		while(true){
		
			MenuSystem menu = new MenuSystem();
			ClientThread[] clients = new ClientThread[numThreads];
			Thread[] threads = new Thread[numThreads];
			
			int selection = menu.imposeMenu();
			
			if(selection == 7)
				System.exit(0);
			
			for(int i = 0; i < clients.length; i++){
				//TODO replace IP and port with runtime arguments
				clients[i] = new ClientThread(hostName, portNum, selection);
				threads[i] = new Thread(clients[i]);
				threads[i].start();
			}
			
			// await threads terminate to restart selection loop
			try{
				for(int i = 0; i < threads.length; i++)
						threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
