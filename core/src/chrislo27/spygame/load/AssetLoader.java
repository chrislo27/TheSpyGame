package chrislo27.spygame.load;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import ionium.animation.Animation;
import ionium.registry.handler.IAssetLoader;
import ionium.util.AssetMap;

public class AssetLoader implements IAssetLoader {

	@Override
	public void addManagedAssets(AssetManager manager) {
		manager.load(AssetMap.add("flag_unknown", "images/flags/unknown.png"), Texture.class);
		manager.load(AssetMap.add("flag_canada", "images/flags/canada.png"), Texture.class);
		manager.load(AssetMap.add("flag_usa", "images/flags/usa.png"), Texture.class);
		manager.load(AssetMap.add("flag_gb", "images/flags/gb.png"), Texture.class);
	}

	@Override
	public void addUnmanagedTextures(HashMap<String, Texture> textures) {
	}

	@Override
	public void addUnmanagedAnimations(HashMap<String, Animation> animations) {
	}

}