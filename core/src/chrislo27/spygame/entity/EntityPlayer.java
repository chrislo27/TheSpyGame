package chrislo27.spygame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import chrislo27.spygame.entity.render.RenderPlayer;
import chrislo27.spygame.world.World;
import ionium.util.Direction;

public class EntityPlayer extends Entity implements Controllable {

	public EntityPlayer(World world, float x, float y) {
		super(world, x, y);

		this.renderer = new RenderPlayer(this);
	}

	@Override
	public void onLeft() {
		lateralMovement(-1);
	}

	@Override
	public void onRight() {
		lateralMovement(1);
	}

	@Override
	public void onUp() {
		onJump();
	}

	@Override
	public void onDown() {
	}

	@Override
	public void onJump() {
		if (this.getEntityColliding(Direction.DOWN) != null) {
			veloY = 15f;
		}
	}

	private void lateralMovement(float sign) {
		veloX = MathUtils.clamp(
				veloX + (Gdx.graphics.getDeltaTime() * (50f + World.DRAG) * Math.signum(sign)), -5f,
				5f);
	}

}
