package co.edu.unisabana.fai2019.sheet3.connect4;

import java.util.Random;

public class RandomC4Player implements C4Player {

	private final Random random;
	
	public RandomC4Player(long seed) {
		this(new Random(seed));
	}

	public RandomC4Player(Random random) {
		super();
		this.random = random;
	}

	@Override
	public int getMove(C4State game) {
		if (!game.movePossible()) {
			throw new IllegalArgumentException("Cannot tell any move for a game board in which no move is possible anymore!");
		}
		int columns = game.getColumns();
		int column;
		do {
			column = random.nextInt(columns);
		}
		while (!game.isColumnFree(column));
		return column;
	}

}
