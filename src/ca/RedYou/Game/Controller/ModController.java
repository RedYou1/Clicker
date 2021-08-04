package ca.RedYou.Game.Controller;

import java.io.File;
import java.util.*;

import ca.RedYou.Game.Mod;

public class ModController {

	public ModController() {
		File f = new File("Mods");
		if (f.exists() && f.isDirectory())
			for (File mod : f.listFiles()) {
				try {
					setMod(Mod.load(mod));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	public void start() {
		for (Mod m : mods.values()) {
			m.start();
		}
	}

	public void stop() {
		for (Mod m : mods.values()) {
			m.stop();
		}
	}

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
