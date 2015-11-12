package chrislo27.spygame.entity.render;

import chrislo27.spygame.entity.EntityWall;
import chrislo27.spygame.world.render.WorldRenderer;

public class RenderWall extends EntityRenderer<EntityWall>{

	public RenderWall(EntityWall en) {
		super(en);
	}

	@Override
	public void render(WorldRenderer renderer) {
		entity.interpolatePosition();
	}

	@Override
	public void renderUpdate(WorldRenderer renderer) {
	}

}
