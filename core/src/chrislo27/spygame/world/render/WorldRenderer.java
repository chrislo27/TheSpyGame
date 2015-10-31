package chrislo27.spygame.world.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import chrislo27.spygame.Main;
import chrislo27.spygame.world.World;
import ionium.registry.GlobalVariables;

public class WorldRenderer {

	public World world;
	public SpriteBatch batch;
	public Main main;
	public OrthographicCamera camera;

	public WorldRenderer(World world, Main main) {
		this.world = world;
		this.main = main;
		this.batch = main.batch;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, GlobalVariables.getInt("DEFAULT_WIDTH") * 1f / World.PX_UNIT,
				GlobalVariables.getInt("DEFAULT_HEIGHT") * 1f / World.PX_UNIT);
	}

}
