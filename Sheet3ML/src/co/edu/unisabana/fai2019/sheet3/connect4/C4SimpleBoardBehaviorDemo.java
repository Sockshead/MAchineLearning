package co.edu.unisabana.fai2019.sheet3.connect4;

public class C4SimpleBoardBehaviorDemo {
	public static void main(String[] args) {
		
		/* create empty 6x7 board */
		C4State state = new C4State(); // player "a" starts the game
		
		/* simulate some moves */
		state = state.getSuccessorStateForAction(0);
		state = state.getSuccessorStateForAction(2);
		state = state.getSuccessorStateForAction(0);
		state = state.getSuccessorStateForAction(2);
		state = state.getSuccessorStateForAction(1);
		state = state.getSuccessorStateForAction(2);
		state = state.getSuccessorStateForAction(5);
		
		/* show how the board looks like after this sequence of moves */
		C4BoardPrinter printer = new C4BoardPrinter();
		System.out.println(printer.getBoardSerialization(state));
	}
}
