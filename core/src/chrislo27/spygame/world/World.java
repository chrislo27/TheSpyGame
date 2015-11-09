package chrislo27.spygame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.entity.Controllable;
import chrislo27.spygame.entity.Entity;

public class World {

	public static final int PX_UNIT = 64;
	public static final float UNIT_PX = (1f / PX_UNIT);

	public static final float FLOOR = 0f;
	public static final float DRAG = 10f;

	public float gravity = 1.5f;

	public Array<Entity> entities = new Array<>();

	public final float sizex, sizey;

	public Entity currentFocusedEntity = null;

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

		if (currentFocusedEntity != null) {
			if(currentFocusedEntity instanceof Controllable){
				if (Gdx.input.isKeyPressed(Keys.LEFT)) {
					((Controllable) currentFocusedEntity).onLeft();
				} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
					((Controllable) currentFocusedEntity).onRight();
				}

				if (Gdx.input.isKeyPressed(Keys.UP)) {
					((Controllable) currentFocusedEntity).onUp();
				}else if(Gdx.input.isKeyPressed(Keys.DOWN)){
					((Controllable) currentFocusedEntity).onDown();
				}
			}
		}
	}

}
