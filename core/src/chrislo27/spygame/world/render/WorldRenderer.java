package chrislo27.spygame.world.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

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

	private Vector3 cameraTarget = new Vector3();
	private final float cameraSpeed = 5f;

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

		if (world == null) {
			return;
		}

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(AssetRegistry.getTexture("test_starrysky"), 0, 0, camera.viewportWidth,
				camera.viewportHeight);

		batch.flush();

		renderAndRenderUpdateAllEntities();

		batch.end();
		batch.setProjectionMatrix(main.camera.combined);

	}

	public void renderUpdate() {
		if (world == null) {
			return;
		}
		
		centerCameraOnEntity(world.currentFocusedEntity);
	}
	
	protected void centerCameraOnEntity(Entity e){
		Entity player = e;
		if (player != null) {
			cameraTarget.set(player.bounds.x + player.bounds.width / 2,
					player.bounds.y + player.bounds.height / 2, 0);
		}

		camera.position.interpolate(cameraTarget, cameraSpeed * Gdx.graphics.getDeltaTime(),
				Interpolation.linear);

		camera.position.x = MathUtils.clamp(camera.position.x, 0 + camera.viewportWidth / 2f,
				world.sizex - camera.viewportWidth / 2);
		camera.position.y = MathUtils.clamp(camera.position.y, 0 + camera.viewportHeight / 2f,
				world.sizey - camera.viewportHeight / 2);

		camera.update();
	}
	
	protected void renderAndRenderUpdateAllEntities(){
		for (int i = 0; i < world.entities.size; i++) {
			Entity e = world.entities.get(i);

			e.getRenderer().renderUpdate(this);

			e.getRenderer().render(this);
		}
	}

}
