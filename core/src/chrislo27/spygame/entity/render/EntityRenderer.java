package chrislo27.spygame.entity.render;

import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.render.WorldRenderer;

public abstract class EntityRenderer<T extends Entity> {
	
	T entity;
	
	public EntityRenderer(T en){
		entity = en;
	}
	
	public abstract void render(WorldRenderer renderer);
	
	public abstract void renderUpdate(WorldRenderer renderer);
	
}
