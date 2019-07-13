package mvc;

public class SnakeDot {
	public int row;
	public int col;
	
	public SnakeDot(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		boolean check = false;
//		SnakeDot other = (SnakeDot) obj;
//		
//		if (row == other.row && col == other.col) {
//			check = true;
//		}
//		
//		return check;
//	}
	
	@Override
	public String toString() {
		return "[" + row + "," + col + "]";
	}

}
