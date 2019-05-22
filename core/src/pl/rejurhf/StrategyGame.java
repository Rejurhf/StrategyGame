package pl.rejurhf;

import com.badlogic.gdx.Game;
import pl.rejurhf.screens.InitialScreen;

public class StrategyGame extends Game {
	public static final String GAME_NAME = "Strategy Game";

	public static final int BOARD_WIDTH = 70;
	public static final int BOARD_HEIGHT = 50;
	public static final int UNIT_LEN = 20;

	public static final int WIDTH = 1778;
	public static final int HEIGHT = 1000;

	private boolean paused;
	
	@Override
	public void create () {
		this.setScreen(new InitialScreen(this));
	}

	/**
	 * ---------------------
	 * getters and setters
	 *
	 */

	public void setPaused(boolean poused) {
		this.paused = poused;
	}

	public boolean isPaused() {
		return paused;
	}
}
