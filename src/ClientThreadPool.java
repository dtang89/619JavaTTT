import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientThreadPool {
	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	public ClientThreadPool(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);

		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	public void communicate() {
		String line = "";
		String response = "";

		while (!line.equals("QUIT")) {
			System.out.println("Please enter a name");

			while (true) {
				try {
					line = stdIn.readLine();
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);
				} catch (IOException e) {
					System.out.println("Sending error: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("what is going on " + e.getMessage());
					break;
				}
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("closing error: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		ClientThreadPool aClient = new ClientThreadPool("localhost", 9898);
		aClient.communicate();
	}
}
