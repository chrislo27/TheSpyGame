package chrislo27.spygame.load;

import ionium.registry.GlobalVariables;
import ionium.util.DebugSetting;

public class ConstTweaks {

	public static final void tweakConstants(){
		GlobalVariables.instance().putInt("TICKS", 30);
		GlobalVariables
				.instance()
				.putString("VERSION_URL",
						"https://raw.githubusercontent.com/chrislo27/VersionPlace/master/TheSpyGame-version.txt");

		DebugSetting.showFPS = false;
	}
	
}
