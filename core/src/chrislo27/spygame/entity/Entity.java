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
		updatePositionFromVelocity(CollisionAxis.X);
		updatePositionFromVelocity(CollisionAxis.Y);
	}

	private void updatePositionFromVelocity(CollisionAxis axis) {
		int transformedVelo = ((int) (((axis == CollisionAxis.X ? veloX : veloY)
				/ GlobalVariables.getInt("TICKS")) * World.PX_UNIT));

		float newVelocity = (axis == CollisionAxis.X ? veloX : veloY);

		if (transformedVelo != 0) {
			Direction collisionDirection = axis == CollisionAxis.X
					? (transformedVelo > 0 ? Direction.RIGHT : Direction.LEFT)
					: (transformedVelo > 0 ? Direction.UP : Direction.DOWN);

			for (int i = 0; i < Math.abs(transformedVelo); i++) {
				Entity e = getEntityColliding(collisionDirection);

				if (e != null) {
					newVelocity = 0;
					break;
				} else {
					float amt = Math.signum(transformedVelo) * World.UNIT_PX;
					if (axis == CollisionAxis.X) {
						bounds.x += amt;
					} else if (axis == CollisionAxis.Y) {
						bounds.y += amt;
					}
				}
			}
		} else {
			newVelocity = 0;
		}

		if (axis == CollisionAxis.X) {
			veloX = newVelocity;
		} else if (axis == CollisionAxis.Y) {
			veloY = newVelocity;
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

	private enum CollisionAxis {
		X, Y;
	}

}
