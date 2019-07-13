package main;

import mvc.SnakeController;
import mvc.SnakeGUI;
import mvc.SnakeModel;

public class SnakeDriver {

	public static void main(String[] args) {
		SnakeGUI gui = new SnakeGUI();
		SnakeModel model = new SnakeModel(gui);
		new SnakeController(gui, model);
	}

}
