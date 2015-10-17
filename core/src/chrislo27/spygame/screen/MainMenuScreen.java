package chrislo27.spygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import chrislo27.spygame.Settings;
import ionium.registry.GlobalVariables;
import ionium.ui.BackButton;
import ionium.ui.SettingsButton;

public class MainMenuScreen extends ScreenBase {

	public MainMenuScreen(Main m) {
		super(m);

		container.elements.add(new BackButton(0, 0, 64, 64) {

			@Override
			public boolean onLeftClick() {
				Gdx.app.exit();
				
				return false;
			}
			
			@Override
			public BackButton updateActualSizeFromFixed(){
				super.updateActualSizeFromFixed();
				
				x = 1f - width;
				y = 1f - height;
				
				return this;
			}

		}.useExitTexture());
		container.elements.add(new SettingsButton(0, 0, 64, 64));
	}

	@Override
	public void render(float delta) {
		main.batch.begin();

		container.render(main);

		main.batch.end();
	}

	@Override
	public void renderUpdate() {
	}

	@Override
	public void tickUpdate() {
	}

	@Override
	public Array<String> getDebugStrings(Array<String> array) {
		return array;
	}

	@Override
	public void resize(int width, int height) {
		container.onResize();
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
