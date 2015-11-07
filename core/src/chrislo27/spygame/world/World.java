package chrislo27.spygame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.entity.Entity;

public class World {

	public static final int PX_UNIT = 64;
	public static final float UNIT_PX = (1f / PX_UNIT);

	public static final float FLOOR = 0f;
	public static final float DRAG = 10f;

	public float gravity = 1.5f;

	public Array<Entity> entities = new Array<>();

	float sizex, sizey;

	public World(float sizex, float sizey) {
		this.sizex = sizex;
		this.sizey = sizey;

		reset();
	}

	public void reset() {
		entities.clear();
	}

	public void tickUpdate() {
		for (int i = 0; i < entities.size; i++) {
			Entity e = entities.get(i);

			e.tickUpdate();
		}
	}

	public void renderUpdate() {

	}

	public void inputUpdate() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			entities.get(0).veloX = MathUtils.clamp(entities.get(0).veloX
					- (Gdx.graphics.getDeltaTime() * 50f) - (Gdx.graphics.getDeltaTime() * DRAG),
					-5f, 5f);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			entities.get(0).veloX = MathUtils.clamp(entities.get(0).veloX
					+ (Gdx.graphics.getDeltaTime() * 50f) + (Gdx.graphics.getDeltaTime() * DRAG),
					-5f, 5f);
		}

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			entities.get(0).veloY = 15f;
		}
	}

}
