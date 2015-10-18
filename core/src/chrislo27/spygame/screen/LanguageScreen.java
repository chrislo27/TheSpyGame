package chrislo27.spygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import ionium.registry.AssetRegistry;
import ionium.registry.ScreenRegistry;
import ionium.ui.BackButton;
import ionium.util.MathHelper;
import ionium.util.Utils;
import ionium.util.i18n.Localization;
import ionium.util.i18n.NamedLocale;

public class LanguageScreen extends ScreenBase {

	private Array<Flag> allLanguages = new Array<>();

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

		addLanguageButtons();

	}

	public void addLanguageButtons() {

		allLanguages.clear();

		for (int i = 0; i < Localization.instance().getAllBundles().size; i++) {
			NamedLocale locale = Localization.instance().getAllBundles().get(i).getLocale();

			allLanguages.add(new Flag(locale));

			Main.logger.debug("noot");
		}

	}

	@Override
	public void render(float delta) {
		main.batch.begin();

		for (int i = 0; i < allLanguages.size; i++) {
			Flag flag = allLanguages.get(i);
			NamedLocale locale = flag.locale;

			flag.update(1f);

			int currentFlag = flag.currentFlag;
			int nextFlag = flag.nextFlag;

			Texture tex = AssetRegistry.getTexture(locale.getFlags().get(currentFlag));

			final int spacingBetweenName = 16;
			final int spacingBetweenFlags = tex.getHeight() + 16;

			float x = Gdx.graphics.getWidth() / 2f - (tex.getWidth()) - (spacingBetweenName / 2f);
			float y = Main.convertY(256 + (spacingBetweenFlags * i));

			if (locale.getFlags().size == 1 || flag.waiting) {
				main.batch.draw(tex, x, y, tex.getWidth(), tex.getHeight());
			} else {
				int down = (int) (tex.getHeight() * flag.percentage);

				main.batch.draw(tex, x, y, tex.getWidth(), tex.getHeight() - down, 0, 0,
						tex.getWidth(), tex.getHeight() - down, false, false);

				tex = AssetRegistry.getTexture(locale.getFlags().get(nextFlag));

				main.batch.draw(tex, x, y + (tex.getHeight() - down), tex.getWidth(), down, 0,
						tex.getHeight() - down, tex.getWidth(), down, false, false);
			}

			main.font.setColor(1, 1, 1, 1);
			main.font
					.draw(main.batch,
							locale.getName() + (locale
									.equals(Localization.instance().getCurrentBundle().getLocale())
											? " <--" : ""),
					x + (tex.getWidth()) + spacingBetweenName,
					y + (tex.getHeight() / 2) + (Utils.getHeight(main.font, locale.getName()) / 2));

			if (Main.convertY(Gdx.input.getY()) >= y
					&& Main.convertY(Gdx.input.getY()) <= y + tex.getHeight()) {
				if (Utils.isButtonJustPressed(Buttons.LEFT)) {
					Localization.instance().setLanguage(locale);
				}
			}

		}

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

	private class Flag {

		NamedLocale locale;

		boolean waiting = true;
		float percentage = 0;
		int currentFlag = -1;
		int nextFlag = 0;

		public Flag(NamedLocale l) {
			this.locale = l;

			scrollFlags();
		}

		public void update(float speed) {
			if (percentage >= 1) {
				waiting = !waiting;
				percentage = 0;

				if (waiting == true) {
					scrollFlags();
				}
			} else percentage += Gdx.graphics.getDeltaTime() * speed;

		}

		private void scrollFlags() {
			++currentFlag;

			if (currentFlag >= locale.getFlags().size) currentFlag = 0;

			nextFlag = currentFlag + 1;

			if (nextFlag >= locale.getFlags().size) nextFlag = 0;
		}

	}

}
