/** Represents the referee for the game
 * @author David Tang
 */
public class Referee {
    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    /** Runs the game by setting opponents, displaying the board and letting X go first
     *
     */
    public void runTheGame(){
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        while (!board.xWins() && !board.oWins() && !board.isFull()) {
            xPlayer.play();
            if (board.xWins() || board.oWins() || board.isFull())
                break;
            xPlayer.getOpponent().play();
        }
    }

    /** Sets the board
     *
     * @param board A board containing board
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /** Sets the 'O' player
     *
     * @param oPlayer A player representing the 'O' player
     */
    public void setoPlayer(Player oPlayer){
        this.oPlayer = oPlayer;
    }

    /** Sets the 'X' player
     *
     * @param xPlayer A player representing the 'X' player
     */
    public void setxPlayer(Player xPlayer){
        this.xPlayer = xPlayer;
    }
}
