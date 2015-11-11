package chrislo27.spygame.screen;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;
import ionium.screen.Updateable;

public class LevelEditorScreen extends Updateable<Main>{

	World world;
	WorldRenderer renderer;
	
	public LevelEditorScreen(Main m) {
		super(m);
		
		renderer = new WorldRenderer(null, main);
	}

	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void renderUpdate() {
		renderer.renderUpdate();
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
