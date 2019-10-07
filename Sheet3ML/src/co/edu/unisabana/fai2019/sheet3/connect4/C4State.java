package co.edu.unisabana.fai2019.sheet3.connect4;

import java.util.HashSet;
import java.util.Set;

/**
 * The main Connect4 State class with all relevant information for a game state and a win-decider.
 * 
 * @author felix
 *
 */
public class C4State {
	private final short[][] matrix; // 0 stands for empty, +1 for Max, and -1 for Min
	private final int rows;
	private final int columns;
	private final short activePlayer;
	private final short winner;
	private final int requiredNumberOfTokens = 4;
	
	/**
	 * Creates an empty board of the default 6x7 game.
	 */
	public C4State() {
		this(6, 7);
	}


	/**
	 * Creates an empty game
	 * 
	 * @param column
	 * @return
	 */
	public C4State(int rows, int columns) {
		this (new short[rows][columns], (short)1, (short)0);
	}
	
	private C4State(short[][] matrix, short activePlayer, short winner) {
		super();
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
		this.activePlayer = activePlayer;
		this.winner = winner;
		if (winner != 0 && activePlayer != 0) {
			throw new IllegalArgumentException("Game has been won by " + winner + ". Then the active player must be 0!");
		}
	}
	
	public C4State getSuccessorStateForAction(int column) {
		
		/* check whether game is still active */
		if (activePlayer == 0) {
			throw new IllegalStateException("No player is active anymore, because " + winner + " has won the game. No successor can be computed.");
		}
		if (!isColumnFree(column)) {
			throw new IllegalArgumentException("Column " + column + " is not free anymore, this move is not possible!");
		}
		
		/* create new board */
		int rowIndex = getNumberOfTokensInColumn(column);
		short[][] clonedMatrix = getCopyOfMatrix();
		clonedMatrix[rowIndex][column] = activePlayer;
		
		/* check whether the newly inserted token made the active player win */
		boolean win = false;
		int inRow = 1;
		for (int col = column - 1; col >= 0 && clonedMatrix[rowIndex][col] == activePlayer; col --) {
			inRow ++;
		}
		for (int col = column + 1; col < columns && clonedMatrix[rowIndex][col] == activePlayer; col ++) {
			inRow ++;
		}
		if (inRow >= requiredNumberOfTokens) {
			win = true;
		}
		else {
			int inColumn = 1;
			for (int row = rowIndex - 1; row >= 0 && clonedMatrix[row][column] == activePlayer; row --) {
				inColumn ++;
			}
			for (int row = rowIndex + 1; row < rows && clonedMatrix[row][column] == activePlayer; row ++) {
				inColumn ++;
			}
			if (inColumn >= requiredNumberOfTokens) {
				win = true;
			}
			else {
				int inIncreasingDiagonal = 1;
				for (int aug = - 1; rowIndex + aug >= 0 && column + aug >= 0 && clonedMatrix[rowIndex + aug][column + aug] == activePlayer; aug --) {
					inIncreasingDiagonal ++;
				}
				for (int aug = 1; rowIndex + aug < rows && column + aug < columns && clonedMatrix[rowIndex + aug][column + aug] == activePlayer; aug ++) {
					inIncreasingDiagonal ++;
				}
				if (inIncreasingDiagonal >= requiredNumberOfTokens) {
					win = true;
				}
				else {
					int inDecreasingDiagonal = 1;
					for (int aug = - 1; rowIndex + aug >= 0 && column - aug < columns && clonedMatrix[rowIndex + aug][column - aug] == activePlayer; aug --) {
						inDecreasingDiagonal ++;
					}
					for (int aug = 1; rowIndex + aug < rows && column - aug >= 0 && clonedMatrix[rowIndex + aug][column - aug] == activePlayer; aug ++) {
						inDecreasingDiagonal ++;
					}
					if (inDecreasingDiagonal >= requiredNumberOfTokens) {
						win = true;
					}
				}
			}
		}
		return new C4State(clonedMatrix, win ? 0 : (short)(activePlayer * -1), win ? activePlayer : 0);
	}
	
	public int getNumberOfTokensInColumn(int column) {
		for (int i = 0; i < rows; i++) {
			if (matrix[i][column] == 0)
				return i;
		}
		return rows;
	}

	public short[][] getCopyOfMatrix() {
		short[][] copy = new short[rows][columns];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				copy[r][c] = matrix[r][c];
			}
		}
		return copy;
	}

	public short getActivePlayer() {
		return activePlayer;
	}
	
	public short getWinner() {
		return winner;
	}


	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}
	
	public boolean isColumnFree(int column) {
		return getNumberOfTokensInColumn(column) < rows;
	}
	
	public int getNumTokens() {
		int t = 0;
		for (int c = 0; c < columns; c++) {
			t += getNumberOfTokensInColumn(c);
		}
		return t;
	}
	
	public int getNumOfFreeCells() {
		return rows * columns - getNumTokens();
	}
	
	public boolean movePossible() {
		if (winner != 0) {
			return false;
		}
		for (int c = 0; c < columns; c++) {
			if (isColumnFree(c))
				return true;
		}
		return false;
	}
	
	public Set<Integer> getPossibleMoves() {
		Set<Integer> moves = new HashSet<>();
		for (int c = 0; c < columns; c++) {
			if (isColumnFree(c)) {
				moves.add(c);
			}
		}
		return moves;
	}
}
