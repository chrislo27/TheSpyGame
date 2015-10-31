package chrislo27.spygame.entity;

import chrislo27.spygame.entity.render.RenderPlayer;
import chrislo27.spygame.world.World;

public class EntityPlayer extends Entity{

	public EntityPlayer(World world) {
		super(world);
		
		this.renderer = new RenderPlayer(this);
	}

}
