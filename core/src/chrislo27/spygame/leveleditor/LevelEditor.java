package chrislo27.spygame.leveleditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import chrislo27.spygame.entity.Entity;
import chrislo27.spygame.world.World;
import ionium.util.MathHelper;
import ionium.util.Utils;

public class LevelEditor {

	public final Main main;

	public World world;
	public LevelEditorRenderer renderer;

	public Array<Entity> currentSelected = new Array<>();
	public Rectangle selectionBox = new Rectangle(-1, -1, 0, 0);

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
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			setScreenCoordsVector();
			renderer.camera.unproject(screenCoords);

			if (selectionBox.x < 0 || selectionBox.y < 0) {
				selectionBox.x = screenCoords.x;
				selectionBox.y = screenCoords.y;
			}

			selectionBox.width = screenCoords.x - selectionBox.x;
			selectionBox.height = screenCoords.y - selectionBox.y;
		}

		if (Utils.isButtonJustReleased(Buttons.LEFT)) {
			Entity e = null;

			for (int i = 0; i < currentSelected.size; i++) {
				e = currentSelected.get(i);

				world.entities.removeIndex(i);
				world.entities.insert(world.entities.size, e);
			}

			currentSelected.clear();

			for (int i = world.entities.size - 1; i >= 0; i--) {
				e = world.entities.get(i);

				if (MathHelper.intersects(e.bounds.x, e.bounds.y, e.bounds.width, e.bounds.height,
						selectionBox.x, selectionBox.y, selectionBox.width, selectionBox.height)) {
					currentSelected.add(e);
				}
			}
		}

		if (Gdx.input.isButtonPressed(Buttons.LEFT) == false) {
			selectionBox.x = -1;
			selectionBox.y = -1;
		}
	}

	public void tickUpdate() {

	}

	private void setScreenCoordsVector() {
		screenCoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	}

}
