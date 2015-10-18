package chrislo27.spygame.load;

import java.util.Locale;

import chrislo27.spygame.screen.LanguageScreen;
import ionium.registry.ScreenRegistry;
import ionium.util.i18n.Localization;
import ionium.util.i18n.NamedLocale;

public class LanguageAdder {

	public static final void addLanguages() {
//		Localization.instance().addBundle(
//				new NamedLocale("French", new Locale("fr"), "flag_france", "flag_quebec"));

		LanguageScreen langScreen = ScreenRegistry.get("langSelect", LanguageScreen.class);
		if (langScreen != null) {
			langScreen.addLanguageButtons();
		}
	}

}
