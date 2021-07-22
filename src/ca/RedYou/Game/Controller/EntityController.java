package ca.RedYou.Game.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.RedYou.Game.Entity;
import ca.RedYou.Game.Mod;

public class EntityController {

	private class Key {
		private Mod mod;
		private String name;

		public Key(Mod mod, String name) {
			if (mod == null)
				throw new NullPointerException("the mod is null");
			this.mod = mod;
			this.name = name;
		}

		public Mod getMod() {
			return mod;
		}

		public String getName() {
			return name;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Key) {
				Key k = (Key) o;
				return mod.equals(k.mod) && name.equals(k.name);
			}
			return false;
		}
	}

	private static final EntityController instance = new EntityController();
	private Map<Key, Entity> entities = new HashMap<>();

	// order
	private List<Entity> ents = new ArrayList<>();

	public static EntityController getInstance() {
		return instance;
	}

	public Entity[] getEntities() {
		return ents.toArray(new Entity[ents.size()]);
	}

	public void setEntity(Mod mod, String name, Entity ent) {
		Key key = new Key(mod, name);
		if (entities.containsKey(key)) {
			int index = ents.indexOf(entities.get(key));
			ents.remove(index);
			ents.add(index, ent);
			entities.replace(key, ent);
		} else {
			ents.add(ent);
			entities.put(key, ent);
		}
	}

	public Entity getEntity(Mod mod, String name) {
		return entities.get(new Key(mod, name));
	}

	public List<Entity> getEntities(Mod mod) {
		List<Entity> ents = new ArrayList<>();
		for (Entry<Key, Entity> a : entities.entrySet()) {
			if (a.getKey().getMod().equals(mod)) {
				ents.add(a.getValue());
			}
		}
		return ents;
	}

	public List<Entity> getEntities(String name) {
		List<Entity> ents = new ArrayList<>();
		for (Entry<Key, Entity> a : entities.entrySet()) {
			if (a.getKey().getName().equals(name)) {
				ents.add(a.getValue());
			}
		}
		return ents;
	}
}
