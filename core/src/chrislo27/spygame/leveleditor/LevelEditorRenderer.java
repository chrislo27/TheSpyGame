package chrislo27.spygame.leveleditor;

import chrislo27.spygame.Main;
import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;
import ionium.util.MathHelper;

public class LevelEditorRenderer extends WorldRenderer {

	private final LevelEditor editor;

	public LevelEditorRenderer(LevelEditor le) {
		super(le.world, le.main);

		editor = le;
	}

	@Override
	public void renderUpdate() {
		if (world == null) {
			return;
		}
	}

	@Override
	protected void renderAndRenderUpdateAllEntities() {
		for (int i = 0; i < world.entities.size; i++) {
			Entity e = world.entities.get(i);

			e.lerpX = e.bounds.x;
			e.lerpY = e.bounds.y;

			if (e != editor.currentSelected) {
				e.getRenderer().render(this);
			}
		}

		if (editor.currentSelected != null) {
			// normal render
			editor.currentSelected.getRenderer().render(this);

			// glowing triangle save
			batch.setColor(0, 1, 1, (MathHelper.getTriangleWave(1f) * 0.5f) + 0.25f);
			editor.currentSelected.getRenderer().render(this);

			// bounds outline
			batch.setColor(0, 1, 1, 1);
			Main.drawRect(batch, editor.currentSelected.bounds.x, editor.currentSelected.bounds.y,
					editor.currentSelected.bounds.width, editor.currentSelected.bounds.height, World.UNIT_PX * 2);
			batch.setColor(1, 1, 1, 1);
		}
	}

}
