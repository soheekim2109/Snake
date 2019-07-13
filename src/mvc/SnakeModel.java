package mvc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
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
	private int points;
	private boolean gameover;
//	private boolean stopgame;
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			runSnake();
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
		
		newApple(); // appleRow, appleCol
		snake.clear();
		points = 0;
//		direction = null;
		gameover = false;
//		stopgame = false;
	}

	public void newApple() {
		appleRow = randomNum(SnakeGUI.ROWS);
		appleCol = randomNum(SnakeGUI.COLS);
		gui.colourDot(appleRow, appleCol, Color.RED);
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
	
//	public void moveSnake() {
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				runSnake();
//			}
//		}, 0, 100);
//		
//		timer.schedule(task, 0, 100);
//	}
	
	public void runSnake() {
		if (!gameover) {
			if (direction == Direction.LEFT) {
				col--;
				if (row < 0 || row >= SnakeGUI.ROWS ||
						col < 0 || col >= SnakeGUI.COLS
//					|| valid()
						) {
					gameover = true;
//				timer.cancel();
				} else {
					if (row == appleRow && col == appleCol) {
						eatApple();
//					if (!snake.isEmpty()) {
//						int r = snake.get(snake.size()-1).row;
//						int c = snake.get(snake.size()-1).col;
//						
//					}
					} else {
						gui.colourDot(row, col+1, Color.BLACK);
					}
					gui.colourDot(row, col, Color.GREEN);
				}
			} else if (direction == Direction.RIGHT) {
				col++;
				if (row < 0 || row >= SnakeGUI.ROWS ||
						col < 0 || col >= SnakeGUI.COLS) {
					gameover = true;
//				timer.cancel();
				} else {
					if (row == appleRow && col == appleCol) {
						eatApple();
//					if (!snake.isEmpty()) {
//						int r = snake.get(snake.size()-1).row;
//						int c = snake.get(snake.size()-1).col;
//						
//					}
					} else {
						gui.colourDot(row, col-1, Color.BLACK);
					}
					gui.colourDot(row, col, Color.GREEN);
				}
			} else if (direction == Direction.UP) {
				row--;
				if (row < 0 || row >= SnakeGUI.ROWS ||
						col < 0 || col >= SnakeGUI.COLS) {
					gameover = true;
//				timer.cancel();
				} else {
					if (row == appleRow && col == appleCol) {
						eatApple();
//					if (!snake.isEmpty()) {
//						int r = snake.get(snake.size()-1).row;
//						int c = snake.get(snake.size()-1).col;
//						
//					}
					} else {
						gui.colourDot(row+1, col, Color.BLACK);
					}
					gui.colourDot(row, col, Color.GREEN);
				}
			} else if (direction == Direction.DOWN) {
				row++;
				if (row < 0 || row >= SnakeGUI.ROWS ||
						col < 0 || col >= SnakeGUI.COLS) {
					gameover = true;
//				timer.cancel();
				} else {
					if (row == appleRow && col == appleCol) {
						eatApple();
//					if (!snake.isEmpty()) {
//						int r = snake.get(snake.size()-1).row;
//						int c = snake.get(snake.size()-1).col;
//						
//					}
					} else {
						gui.colourDot(row-1, col, Color.BLACK);
					}
					gui.colourDot(row, col, Color.GREEN);
				}
			}
			
//			if (stopgame) {
////				timer.cancel();
//				System.out.println("stop not over");
//			}
		}
		
		
//		if (stopgame && !gameover) {
////			timer.cancel();
//			System.out.println("stop not over");
//		}
	}
	
	// called when snake eats apple
	private void eatApple() {
//		if (row == appleRow && col == appleCol) {
			snake.add(0, new SnakeDot(row, col));
			newApple();
			
			System.out.println("Snake:");
			for (SnakeDot dot : snake) {
				System.out.println(dot);
			}
			
			points++;
			System.out.println("Points: " + points);
//		}
	}
	
	// checks if snake is touching itself
	private boolean valid() {
		boolean valid = true;
		
		for (SnakeDot dot : snake) {
			if (row == dot.row || col == dot.col) {
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
	
	// displays game over
	private void gameover() {
		gui.colourDot(row, col, Color.GREEN);
	}

//	public void stopGame() {
//		stopgame = true;
//	}
}
