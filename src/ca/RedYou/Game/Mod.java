package ca.RedYou.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import ca.RedYou.Game.Controller.EntityController;

public abstract class Mod {

	public Mod(File gameFolder) {
	}

	public abstract String name();

	public abstract int version();

	public abstract void start();

	public abstract void end();

	public File getSource(String name) {
		return new File("source/" + name);
	}

	public EntityController.Key setEntity(Entity ent) {
		return EntityController.getInstance().setEntity(this, ent.name(), ent);
	}

	public Entity getEntity(EntityController.Key key) {
		return EntityController.getInstance().getEntity(key);
	}

	public static Mod load(File f) throws Exception {
		URL[] urls = { new URL("jar:file:" + f.getAbsolutePath() + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		BufferedReader reader = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("Mod.txt")));

		String line = reader.readLine();

		Class<?> main = cl.loadClass(line.substring("Main:".length()));

		Object m = main.getConstructor(File.class).newInstance(f.getParentFile().getParentFile());

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
