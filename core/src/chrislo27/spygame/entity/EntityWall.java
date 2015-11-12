package chrislo27.spygame.entity;

import chrislo27.spygame.entity.render.RenderWall;
import chrislo27.spygame.util.BodyType;
import chrislo27.spygame.world.World;

public class EntityWall extends Entity{

	public EntityWall(World world, float x, float y) {
		super(world, x, y);
		
		this.renderer = new RenderWall(this);
	}
	
	@Override
	public BodyType getBodyType(){
		return BodyType.STATIC;
	}

}
