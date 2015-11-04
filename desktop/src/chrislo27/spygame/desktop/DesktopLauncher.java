package chrislo27.spygame.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import chrislo27.spygame.Main;
import chrislo27.spygame.load.GlobalVariableTweaks;
import ionium.desktop.ArgumentInferredLwjglAppConfig;
import ionium.desktop.GameLwjglApp;
import ionium.registry.GlobalVariables;
import ionium.util.Logger;
import ionium.util.resolution.Resolution;
import ionium.util.resolution.ResolutionDeterminator;

public class DesktopLauncher {

	private static Logger logger;

	public static void main(String[] args) {
		GlobalVariableTweaks.tweak();
		
		ArgumentInferredLwjglAppConfig config = new ArgumentInferredLwjglAppConfig(args);
		config.title = "";
		config.width = GlobalVariables.getInt("DEFAULT_WIDTH");
		config.height = GlobalVariables.getInt("DEFAULT_HEIGHT");
		config.fullscreen = false;
		config.foregroundFPS = GlobalVariables.getInt("MAX_FPS");
		config.backgroundFPS = GlobalVariables.getInt("MAX_FPS");
		config.resizable = false;
		config.vSyncEnabled = true;
		config.samples = 4;
		
		ResolutionDeterminator.determineIdealResolution(config, Resolution.get169ResolutionsList());
		
		config.inferFromArguments();

		config.addIcon("images/icon/icon32.png", FileType.Internal);
		config.addIcon("images/icon/icon16.png", FileType.Internal);
		config.addIcon("images/icon/icon128.png", FileType.Internal);

		logger = new Logger("", com.badlogic.gdx.utils.Logger.DEBUG);
		new GameLwjglApp(new Main(logger), config, logger);
	}

}
