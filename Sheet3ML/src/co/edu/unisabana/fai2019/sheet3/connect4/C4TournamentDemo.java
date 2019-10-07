package co.edu.unisabana.fai2019.sheet3.connect4;

public class C4TournamentDemo {
	public static void main(String[] args) {

		int winsA = 0;
		int winsB = 0;
		final int turns = 1;
		for (int i = 0; i < turns; i++) {
			C4Player p1 = new RandomC4Player(1);
			C4Player p2 = new RandomC4Player(2);
			C4State endState = runCombat(5, 5, p1, p2);
			if (endState.getWinner() == 1) {
				winsA++;
			}
			else if (endState.getWinner() == -1) {
				winsB ++;
			}
		}
		System.out.println(winsA + "/" + (turns - winsA - winsB) + "/" + winsB);
	}

	/**
	 * Lets player1 play against player2 on a customized game board
	 * 
	 * @param rows
	 * @param columns
	 * @param player1
	 * @param player2
	 * @return
	 */
	public static C4State runCombat(int rows, int columns, C4Player player1, C4Player player2) {
		C4State state = new C4State(rows, columns);
		C4Player activePlayer = player1;
		C4BoardPrinter printer = new C4BoardPrinter();
		
		/* as long as the game is not over ... */
		while (state.getWinner() == 0 && state.movePossible()) {
			
			/* get player move */
			String activePlayerName = (activePlayer == player1 ? "a" : "b");
			System.out.println(printer.getBoardSerialization(state));
			short[][] boardBefore =  state.getCopyOfMatrix();
			int move = activePlayer.getMove(state);
			
			/* verify that the player has not changed the state */
			short[][] boardAfter = state.getCopyOfMatrix();
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					if (boardBefore[r][c] != boardAfter[r][c]) {
						throw new IllegalStateException("Player " + activePlayerName + " has manipulated the game board in field " + r + "/" + c + " during his reasoning process! Board looks like:\n" + printer.getBoardSerialization(state));						
					}
				}
			}
			
			/* compute successor state */
			state = state.getSuccessorStateForAction(move);
			System.out.println("Player " + activePlayerName + " has put a token in column " + move
					+ ". Win move: " + (state.getWinner() != 0));
			if (state.getWinner() != 0) {
				System.out.println(printer.getBoardSerialization(state));
			}
			activePlayer = activePlayer == player1 ? player2 : player1;
		}
		return state;
	}
}
