package chrislo27.spygame.screen;

import com.badlogic.gdx.utils.Array;

import ionium.screen.Updateable;
import ionium.templates.Main;


public class ScreenBase extends Updateable {

	public chrislo27.spygame.Main main;
	
	public ScreenBase(chrislo27.spygame.Main m) {
		super(m);
		
		this.main = m;
	}

	@Override
	public void render(float delta) {
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
