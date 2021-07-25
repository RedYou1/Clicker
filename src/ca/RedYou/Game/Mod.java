package ca.RedYou.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import ca.RedYou.Game.Controller.EntityController;

public abstract class Mod {

	protected File modFolder;

	public Mod(File modFolder) {
		this.modFolder = modFolder;
	}

	public abstract String name();

	public abstract int version();

	public abstract void start();

	public abstract void end();

	public File getSource(String name) {
		return new File(modFolder.getAbsolutePath() + "/source/" + name);
	}

	public Entity setEntity(Entity ent) {
		EntityController.getInstance().setEntity(this, ent.name(), ent);
		return ent;
	}

	public static Mod load(File f) throws Exception {
		URL[] urls = { new URL("jar:file:" + f.getAbsolutePath() + "/mod.jar" + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		BufferedReader reader = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("Mod.txt")));

		String line = reader.readLine();

		Class<?> main = cl.loadClass(line.substring("Main:".length()));

		Object m = main.getConstructor(File.class).newInstance(f);

		return (Mod) m;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Mod) {
			Mod m = (Mod) o;
			return name().equalsIgnoreCase(m.name()) && version() == m.version();
		}
		return false;
	}
}
