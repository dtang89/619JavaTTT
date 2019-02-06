//Game.java
import java.io.*;
import java.net.Socket;

public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;
	private Socket xSocket;
	private Socket oSocket;

	private Player xPlayer;
	private Player oPlayer;

	private PrintWriter out;
	private BufferedReader in;
	
	/**
	 * creates a board for the game
	 */
    public Game(Socket x, Socket o) {
        xSocket = x;
        oSocket = o;
        theBoard  = new Board();
        theRef = new Referee();
	}
    
    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game 
     * @throws IOException
     */
    public void appointReferee(Referee r) {
        theRef = r;
    	theRef.runTheGame();
    }


	@Override
	public void run() {
		xPlayer = new Player(xSocket, 'X', theBoard);
		oPlayer = new Player(oSocket, 'O', theBoard);
		theRef.setxPlayer(xPlayer);
		theRef.setoPlayer(oPlayer);
		appointReferee(theRef);
		xPlayer.play();
		
		// TODO Auto-generated method stub
		String line = null;
		while (true) {
			try {
				line = in.readLine();
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					out.println(line);
					break;
				}
				line = line.toUpperCase();
				out.println(line);
			} catch (IOException e) {
			}

		}

	}
	//public static void main(String[] args) throws IOException {
//
//		Referee theRef;
//		Player xPlayer, oPlayer;
//		BufferedReader stdin;
//		Game theGame = new Game();
//		stdin = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("\nPlease enter the name of the \'X\' player: ");
//		String name= stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}
//
//		xPlayer = create_player (name, LETTER_X, theGame.theBoard, stdin);
//
//		System.out.print("\nPlease enter the name of the \'O\' player: ");
//		name = stdin.readLine();
//		while (name == null) {
//			System.out.print("Please try again: ");
//			name = stdin.readLine();
//		}
//
//		oPlayer = create_player (name, LETTER_O, theGame.theBoard, stdin);
//
//		theRef = new Referee();
//		theRef.setBoard(theGame.theBoard);
//		theRef.setoPlayer(oPlayer);
//		theRef.setxPlayer(xPlayer);
//
//        theGame.appointReferee(theRef);
//	}
	/**
	 * Creates the specified type of player indicated by the user. 
	 * 
	 * @param name player's name
	 * @param mark player's mark (X or O)
	 * @param board refers to the game board
	 * @param stdin refers to an input stream
	 * @return a newly created player
	 * @throws IOException
	 */
//	static public Player  create_player(String name, char mark, Board board,
//			BufferedReader stdin)throws IOException {
//		// Get the player type.
//		final int NUMBER_OF_TYPES = 4;
//		System.out.print ( "\nWhat type of player is " + name + "?\n");
//		System.out.print("  1: Human\n" + "  2: Random Player\n"
//		+ "  3: Blocking Player\n" + "  4: Smart Player\n");
//		System.out.print( "Please enter a number in the range 1-" + NUMBER_OF_TYPES + ": ");
//		int player_type = 0;
//
//		String input;
//		stdin = new BufferedReader(new InputStreamReader(System.in));
//		input= stdin.readLine();
//		player_type = Integer.parseInt(input);
//		while (player_type < 1 || player_type > NUMBER_OF_TYPES) {
//			System.out.print( "Please try again.\n");
//			System.out.print ( "Enter a number in the range 1-" +NUMBER_OF_TYPES + ": ");
//			input= stdin.readLine();
//			player_type = Integer.parseInt(input);
//		}
//
//		// Create a specific type of Player
//		Player result = null;
//		switch(player_type) {
//			case 1:
//				result = new Player(name, mark);
//				break;
//			case 2:
//				result = new RandomPlayer(name, mark);
//				break;
//			case 3:
//				result = new BlockingPlayer(name, mark);
//				break;
//			//case 4:
//				//result = new SmartPlayer(name, mark);
//				//break;
//			default:
//				System.out.print ( "\nDefault case in switch should not be reached.\n"
//				+ "  Program terminated.\n");
//				System.exit(0);
//		}
//		result.setBoard(board);
//		return result;
//	}


}
