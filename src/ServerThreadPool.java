import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerThreadPool {
    PrintWriter out;
    Socket xSocket;
    Socket oSocket;
    ServerSocket serverSocket;
    BufferedReader in;
    /**
     * Thread Pool to Handle Communication.
     */
    private ExecutorService pool;

    public ServerThreadPool() { // throws IOException {
        try {
            serverSocket = new ServerSocket(9898);
            pool = Executors.newFixedThreadPool(2);

        } catch (IOException e) {
            System.out.println("Create new socket error");
            System.out.println(e.getMessage());
        }
        System.out.println("Server is running");
    }

    public void runServer() {
        try {
            while (true) {
                xSocket = serverSocket.accept();
                System.out.println("X Player Connected");
                oSocket = serverSocket.accept();
                System.out.println("O Player Connected");
                Game gam1 = new Game(xSocket, oSocket);
                pool.execute(gam1);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Stop accepting new games and finish any active ones, then shutdown the threadpool.
            pool.shutdown();
            try {
                in.close();
                out.close();
                xSocket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerThreadPool myserver = new ServerThreadPool();
        myserver.runServer();
    }

}