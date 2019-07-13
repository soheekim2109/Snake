package mvc;

public class SnakeDot {
	public int row;
	public int col;
	
	public SnakeDot(int row, int col) {
		this.row = row;
		this.col = col;
	}
		
	@Override
	public String toString() {
		return "[" + row + "," + col + "]";
	}

}
