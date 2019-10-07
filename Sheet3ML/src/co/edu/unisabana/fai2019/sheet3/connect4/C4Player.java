package co.edu.unisabana.fai2019.sheet3.connect4;

public interface C4Player {
	
	/**
	 * Decides the move of the active player (the column where to put the next token).
	 * 
	 * @param game
	 * @return
	 */
	public int getMove(C4State game);
}
