package ca.RedYou.Game.Controller;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

import ca.RedYou.Game.*;

public class UpgradeController {
	private final static UpgradeController instance = new UpgradeController();

	public static UpgradeController getInstance() {
		return instance;
	}

	private Map<Upgrade, Function<Void, Boolean>> upgrades = new HashMap<>();
	private List<Upgrade> see = new ArrayList<>();

	public void addUpgrade(Function<Void, Boolean> necessity, Upgrade up) {
		upgrades.put(up, necessity);
	}

	public void buy(Upgrade up) {
		if (upgrades.containsKey(up) && upgrades.get(up).apply(null)) {
			if (Player.getInstance().getMoney().compareTo(up.price()) > -1) {
				Player.getInstance().getMoney().sub(new Quantity(up.price()));
				Main.last.sub(new Quantity(up.price()));

				up.action();

				upgrades.remove(up);

				Main.menu();
			}
		}
	}

	public Upgrade[] getUpgrades() {
		see = new ArrayList<>();

		for (Entry<Upgrade, Function<Void, Boolean>> a : upgrades.entrySet()) {
			if (a.getValue().apply(null)) {
				see.add(a.getKey());
			}
		}

		return see.toArray(new Upgrade[see.size()]);
	}
}
