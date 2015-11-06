package chrislo27.spygame.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import chrislo27.spygame.entity.render.EntityRenderer;
import chrislo27.spygame.util.BodyType;
import chrislo27.spygame.util.Bounds;
import chrislo27.spygame.util.Bounds.Boundable;
import chrislo27.spygame.util.CollisionAxis;
import chrislo27.spygame.world.World;
import ionium.registry.GlobalVariables;
import ionium.templates.Main;
import ionium.util.Direction;

public abstract class Entity implements Boundable {

	public Bounds bounds = new Bounds();
	public float veloX = 0;
	public float veloY = 0;
	public float lerpX = 0;
	public float lerpY = 0;
	protected World world;

	protected EntityRenderer renderer;

	public Entity(World world, float x, float y) {
		this.world = world;

		bounds.x = x;
		bounds.y = y;
	}

	@Override
	public Bounds getBounds() {
		return bounds;
	}
	
	public void setPosition(float x, float y){
		bounds.x = x;
		bounds.y = y;
		lerpX = x;
		lerpY = y;
	}

	public EntityRenderer getRenderer() {
		return renderer;
	}

	/**
	 * Returns true if the given entity is allowed to collide into this one.
	 * @param e
	 * @return
	 */
	public boolean canEntityCollideIntoMe(Entity e) {
		return true;
	}

	public BodyType getBodyType() {
		return BodyType.DYNAMIC;
	}

	public void tickUpdate() {
		if (getBodyType() == BodyType.DYNAMIC) {
			if(getEntityColliding(Direction.DOWN) == null){
				veloY -= world.gravity;
			}
			
			updatePositionFromVelocity(CollisionAxis.X);
			updatePositionFromVelocity(CollisionAxis.Y);
		}
	}

	private void updatePositionFromVelocity(CollisionAxis axis) {
		float oldVelocity = (axis == CollisionAxis.X ? veloX : veloY);
		float newVelocity = oldVelocity;
		int transformedVelo = (int) ((oldVelocity / GlobalVariables.getInt("TICKS"))
				* World.PX_UNIT);

		if (transformedVelo != 0) {
			Direction collisionDirection = (axis == CollisionAxis.X
					? (transformedVelo > 0 ? Direction.RIGHT : Direction.LEFT)
					: (transformedVelo > 0 ? Direction.UP : Direction.DOWN));

			for (int i = 0; i < Math.abs(transformedVelo); i++) {
				Entity e = getEntityColliding(collisionDirection);

				if (e != null) {
					newVelocity = 0;

					if (e == this && e.bounds.y < 0) {
						// hit floor, colliding with yourself
						e.bounds.y = 0;
					}

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
		Entity e;
		int transformedX = (int) (bounds.x * World.PX_UNIT);
		int transformedY = (int) (bounds.y * World.PX_UNIT);
		int transformedWidth = (int) (bounds.width * World.PX_UNIT);
		int transformedHeight = (int) (bounds.height * World.PX_UNIT);

		if (direction == Direction.DOWN) {
			if (transformedY <= ((int) (World.FLOOR * World.PX_UNIT))) {
				return this;
			}
		}

		for (int i = 0; i < world.entities.size; i++) {
			e = world.entities.get(i);

			if (e == this) continue;
			if (!e.canEntityCollideIntoMe(this)) continue;

			int eX = (int) (e.bounds.x * World.PX_UNIT);
			int eY = (int) (e.bounds.y * World.PX_UNIT);
			int eWidth = (int) (e.bounds.width * World.PX_UNIT);
			int eHeight = (int) (e.bounds.height * World.PX_UNIT);

			switch (direction) {
			case DOWN:
				// check if X position is within the other entity
				if (transformedX + transformedWidth >= eX && transformedX <= eX + eWidth) {
					// bottom side next to e's top side
					if (transformedY - 1 == (eY + eHeight)) {
						return e;
					}
				}
				break;
			case LEFT:
				// check if Y position is within the other entity
				if (transformedY + transformedHeight >= eY && transformedY <= eY + eHeight) {
					// left side next to e's right side
					if (transformedX - 1 == (eX + eWidth)) {
						return e;
					}
				}
				break;
			case RIGHT:
				// check if Y position is within the other entity
				if (transformedY + transformedHeight >= eY && transformedY <= eY + eHeight) {
					// right side next to e's left side
					if (transformedX + transformedWidth + 1 == eX) {
						return e;
					}
				}
				break;
			case UP:

				// check if X position is within the other entity
				if (transformedX + transformedWidth >= eX && transformedX <= eX + eWidth) {
					// top side next to e's bottom side
					if (transformedY + transformedHeight + 1 == eY) {
						return e;
					}
				}
				break;
			default:
				break;
			}

		}

		return null;
	}

}
