package chrislo27.spygame.world.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import chrislo27.spygame.Main;
import chrislo27.spygame.world.World;

public class WorldRenderer {

	public World world;
	public SpriteBatch batch;
	public Main main;
	
	public WorldRenderer(World world, Main main){
		this.world = world;
		this.main = main;
		this.batch = main.batch;
	}
	
}
