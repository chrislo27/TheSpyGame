package chrislo27.spygame.screen;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import ionium.registry.ScreenRegistry;
import ionium.ui.BackButton;

public class LanguageScreen extends ScreenBase {

	public LanguageScreen(Main m) {
		super(m);

		container.elements.add(new BackButton(0, 0, 64, 64) {

			@Override
			public boolean onLeftClick() {
				main.setScreen(ScreenRegistry.get("mainMenu"));

				return true;
			}

			@Override
			public BackButton updateActualSizeFromFixed() {
				super.updateActualSizeFromFixed();

				x = 1f - width;
				y = 1f - height;

				return this;
			}

		});

	}

	@Override
	public void render(float delta) {
		main.batch.begin();

		main.batch.flush();
		
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
		return null;
	}

	@Override
	public void resize(int width, int height) {
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
