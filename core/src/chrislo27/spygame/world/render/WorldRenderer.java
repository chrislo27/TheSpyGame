package chrislo27.spygame.world.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import chrislo27.spygame.Main;
import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.World;
import ionium.registry.AssetRegistry;
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
		camera.update();
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(AssetRegistry.getTexture("test_starrysky"), 0, 0, camera.viewportWidth,
				camera.viewportHeight);

		batch.flush();

		for (int i = 0; i < world.entities.size; i++) {
			Entity e = world.entities.get(i);

			e.getRenderer().renderUpdate(this);
			
			e.getRenderer().render(this);
		}

		batch.end();
		batch.setProjectionMatrix(main.camera.combined);

		if (Gdx.input.isKeyPressed(Keys.D)) {
			camera.translate(4f * Gdx.graphics.getDeltaTime(), 0);
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			camera.translate(-4f * Gdx.graphics.getDeltaTime(), 0);
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			camera.translate(0, 4f * Gdx.graphics.getDeltaTime());
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			camera.translate(0, -4f * Gdx.graphics.getDeltaTime());
		}

		if (Gdx.input.isKeyPressed(Keys.Z)) {
			camera.position.z += 0.5f * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Keys.X)) {
			camera.position.z += -0.5f * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.Q)){
			camera.rotate(-90f * Gdx.graphics.getDeltaTime());
		}else if(Gdx.input.isKeyPressed(Keys.E)){
			camera.rotate(90f * Gdx.graphics.getDeltaTime());
		}

		camera.update();
	}

}
