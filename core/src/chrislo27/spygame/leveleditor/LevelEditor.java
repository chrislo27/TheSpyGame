package chrislo27.spygame.leveleditor;

import chrislo27.spygame.Main;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;

public class LevelEditor {

	public final Main main;

	public World world;
	public WorldRenderer renderer;

	public LevelEditor(Main m, World w) {
		this.main = m;
		this.world = w;

		renderer = new WorldRenderer(world, main);
	}

	public void render() {
		renderer.render();
	}

	public void renderUpdate() {
		renderer.renderUpdate();
	}

	public void inputUpdate() {

	}

	public void tickUpdate() {

	}

}
