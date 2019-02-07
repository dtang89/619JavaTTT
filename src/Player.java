import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


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
            if (name.contains("Name:")) {
                name = name.replace("Name:","");
                out.println("Welcome " + name);
            }

        }catch (IOException e){
            System.out.println("Error getting input");
        }
    }


    /** A method that helps run and play the game.
     * Calls on methods to determine who wins, if the board is full and for players to make a move.
     */
    //runs until win condition is met
    public void play() {

        if(!board.xWins() && !board.oWins() && !board.isFull()){
            out.println(board.display());
            opponent.out.println(board.display());
            opponent.out.println("Waiting for opponent move");
            makeMove();
        }

        if(board.xWins()) {
            out.println(board.display());
            out.println("X player wins!\n");
            opponent.out.println(board.display());
            opponent.out.println("X player wins!\n");
        }

        else if(board.oWins()) {
            out.println(board.display());
            out.println("O player wins!\n");
            opponent.out.println(board.display());
            opponent.out.println("O player wins!\n");
        }
        else if(board.isFull()) {
            out.println(board.display());
            out.println("The board is full. This game is a draw!\n");
        }

        //opponent.play();
    }

    public void makeMove(){
        String response="";
        int row = 0;
        int column = 0;

        while (true) {
            try {
                out.println("Make your move! Enter which row you would like to play.");
                response = in.readLine();

                if (response.contains("Row:")) {
                    response = response.replace("Row:","");
                    row = Integer.parseInt(response);

//                    while (row > 2 || row < 0) {
//                        out.println("Improper row entered. Please try again!\n" +
//                                "Which row would you like to play");
//                        response = in.readLine();
//
//                        if (response.contains("Row:")) {
//                            response.replace("Row:","");
//                            row = Integer.parseInt(response);
//
//                    }
                    out.println("You have entered row " + row + ". " +
                            "Now enter your desired column. ");
                    response = in.readLine();

                    if (response.contains("Column:")) {
                        response = response.replace("Column:", "");
                        column = Integer.parseInt(response);
                    }

                }


//                while (column > 2 || column < 0) {
//                    out.println("Improper column entered. Please try again!");
//                    out.println("Which column would you like to play?");
//                    column = Integer.parseInt(in.readLine());
//                }

                if (board.getMark(row, column) == 'X' || board.getMark(row, column) == 'O') {
                    out.println("Space is already taken! Please try again.");
                }
                else
                    break;
            }catch(IOException e){
                System.out.println("Error reading move");
            }
        }
        board.addMark(row, column, mark);

    }


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

    public Player getOpponent() {
        return opponent;
    }
}
