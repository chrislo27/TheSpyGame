package chrislo27.spygame.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import chrislo27.spygame.Main;
import ionium.registry.GlobalVariables;
import ionium.util.Logger;

public class DesktopLauncher {

	private static Logger logger;

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "";
		config.width = GlobalVariables.getInt("DEFAULT_WIDTH");
		config.height = GlobalVariables.getInt("DEFAULT_HEIGHT");
		config.fullscreen = false;
		config.foregroundFPS = GlobalVariables.getInt("MAX_FPS");
		config.backgroundFPS = GlobalVariables.getInt("MAX_FPS");
		config.resizable = false;
		config.vSyncEnabled = true;

		config.addIcon("images/icon/icon32.png", FileType.Internal);
		config.addIcon("images/icon/icon16.png", FileType.Internal);
		config.addIcon("images/icon/icon128.png", FileType.Internal);

		logger = new Logger("", com.badlogic.gdx.utils.Logger.DEBUG);
		// new GameLwjglApp(new Main(logger), config, logger);
	}

}
