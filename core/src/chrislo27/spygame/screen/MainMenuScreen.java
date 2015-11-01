package chrislo27.spygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import ionium.registry.ScreenRegistry;
import ionium.ui.BackButton;
import ionium.ui.Button;
import ionium.ui.SettingsButton;

public class MainMenuScreen extends ScreenBase {

	public MainMenuScreen(Main m) {
		super(m);

		container.elements.add(new BackButton(0, 0, 64, 64) {

			@Override
			public boolean onLeftClick() {
				Gdx.app.exit();

				return true;
			}

			@Override
			public BackButton updateActualSizeFromFixed() {
				super.updateActualSizeFromFixed();

				x = 1f - width;
				y = 1f - height;

				return this;
			}

		}.useExitTexture());
		container.elements.add(new SettingsButton(0, 0, 64, 64) {

			@Override
			public boolean onLeftClick() {
				main.setScreen(ScreenRegistry.get("settings"));

				return true;
			}
		});

		container.elements.add(new Button(0.4f, 0.2f, 0.2f, 0.05f, "menu.play") {

			@Override
			public boolean onLeftClick() {
				return true;
			}
		});
	}

	@Override
	public void render(float delta) {
		main.batch.begin();

		container.render(main, main.font);

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
