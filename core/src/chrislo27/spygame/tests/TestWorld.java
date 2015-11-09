package chrislo27.spygame.tests;

import chrislo27.spygame.entity.EntityPlayer;
import chrislo27.spygame.world.World;

public class TestWorld extends World{

	public TestWorld(float sizex, float sizey) {
		super(sizex, sizey);
		
		EntityPlayer player = new EntityPlayer(this, 4, 10);
		
		entities.add(player);
		
		currentFocusedEntity = player;
	}

}
