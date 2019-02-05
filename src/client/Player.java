package client;

/** Represents a player
 *@author David Tang
 */

import java.util.Scanner;

abstract public class Player {
    protected String name;
    protected Board board;
    protected Player opponent;
    protected char mark;

    /** Creates a player with specified inputs
     *
     * @param name A string containing name
     * @param mark A char containing mark
     */
    public Player (String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    public Player(){}

    /** A method that helps run and play the game.
     * Calls on methods to determine who wins, if the board is full and for players to make a move.
     */
    //runs until win condition is met
    public void play() {
        if(board.xWins() == false && board.oWins() == false && board.isFull() == false){
            makeMove();
            board.display();
        }
        else if(board.xWins()) {
            System.out.println(opponent.name + " is the winner!");
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

    /** A method that asks for player to make move
     *
     */
    public abstract void makeMove();


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
