package chrislo27.spygame.leveleditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;

import chrislo27.spygame.Main;
import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.World;
import ionium.util.MathHelper;
import ionium.util.Utils;

public class LevelEditor {

	public final Main main;

	public World world;
	public LevelEditorRenderer renderer;

	public Entity currentSelected;

	private Vector3 screenCoords = new Vector3();

	public LevelEditor(Main m, World w) {
		this.main = m;
		this.world = w;

		renderer = new LevelEditorRenderer(this);
	}

	public void render() {
		renderer.render();
	}

	public void renderUpdate() {
		renderer.renderUpdate();
	}

	public void inputUpdate() {
		if (Utils.isButtonJustPressed(Buttons.LEFT)) {
			Entity e = null;
			Entity newSelected = null;

			for (int i = world.entities.size - 1; i >= 0; i--) {
				setScreenCoordsVector();
				e = world.entities.get(i);

				if (MathHelper.isPointInRectangle(e.bounds.x, e.bounds.y, e.bounds.width,
						e.bounds.height, screenCoords.x, screenCoords.y)) {
					newSelected = e;

					break;
				}
			}

			if (newSelected != currentSelected) {
				if (currentSelected != null) {
					world.entities.removeValue(currentSelected, true);
					world.entities.insert(world.entities.size, currentSelected);
				}
				
				currentSelected = newSelected;
			}

		}
	}

	public void tickUpdate() {

	}

	private void setScreenCoordsVector() {
		screenCoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	}

}
