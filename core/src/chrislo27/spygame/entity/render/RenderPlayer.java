package chrislo27.spygame.entity.render;

import chrislo27.spygame.entity.EntityPlayer;
import chrislo27.spygame.world.render.WorldRenderer;
import ionium.registry.AssetRegistry;

public class RenderPlayer extends EntityRenderer<EntityPlayer>{

	public RenderPlayer(EntityPlayer en) {
		super(en);
	}

	@Override
	public void render(WorldRenderer renderer) {
		renderer.batch.draw(AssetRegistry.getTexture("player_test"), entity.bounds.x, entity.bounds.y, 1, 1);
	}

	

}
