package chrislo27.spygame.entity.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import chrislo27.spygame.entity.EntityPlayer;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;
import ionium.registry.AssetRegistry;

public class RenderPlayer extends EntityRenderer<EntityPlayer> {

	private final float padding = 8;
	private final float bottomPieceMax = 24 - padding;
	private final float middlePieceMax = 18 - padding;
	private final float topPieceMax = 15 - padding;
	private final float maxStretch = 10;

	private final float endOfPiecesScale = 5f;
	private final float stretchScale = 5f;
	private final float stretchMorePer = 1.25f;

	private float piecesPositionX = 0f;
	private float piecesPositionY = 0f;

	private boolean facingLeft = false;

	public RenderPlayer(EntityPlayer en) {
		super(en);
	}

	@Override
	public void render(WorldRenderer renderer) {
		// the head is where the collision box top side is always
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_headWithoutGears"),
				entity.lerpX, entity.lerpY, entity.bounds.width, entity.bounds.height);

		//		renderer.batch.draw(AssetRegistry.getTexture("entity_player_leftGear"), entity.lerpX,
		//				entity.lerpY, entity.bounds.width, entity.bounds.height);
		//		renderer.batch.draw(AssetRegistry.getTexture("entity_player_rightGear"), entity.lerpX,
		//				entity.lerpY, entity.bounds.width, entity.bounds.height);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_gears"),
				entity.lerpX + (facingLeft ? entity.bounds.width : 0), entity.lerpY,
				entity.bounds.width * (facingLeft ? -1 : 1), entity.bounds.height);

		renderer.batch.draw(AssetRegistry.getTexture("entity_player_top"),
				entity.lerpX + (topPieceMax * World.UNIT_PX * piecesPositionX),
				entity.lerpY
						+ (maxStretch * World.UNIT_PX * piecesPositionY * (stretchMorePer * 1f)),
				entity.bounds.width, entity.bounds.height);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_middle"),
				entity.lerpX + (middlePieceMax * World.UNIT_PX * piecesPositionX),
				entity.lerpY
						+ (maxStretch * World.UNIT_PX * piecesPositionY * (stretchMorePer * 2f)),
				entity.bounds.width, entity.bounds.height);
		renderer.batch.draw(AssetRegistry.getTexture("entity_player_bottom"),
				entity.lerpX + (bottomPieceMax * World.UNIT_PX * piecesPositionX),
				entity.lerpY
						+ (maxStretch * World.UNIT_PX * piecesPositionY * (stretchMorePer * 3f)),
				entity.bounds.width, entity.bounds.height);

	}

	@Override
	public void renderUpdate(WorldRenderer renderer) {
		entity.interpolatePosition();

		if (entity.veloX < 0) {
			facingLeft = true;
		} else if (entity.veloX > 0) {
			facingLeft = false;
		}

		updatePosX();
		updatePosY();
	}

	private void updatePosX() {
		float target = MathUtils.clamp(-entity.veloX, -endOfPiecesScale, endOfPiecesScale)
				/ endOfPiecesScale;

		piecesPositionX += (target - piecesPositionX) * Gdx.graphics.getDeltaTime() * 10f;

		piecesPositionX = MathUtils.clamp(piecesPositionX, -1f, 1f);
	}

	private void updatePosY() {
		float target = MathUtils.clamp(entity.veloY, 0, stretchScale) / stretchScale;
		target *= -1;

		piecesPositionY += (target - piecesPositionY) * Gdx.graphics.getDeltaTime() * 10f;

		piecesPositionY = MathUtils.clamp(piecesPositionY, -1f, 1f);
	}

}
