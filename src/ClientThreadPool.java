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
		String[] board;

		while (!line.equals("QUIT")) {
			System.out.println("Please enter a name");
			try {
				line = stdIn.readLine();
				socketOut.println("Name: " +line);
				response = socketIn.readLine();

				if (response.contains("Welcome"))
					System.out.println(response);
			}catch (IOException e){
				System.out.println("Error adding name");
			}

			while (true) {
				try {
					//System.out.println("test2");
					response = socketIn.readLine();

					if (response.contains("|col")){
						System.out.println(response);
						for (int i = 0; i < 13; i++) {
							response = socketIn.readLine();
							System.out.println(response);
						}
					}

					if (response.contains("which row")){
						System.out.println(response);
						line = stdIn.readLine();
						socketOut.println("Row:" + line);
					}

					else if (response.contains("desired column")){
						System.out.println(response);
						line = stdIn.readLine();
						socketOut.println("Column:" + line);
					}

					else if (response.contains("already taken")|| response.contains("Waiting for opponent"))
						System.out.println(response);

					else if (response.contains("wins") || response.contains("draw")){
                        System.out.println(response);
                        System.exit(0);
                    }



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

	public static void main(String[] args) {
		ClientThreadPool aClient = new ClientThreadPool("localhost", 9898);
		aClient.communicate();
	}
}
