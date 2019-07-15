package mvc;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeModel {
	private SnakeGUI gui;
	
	private ArrayList<SnakeDot> snake = new ArrayList<SnakeDot>();
	private int row;
	private int col;
	private Direction direction;
	
	private int appleRow;
	private int appleCol;
	
	private int highscore;
	private int points;
	private boolean gameover;
	private boolean pause;
	
	private File hscorefile = new File("highscore");
	
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			if (!gameover && !pause) {
				if (valid()) {
					runSnake();
				} else {
					gameover = true;
				}
			}
		}
	};

	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	
	public SnakeModel(SnakeGUI gui) {
		this.gui = gui;
		newModel();
		timer.schedule(task, 0, 100);
	}
	
	public void newModel() {
		// initial starting position
		row = randomNum(SnakeGUI.ROWS);
		col = randomNum(SnakeGUI.COLS);
		gui.colourDot(row, col, Color.GREEN);
		
		newApple();
		snake.clear();
		points = 0;
		direction = null;
		gameover = false;
		pause = false;
		
		gui.updateScore(points);
		gui.updateHighScore(highscore());
	}

	public void newApple() {
		do {
			appleRow = randomNum(SnakeGUI.ROWS);
			appleCol = randomNum(SnakeGUI.COLS);
		} while (!validApple());
		
		gui.colourDot(appleRow, appleCol, Color.RED);
	}
	
	private boolean validApple() {
		boolean valid = true;
		
		for (SnakeDot dot : snake) {
			if ((appleRow == dot.row && appleCol == dot.col) ||
					(appleRow == row && appleCol == col)) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	public int highscore() {
		try {
			Scanner hscore = new Scanner(hscorefile);
			if (hscore.hasNextInt()) {
				highscore = hscore.nextInt();
			}
			hscore.close();
		} catch (FileNotFoundException e) {
			highscore = 0;
			e.printStackTrace();
		}
		
		return highscore;
	}
	
	// called when current points > high score
	private void updateHighscore() {
		try {
			PrintWriter hscore = new PrintWriter(hscorefile);
			hscore.print(points);
			hscore.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pauseGame() {
		pause = !pause;
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void runSnake() {
		// move current position
		if (direction == Direction.LEFT) {
			col--;
		} else if (direction == Direction.RIGHT) {
			col++;
		} else if (direction == Direction.UP) {
			row--;
		} else if (direction == Direction.DOWN) {
			row++;
		}

		if (row < 0 || row >= SnakeGUI.ROWS ||
				col < 0 || col >= SnakeGUI.COLS) {
			gameover = true;
		} else {
			boolean apple = row == appleRow && col == appleCol;
			boolean empty = snake.isEmpty();

			if (empty && !apple) {
				// colour last position to black
				if (direction == Direction.LEFT) {
					gui.colourDot(row, col+1, Color.BLACK);
				} else if (direction == Direction.RIGHT) {
					gui.colourDot(row, col-1, Color.BLACK);
				} else if (direction == Direction.UP) {
					gui.colourDot(row+1, col, Color.BLACK);
				} else if (direction == Direction.DOWN) {
					gui.colourDot(row-1, col, Color.BLACK);
				}
			}

			if (empty && apple) {
				// colour last position to black
				if (direction == Direction.LEFT) {
					snake.add(0, new SnakeDot(row, col+1));
				} else if (direction == Direction.RIGHT) {
					snake.add(0, new SnakeDot(row, col-1));
				} else if (direction == Direction.UP) {
					snake.add(0, new SnakeDot(row+1, col));
				} else if (direction == Direction.DOWN) {
					snake.add(0, new SnakeDot(row-1, col));
				}

				// add current to snake
				snake.add(0, new SnakeDot(row, col));

				eatApple();
			}

			if (!empty && !apple) {
				// add current to snake
				snake.add(0, new SnakeDot(row, col));

				// remove last snake and colour it black
				SnakeDot last = snake.remove(snake.size()-1);
				gui.colourDot(last.row, last.col, Color.BLACK);
			}

			if (!empty && apple) {
				eatApple();
				
				// add current to snake
				snake.add(0, new SnakeDot(row, col));
			}

			for (SnakeDot dot : snake) {
				gui.colourDot(dot.row, dot.col, Color.GREEN);
			}

			// colour current to green
			gui.colourDot(row, col, Color.GREEN);
		}
	}
	
	// called when snake eats apple
	private void eatApple() {
		newApple();
		
		points++;
		gui.updateScore(points);
		
		if (points > highscore) {
			updateHighscore();
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	// checks if snake bites itself
	private boolean valid() {
		boolean valid = true;
		
		for (int i = 1; i < snake.size(); i++) {
			if (row == snake.get(i).row && col == snake.get(i).col) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	// returns a random number between 0 to n-1
	private int randomNum(int n) {
		Random random = new Random();
		return random.nextInt(n);
	}
	
	public class SnakeDot {
		public int row;
		public int col;
		
		public SnakeDot(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
