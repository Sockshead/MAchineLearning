package co.edu.unisabana.fai2019.sheet3.connect4;

public class C4BoardPrinter {
	public String getBoardSerialization(C4State state) {
		StringBuilder sb = new StringBuilder();
		int rows = state.getRows();
		int cols = state.getColumns();
		for (int row = rows - 1; row >= 0; row --) {
			sb.append("|");
			for (int col = 0; col < cols; col ++) {
				short token = state.getCopyOfMatrix()[row][col];
				char sign = ' ';
				if (token != 0) {
					sign = token > 0 ? 'a' : 'b';
				}
				sb.append(sign);
				sb.append("|");
			}
			sb.append("\n");
		}
		sb.append("+");
		for (int col = 0; col < cols; col ++) {
			sb.append("-+");
		}
		return sb.toString();
	}
}
