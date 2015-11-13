package chrislo27.spygame.screen;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import chrislo27.spygame.leveleditor.LevelEditor;
import chrislo27.spygame.tests.TestWorld;
import ionium.screen.Updateable;

public class LevelEditorScreen extends Updateable<Main> {

	LevelEditor levelEditor;

	public LevelEditorScreen(Main m) {
		super(m);

		levelEditor = new LevelEditor(main, new TestWorld(100, 50));
	}

	@Override
	public void render(float delta) {
		levelEditor.render();
	}

	@Override
	public void renderUpdate() {
		levelEditor.inputUpdate();
		levelEditor.renderUpdate();
	}

	@Override
	public void tickUpdate() {
		levelEditor.tickUpdate();
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
