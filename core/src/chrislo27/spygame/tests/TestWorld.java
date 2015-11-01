package chrislo27.spygame.tests;

import chrislo27.spygame.entity.EntityPlayer;
import chrislo27.spygame.world.World;

public class TestWorld extends World{

	public TestWorld(float sizex, float sizey) {
		super(sizex, sizey);
		
		entities.add(new EntityPlayer(this, 0, 10));
		entities.add(new EntityPlayer(this, 0, 8));
		entities.add(new EntityPlayer(this, 0, 6));
		entities.add(new EntityPlayer(this, 0, 4));
		
		entities.add(new EntityPlayer(this, 4, 10));
		entities.add(new EntityPlayer(this, 4, 8));
		entities.add(new EntityPlayer(this, 2, 6));
		entities.add(new EntityPlayer(this, 8, 4));
	}

}
