package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mvc.SnakeModel.Direction;

public class SnakeController implements KeyListener {
	private SnakeGUI gui;
	private SnakeModel model;
	
	public SnakeController(SnakeGUI gui, SnakeModel model) {
		this.model = model;
		this.gui = gui;
		gui.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			model.setDirection(Direction.LEFT);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			model.setDirection(Direction.RIGHT);
		} else if (e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			model.setDirection(Direction.UP);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			model.setDirection(Direction.DOWN);
		} else if (e.getKeyCode() == KeyEvent.VK_T) {
			// new game
			gui.resetGrid();
//			model.stopGame();
			model.newModel();
			System.out.println("new game");
			// reset snake, current position, apple position(, points)
		} else if (e.getKeyCode() == KeyEvent.VK_G) {
			model.setDirection(null);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}