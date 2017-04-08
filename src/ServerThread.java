import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	
	Socket clientSocket;
	
	public ServerThread(Socket s) {
		this.clientSocket = s;
	}

	@Override
	public void run() {
		

		boolean responseFlag = false;
		
		try {
		
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream() ) );
		    System.out.println("Accepting a new connection from: " +
		    		clientSocket.getInetAddress().getHostAddress() + 
		    		":"+clientSocket.getPort()
		    );
		    
		    // retrieve and link commands from request message
		    int selection = Integer.parseInt(in.readLine());
		    String command = MenuSystem.COMMANDLIST[selection - 1];
		    
		    Process p = Runtime.getRuntime().exec( command );
		    System.out.println("Executing command " + selection);
		    BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream() ) );
		    String s = null;
		    while((s = stdOut.readLine()) != null){
		    	
		    	if(!responseFlag){
		    		System.out.println("Sending response");
		    		responseFlag = true;
		    	}
		    	out.println(s);
		    }
		    
		    out.println(ServerMain.END_OF_MESSAGE_STRING);
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
