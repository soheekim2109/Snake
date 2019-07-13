package mvc;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SnakeGUI extends JFrame {
	public static final int ROWS = 31;
	public static final int COLS = 31;
	
	private JPanel[][] dots = new JPanel[ROWS][COLS];
	private JPanel grid = new JPanel(new GridLayout(ROWS, COLS));
	
	public SnakeGUI() {
		setTitle("Snake Game");
		setBounds(400, 100, 400, 400);
		setGrid();
		
		setResizable(false);
		setVisible(true);
	}
	
	// for new game
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
	
	public void colourDot(int r, int c, Color colour) {
		dots[r][c].setBackground(colour);
	}
}
