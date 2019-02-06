import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/** Represents a player
 *@author David Tang
 */

public class Player {

    private String name;
    private Board board;
    private Player opponent;
    private Socket aSocket;
    private char mark;
    private BufferedReader in;
    private PrintWriter out;




    /** Creates a player with specified inputs
     *
     * @param mark A char containing mark
     */
    public Player (Socket s, char mark, Board b){
        aSocket = s;
        this.mark = mark;
        board = b;
        try {
            in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            out = new PrintWriter((aSocket.getOutputStream()), true);
            name = in.readLine();
        }catch (IOException e){
            System.out.println("Error getting input");
        }
    }


    /** A method that helps run and play the game.
     * Calls on methods to determine who wins, if the board is full and for players to make a move.
     */
    //runs until win condition is met
    public void play() {
        if(board.xWins() == false && board.oWins() == false && board.isFull() == false){
            makeMove();
            out.println(board.display());
        }
        else if(board.xWins()) {
            out.println(opponent.name + " is the winner!");
            System.out.println();
            System.exit(0);
        }
        else if(board.oWins()) {
            System.out.println(opponent.name + " is the winner!");
            System.exit(0);
        }
        else if(board.isFull()) {
            System.out.println("The board is full. This game is a draw!");
            System.exit(0);
        }

        opponent.play();
    }

    public void makeMove(){
        int row = 0;
        int column = 0;
        boolean check = false;
        Scanner s = new Scanner(System.in);
        while (!check) {
            out.println("test");
            System.out.println("Make your move! Enter which row you would like to play.");
            row = Integer.parseInt(s.nextLine());
            while (row > 2 || row < 0) {
                System.out.println("Improper row entered. Please try again!");
                System.out.println("Which row would you like to play?");
                row = Integer.parseInt(s.nextLine());
            }

            System.out.println("You have entered row " + Integer.toString(row) + ". " +
                    "Now enter your desired column. ");
            column = Integer.parseInt(s.nextLine());

            while (column > 2 || column < 0) {
                System.out.println("Improper column entered. Please try again!");
                System.out.println("Which column would you like to play?");
                column = Integer.parseInt(s.nextLine());
            }
            if (board.getMark(row, column) == 'X' || board.getMark(row, column) == 'O'){
                System.out.println("Space is already taken! Please try again.");
            }
            else
                check = true;
        }
        board.addMark(row, column, mark);
    }

    /** A method that asks for player to make move
     *
     */


    /** Sets the opponent
     *
     * @param opponent A player containing opponent
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /** Sets the board
     *
     * @param theBoard A board containing theBoard
     */
    public void setBoard(Board theBoard){
        board = theBoard;
    }
}
