package pl.rejurhf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.rejurhf.StrategyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = StrategyGame.GAME_NAME;
		config.width = StrategyGame.WIDTH;
		config.height = StrategyGame.HEIGHT;
//		config.resizable = false;

		new LwjglApplication(new StrategyGame(), config);
	}
}
