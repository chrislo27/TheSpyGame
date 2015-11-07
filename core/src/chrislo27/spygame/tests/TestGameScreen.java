package chrislo27.spygame.tests;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;
import ionium.screen.Updateable;

public class TestGameScreen extends Updateable{

	World world;
	WorldRenderer renderer;
	
	Main main;
	
	public TestGameScreen(Main m) {
		super(m);
		
		this.main = m;
		
		world = new TestWorld(100, 50);
		renderer = new WorldRenderer(world, main);
	}

	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void renderUpdate() {
		world.inputUpdate();
		world.renderUpdate();
	}

	@Override
	public void tickUpdate() {
		world.tickUpdate();
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
