package chrislo27.spygame.tests;

import chrislo27.spygame.entity.EntityPlayer;
import chrislo27.spygame.world.World;

public class TestWorld extends World{

	public TestWorld(float sizex, float sizey) {
		super(sizex, sizey);
		
		entities.add(new EntityPlayer(this, 5, 15));
	}

}
