package chrislo27.spygame.entity;

import chrislo27.spygame.util.Bounds;
import chrislo27.spygame.util.Bounds.Boundable;
import chrislo27.spygame.world.World;
import ionium.registry.GlobalVariables;
import ionium.util.Direction;

public abstract class Entity implements Boundable {

	public Bounds bounds = new Bounds();
	public float veloX = 0;
	public float veloY = 0;
	protected World world;

	public Entity(World world) {
		this.world = world;
	}

	@Override
	public Bounds getBounds() {
		return bounds;
	}

	public void tickUpdate() {
		updatePositionFromVelocity();
	}

	public void updatePositionFromVelocity() {
		int transformedVeloX = ((int) ((veloX / GlobalVariables.getInt("TICKS")) * World.PX_UNIT));
		int transformedVeloY = ((int) ((veloY / GlobalVariables.getInt("TICKS")) * World.PX_UNIT));

		if (transformedVeloX != 0) {
			for (int i = 0; i < Math.abs(transformedVeloX); i++) {
				Entity e = getEntityColliding(
						(transformedVeloX > 0 ? Direction.RIGHT : Direction.LEFT));

				if (e != null) {
					veloX = 0;
					break;
				} else {
					bounds.x += Math.signum(transformedVeloX) * World.UNIT_PX;
				}
			}
		} else {
			veloX = 0;
		}

		if (transformedVeloY != 0) {
			for (int i = 0; i < Math.abs(transformedVeloY); i++) {
				Entity e = getEntityColliding(
						(transformedVeloY > 0 ? Direction.UP : Direction.DOWN));

				if (e != null) {
					veloY = 0;
					break;
				} else {
					bounds.y += Math.signum(transformedVeloY) * World.UNIT_PX;
				}
			}
		} else {
			veloY = 0;
		}
	}

	public Entity getEntityColliding(Direction direction) {
		if (world.entities.size <= 1) return null;

		Entity e;
		int transformedX = ((int) bounds.x * World.PX_UNIT);
		int transformedY = ((int) bounds.y * World.PX_UNIT);
		int transformedWidth = ((int) bounds.width * World.PX_UNIT);
		int transformedHeight = ((int) bounds.height * World.PX_UNIT);

		for (int i = 0; i < world.entities.size; i++) {
			e = world.entities.get(i);

			if (e == this) continue;

			int eX = ((int) e.bounds.x * World.PX_UNIT);
			int eY = ((int) e.bounds.y * World.PX_UNIT);
			int eWidth = ((int) e.bounds.width * World.PX_UNIT);
			int eHeight = ((int) e.bounds.height * World.PX_UNIT);

			switch (direction) {
			case DOWN:
				// bottom side next to e's top side
				if (transformedY - 1 == (eY + eHeight)) {
					return e;
				}
				break;
			case LEFT:
				// left side next to e's right side
				if (transformedX - 1 == (eX + eWidth)) {
					return e;
				}
				break;
			case RIGHT:
				// right side next to e's left side
				if (transformedX + transformedWidth + 1 == eX) {
					return e;
				}
				break;
			case UP:
				// top side next to e's bottom side
				if (transformedY + transformedHeight + 1 == eY) {
					return e;
				}
				break;
			default:
				break;
			}

		}

		return null;
	}

}
