package server;

/** Represents a board
*@author David Tang
 *@author 00502532
 */
//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 


public class Board implements Constants {


	//Implements the elements from interface constant to class board
	private char theBoard[][];
	private int markCount;
	//Member variables

	/** Creates a default board
	 *
 	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}//Default constructor for board. Creates the a 3x3 board of blank spaces

	/** Gets the mark from the board
	 *
	 * @param row An int containing row
	 * @param col An int containing column
	 * @return A char representing X, O or blank square
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/** Boolean check if the board is full
	 *
	 * @return An int representing total mark count
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/** Boolean check if X Wins
	 *
	 * @return A boolean representing X win or loss
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	} //Boolean to check if x wins

	/** Bolean check if O Wins
	 *
	 * @return A boolean representing O win or loss
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	} //Boolean to check if O wins


	/** Adds a mark based on inputs
	 *
	 * @param row An int containing row
	 * @param col An int containing column
	 * @param mark A char containing X or O
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
	//Adds a mark at row and column

	/** Clears the board and populates with spaces
	 *
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	} //Clears the board by adding blank spaces to entire board

	/** Checks for tic tac toe win condition
	 *
	 * @param mark A char containing X or O
	 * @return An int representing 1 for win or 0 for loss
	 */
	int checkWinner(char mark) { //this whole method checks the board to determine the winner

		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		} //checks rows for marks

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		} //checks columns for marks

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		} //checks the diagonal
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}


	public char[][] getTheBoard() {
		return theBoard;
	}
}
