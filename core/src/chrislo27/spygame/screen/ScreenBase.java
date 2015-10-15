package chrislo27.spygame.screen;

import com.badlogic.gdx.utils.Array;

import chrislo27.spygame.Main;
import ionium.screen.Updateable;

public abstract class ScreenBase extends Updateable {

	public chrislo27.spygame.Main main;

	public ScreenBase(Main m) {
		super(m);

		this.main = m;
	}

}
