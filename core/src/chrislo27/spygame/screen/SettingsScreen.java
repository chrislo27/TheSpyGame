package chrislo27.spygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import ionium.registry.ScreenRegistry;
import ionium.ui.BackButton;
import ionium.ui.Button;
import ionium.util.i18n.Localization;

public class SettingsScreen extends ScreenBase {

	public SettingsScreen(Main m) {
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

		container.elements.add(new Button(0, 0, 64, 64, "") {

			@Override
			public void render(ionium.templates.Main main, BitmapFont font) {
				imageRender(main, "guilanguage");
				font.setColor(Color.WHITE);
				font.draw(main.batch,
						Localization.get("menu.currentLanguage") + ": "
								+ Localization.instance().getCurrentBundle().getLocale().getName(),
						(x + width) * Gdx.graphics.getWidth() + 5,
						(y + (height / 2)) * Gdx.graphics.getHeight());
			}

			@Override
			public boolean onLeftClick() {
				main.setScreen(ScreenRegistry.get("langSelect"));

				return true;
			}

		}.setFixedSize(0, 0, 64, 64));

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
