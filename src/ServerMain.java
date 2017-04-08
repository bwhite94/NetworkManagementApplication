import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	
	public static final String END_OF_MESSAGE_STRING = "47fd3beb-ce0d-44b2-b329-dfecee40bfc5";

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.err.println("Try: java ServerMain <port number>");
			System.exit(1);
		}
		
		int portNum = Integer.parseInt(args[0]);
		
		System.out.println("Server started -- bound to port number " + portNum);
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(portNum);
			boolean responseFlag = false;
			while(true){
				
			    Socket clientSocket = serverSocket.accept();
			    new Thread(new ServerThread(clientSocket)).start();
			    
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
