package server; /**
 * server.Server is a server class that is used to implement a client-server program
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    PrintWriter out;
    Socket aSocket;
    ServerSocket serverSocket;
    BufferedReader in;

    /**
     * Initializes the server.Server with Port 8099
     */
    public Server() { // throws IOException {
        try {
            serverSocket = new ServerSocket(8099);
        } catch (IOException e) {
            System.out.println("Create the new socket error");
        }
        System.out.println("server.Server is running");
    }

    /**
     * Checks to see if the word the client entered is a palindrome
     */
    public void Palindrome() {

        String line = null;
        String check;
        while (true) {
            try {
                line = in.readLine();
                check = line;
                int length = line.length();
                if (line.equals("QUIT")) {
                    line = "Good Bye!";
                    out.println(line);
                    break;
                }

                for (int i = 0; i < (length/2); i++){
                    if (line.charAt(i) != line.charAt(length - i - 1)) {
                        check = line + " is not a palindrome";
                        break;
                    }
                    check = line + " is a palindrome";
                }

                if (length == 1)
                    check = line + " is a palindrome";

                out.println(check);
            } catch (IOException e) {
            }

        }

    }

    /**
     * Runs the server
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        Server myserver = new Server();
        try {

            myserver.aSocket = myserver.serverSocket.accept();
            System.out.println("after accept");
            myserver.in = new BufferedReader(new InputStreamReader(myserver.aSocket.getInputStream()));
            myserver.out = new PrintWriter((myserver.aSocket.getOutputStream()), true);

            myserver.Palindrome();

            myserver.in.close();
            myserver.out.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}