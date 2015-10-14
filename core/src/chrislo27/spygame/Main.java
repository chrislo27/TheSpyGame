package chrislo27.spygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.sun.java.accessibility.util.Translator;

import chrislo27.spygame.load.AssetLoader;
import chrislo27.spygame.load.ConstTweaks;
import ionium.registry.AssetRegistry;
import ionium.registry.ScreenRegistry;
import ionium.util.DebugSetting;
import ionium.util.Logger;
import ionium.util.i18n.Localization;

public class Main extends ionium.templates.Main {

	public Main(Logger l) {
		super(l);
	}

	@Override
	public String getScreenToSwitchToAfterLoadingAssets() {
		return "mainmenu";
	}
	
	@Override
	public void create() {
		super.create();

		ConstTweaks.tweakConstants();
		resizeScreenFromSettings();

		AssetRegistry.instance().addAssetLoader(new AssetLoader());
	}

	private void resizeScreenFromSettings() {
		if (Gdx.graphics.getWidth() != Settings.instance().actualWidth
				|| Gdx.graphics.getHeight() != Settings.instance().actualHeight
				|| Gdx.graphics.isFullscreen() != Settings.instance().fullscreen) {
			Gdx.graphics.setDisplayMode(Settings.instance().actualWidth, Settings.instance().actualHeight, false);
		}
	}

	@Override
	public void prepareStates() {
		super.prepareStates();

		ScreenRegistry reg = ScreenRegistry.instance();

	}

	@Override
	protected void preRender() {
		super.preRender();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	protected void postRender() {
		super.postRender();
	}

	@Override
	protected Array<String> getDebugStrings() {
		return super.getDebugStrings();
	}

	@Override
	public void tickUpdate() {
		super.tickUpdate();
	}

	@Override
	public void inputUpdate() {
		super.inputUpdate();

		if (Gdx.input.isKeyPressed(DebugSetting.DEBUG_KEY)) {
			if (Gdx.input.isKeyPressed(Keys.I) && Gdx.input.isKeyJustPressed(Keys.N)) {
				Localization.instance().reloadFromFile();
				Main.logger.debug("Reloaded I18N from files");
			}
		}

	}

	@Override
	public void loadFont() {
		super.loadFont();
	}

	@Override
	public void dispose() {
		super.dispose();

		Settings.getPreferences().flush();
	}
	
}
