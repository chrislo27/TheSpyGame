package chrislo27.spygame.leveleditor;

import chrislo27.spygame.Main;
import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.World;
import chrislo27.spygame.world.render.WorldRenderer;

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

			if (e != editor.currentSelected) e.getRenderer().render(this);
		}

		if (editor.currentSelected != null) editor.currentSelected.getRenderer().render(this);
	}

}
