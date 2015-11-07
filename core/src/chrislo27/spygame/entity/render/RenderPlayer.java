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
		entity.interpolatePosition();
		
		// the head is where the collision box top side is always
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_headWithoutGears"), entity.lerpX, entity.lerpY, 1, 1);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_leftGear"), entity.lerpX, entity.lerpY, 1, 1);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_rightGear"), entity.lerpX, entity.lerpY, 1, 1);
		
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_top"), entity.lerpX, entity.lerpY, 1, 1);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_middle"), entity.lerpX, entity.lerpY, 1, 1);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_bottom"), entity.lerpX, entity.lerpY, 1, 1);
		
	}

	

}
