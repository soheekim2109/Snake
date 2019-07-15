package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SnakeGUI extends JFrame {
	public static final int ROWS = 31;
	public static final int COLS = 31;
	
	private JPanel[][] dots = new JPanel[ROWS][COLS];
	private JPanel grid = new JPanel(new GridLayout(ROWS, COLS));
	private JPanel status = new JPanel(new GridLayout(0,2));
	private JLabel score = new JLabel();
	private JLabel hscore = new JLabel();
	
	public SnakeGUI() {
		setTitle("Snake");
		setBounds(400, 100, 400, 400);
		setLayout(new BorderLayout());
		setGrid();
		setStatusbar();
		setIconImage(new ImageIcon("snake-icon.png").getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		add(grid, BorderLayout.CENTER);
	}
	
	public void setStatusbar() {
		status.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		hscore.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.GRAY));
		status.add(score);
		status.add(hscore);
		add(status, BorderLayout.SOUTH);
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
	
	public void updateScore(int points) {
		score.setText("Score: " + points);
	}
	
	public void updateHighScore(int points) {
		hscore.setText("High Score: " + points);
	}
}
