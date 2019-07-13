package mvc;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGUI extends JFrame {
	public static final int ROWS = 31;
	public static final int COLS = 31;
	
	private JPanel[][] dots = new JPanel[ROWS][COLS];
	private int currentRow = 15; // middle
	private int currentCol = 15; // middle
	private JPanel grid = new JPanel(new GridLayout(ROWS, COLS));
	
	public SnakeGUI() {
		setTitle("Snake Game");
		setBounds(100, 100, 400, 400);
		setGrid();
		
//		model.initialPosition(currentRow, currentCol);
		
		// start at 15,15
//		colourDot(dots[currentRow][currentCol], Color.GREEN);
		
		setVisible(true);
	}
	
	// initialising new game
	public void setGrid() {
		// generate dots
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				dots[i][j] = new JPanel();
				grid.add(dots[i][j]);
			}
		}
		resetGrid();
		add(grid);
	}
	
	public void resetGrid() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				dots[i][j].setBackground(Color.BLACK);
			}
		}
	}
	
//	// colour a dot
//	public void colourDot(JPanel dot, Color colour) {
//		dot.setBackground(colour);
//	}
	
	// colour a dot
	public void colourDot(int r, int c, Color colour) {
		dots[r][c].setBackground(colour);
	}

	// colour a dot
	public void colourDot(int[] pos, Color colour) {
		dots[pos[0]][pos[1]].setBackground(colour);
	}

}
