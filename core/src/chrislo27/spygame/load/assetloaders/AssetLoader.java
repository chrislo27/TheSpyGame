package chrislo27.spygame.load.assetloaders;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import ionium.animation.Animation;
import ionium.registry.handler.IAssetLoader;
import ionium.util.AssetMap;

public class AssetLoader implements IAssetLoader {

	private void loadFlags(AssetManager manager){
		manager.load(AssetMap.add("flag_unknown", "images/flags/unknown.png"), Texture.class);
		manager.load(AssetMap.add("flag_canada", "images/flags/canada.png"), Texture.class);
		manager.load(AssetMap.add("flag_usa", "images/flags/usa.png"), Texture.class);
		manager.load(AssetMap.add("flag_gb", "images/flags/gb.png"), Texture.class);
		manager.load(AssetMap.add("flag_france", "images/flags/france.png"), Texture.class);
		manager.load(AssetMap.add("flag_quebec", "images/flags/quebec.png"), Texture.class);
	}
	
	private void loadDebugThings(AssetManager manager){
		manager.load(AssetMap.add("test_starrysky", "images/starrysky.png"), Texture.class);
	}
	
	private void loadEntities(AssetManager manager){
		manager.load(AssetMap.add("entity_player_full", "images/entity/player.png"), Texture.class);
		manager.load(AssetMap.add("entity_player_bottom", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_middle", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_top", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_gears", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_leftGear", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_rightGear", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_head", "images/entity/player/"), Texture.class);
		manager.load(AssetMap.add("entity_player_headWithoutGears", "images/entity/player/"), Texture.class);
		
	}
	
	@Override
	public void addManagedAssets(AssetManager manager) {
		loadFlags(manager);
		loadEntities(manager);
		loadDebugThings(manager);
	}

	@Override
	public void addUnmanagedTextures(HashMap<String, Texture> textures) {
	}

	@Override
	public void addUnmanagedAnimations(HashMap<String, Animation> animations) {
	}

}