package ca.RedYou.Game.Controller;

import java.util.HashMap;
import java.util.Map;

import ca.RedYou.Game.*;

public class Player {

	private static final Player instance = new Player();

	private Map<Entity, Quantity> entities = new HashMap<>();
	private Quantity money = new Quantity();

	public static Player getInstance() {
		return instance;
	}

	public Quantity getEntityQuantity(Entity ent) {
		if (!entities.containsKey(ent))
			entities.put(ent, new Quantity());
		return entities.get(ent);
	}

	public Quantity getMoney() {
		return money;
	}
}
