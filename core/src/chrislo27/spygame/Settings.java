package chrislo27.spygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import ionium.registry.GlobalVariables;

public class Settings {

	private static Settings instance;

	public float musicVolume = 1;
	public float soundVolume = 1;

	public int actualWidth = GlobalVariables.getInt("DEFAULT_WIDTH");
	public int actualHeight = GlobalVariables.getInt("DEFAULT_HEIGHT");
	public boolean fullscreen = false;
	public boolean hasResolutionBeenSaved = false;

	private Settings() {
	}

	public static Settings instance() {
		if (instance == null) {
			instance = new Settings();
			instance.loadResources();
		}
		return instance;
	}

	private Preferences pref;

	private void loadResources() {
		pref = getPrefWithGamePrefix("settings");

		soundVolume = pref.getFloat("soundVolume", 1f);
		musicVolume = pref.getFloat("musicVolume", 1f);
		actualWidth = pref.getInteger("actualWidth", 0);
		actualHeight = pref.getInteger("actualHeight", 0);
		fullscreen = pref.getBoolean("fullscreen", true);
		hasResolutionBeenSaved = pref.getBoolean("hasResolutionBeenSaved", false);
	}

	public static Preferences getPrefWithGamePrefix(String ref) {
		return Gdx.app.getPreferences("TheSpyGame-" + ref);
	}

	public void save() {
		pref.putFloat("sound", soundVolume).putFloat("music", musicVolume)
				.putInteger("actualWidth", actualWidth).putInteger("actualHeight", actualHeight)
				.putBoolean("hasResolutionBeenSaved", true).flush();
	}

	public static Preferences getSettingsPreferences() {
		return instance().pref;
	}
}
