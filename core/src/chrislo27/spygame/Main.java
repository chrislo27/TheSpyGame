package chrislo27.spygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;
import com.bitfire.utils.ShaderLoader;

import chrislo27.spygame.load.GlobalVariableTweaks;
import chrislo27.spygame.load.LanguageAdder;
import chrislo27.spygame.load.assetloaders.AssetLoader;
import chrislo27.spygame.screen.LanguageScreen;
import chrislo27.spygame.screen.LevelEditorScreen;
import chrislo27.spygame.screen.MainMenuScreen;
import chrislo27.spygame.screen.SettingsScreen;
import chrislo27.spygame.tests.TestGameScreen;
import ionium.registry.AssetRegistry;
import ionium.registry.ScreenRegistry;
import ionium.util.DebugSetting;
import ionium.util.Logger;
import ionium.util.i18n.Localization;
import ionium.util.resolution.Resolutable;
import ionium.util.resolution.Resolution;
import ionium.util.resolution.ResolutionDeterminator;

public class Main extends ionium.templates.Main {

	public BitmapFont font;

	public Main(Logger l) {
		super(l);
	}

	@Override
	public String getScreenToSwitchToAfterLoadingAssets() {
		return "mainMenu";
	}

	@Override
	public void create() {
		super.create();

		GlobalVariableTweaks.tweak();
		LanguageAdder.addLanguages();
		ShaderLoader.BasePath = "shaders/";

		AssetRegistry.instance().addAssetLoader(new AssetLoader());

		resizeScreenFromSettings();
	}

	private void resizeScreenFromSettings() {
		if (!Settings.instance().hasResolutionBeenSaved) {
			Resolutable res = new Resolutable(){

				private int width, height;
				private boolean fs;
				
				@Override
				public void setWidth(int w) {
					width = w;
				}

				@Override
				public void setHeight(int h) {
					height = h;
				}

				@Override
				public void setFullscreen(boolean fs) {
					this.fs = fs;
				}

				@Override
				public int getWidth() {
					return width;
				}

				@Override
				public int getHeight() {
					return height;
				}

				@Override
				public boolean isFullscreen() {
					return fs;
				}
				
			};
			
			ResolutionDeterminator.determineIdealResolution(res, Resolution.get169ResolutionsList());
			
			Gdx.graphics.setDisplayMode(res.getWidth(), res.getHeight(), true);
		} else {
			if ((Gdx.graphics.getWidth() != Settings.instance().actualWidth
					|| Gdx.graphics.getHeight() != Settings.instance().actualHeight
					|| Gdx.graphics.isFullscreen() != Settings.instance().fullscreen)) {
				Gdx.graphics.setDisplayMode(Settings.instance().actualWidth,
						Settings.instance().actualHeight, Settings.instance().fullscreen);
			}
		}
	}

	@Override
	public void prepareStates() {
		super.prepareStates();

		ScreenRegistry reg = ScreenRegistry.instance();

		reg.add("mainMenu", new MainMenuScreen(this));
		reg.add("settings", new SettingsScreen(this));
		reg.add("langSelect", new LanguageScreen(this));
		reg.add("testGame", new TestGameScreen(this));
		reg.add("levelEditor", new LevelEditorScreen(this));
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

		FreeTypeFontGenerator ttfGenerator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/courbd.ttf"));
		FreeTypeFontParameter ttfParam = new FreeTypeFontParameter();
		ttfParam.magFilter = TextureFilter.Nearest;
		ttfParam.minFilter = TextureFilter.Nearest;
		ttfParam.genMipMaps = true;
		ttfParam.size = 24;
		font = ttfGenerator.generateFont(ttfParam);
		font.getData().markupEnabled = true;

		ttfGenerator.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);
		
		Settings.instance().actualWidth = Gdx.graphics.getWidth();
		Settings.instance().actualHeight = Gdx.graphics.getHeight();
		Settings.instance().fullscreen = Gdx.graphics.isFullscreen();
		Settings.instance().save();
	}
	
	@Override
	public void dispose() {
		super.dispose();

		Settings.instance().save();
	}

}
