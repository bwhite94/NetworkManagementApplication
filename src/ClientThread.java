import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable {
	
	/**
	 * holds the measured time for the server response
	 */
	private long measuredTime = 0;
	public String hostName = "";
	public int portNumber = 0;
	int commandSelection = -1;
	
	// constructor with hostName & portNumber
	ClientThread(String host, int port, int selection){
		this.hostName = host;
		this.portNumber = port;
		this.commandSelection = selection;
	}

	@Override
	public void run() {
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		// 1. connect
		// 2. measure time
		// 3. response
		
		// CONNECT, measure time, response
		try {
			clientSocket = new Socket(hostName, portNumber);
		    out = new PrintWriter(clientSocket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		// connect, MEASURE TIME, response
		long startTime = System.currentTimeMillis();

		// command sent
		out.println(this.commandSelection);
		
		// connect, measure time, RESPONSE
		try {
			String r = null;
			while(!(r = in.readLine()).equals(ServerMain.END_OF_MESSAGE_STRING)
				&& r != null){
				System.out.println(r);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		measuredTime = (endTime - startTime);
		System.out.println("START: "+startTime+", END: "+endTime+", MEASURED TIME: " + measuredTime);
		
		// close connection
		try {
			if(clientSocket != null)
				clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
