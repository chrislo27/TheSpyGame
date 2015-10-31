package chrislo27.spygame.world;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.entity.Entity;

public class World {

	public static final int PX_UNIT = 64;
	public static final float UNIT_PX = (1f / PX_UNIT);
	
	public static final float FLOOR = 0f;

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
	
}