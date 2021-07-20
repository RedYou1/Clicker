package ca.RedYou.Game.Controller;

import java.util.*;

import ca.RedYou.Game.Mod;

public class ModController {

	private static final ModController instance = new ModController();

	public static ModController getInstance() {
		return instance;
	}

	private Map<String, Mod> mods = new HashMap<>();

	public void setMod(Mod mod) {
		if (mods.containsKey(mod.name()))
			mods.replace(mod.name(), mod);
		else
			mods.put(mod.name(), mod);
	}

	public Mod getMod(String name) {
		return mods.get(name);
	}
}
