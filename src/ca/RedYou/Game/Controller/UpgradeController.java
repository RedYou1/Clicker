package ca.RedYou.Game.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

import ca.RedYou.Game.*;

public class UpgradeController {

	private final File save = new File("save\\Upgrades.sav");

	public void start() {
		if (save.exists()) {
			try {
				Map<Upgrade, Function<Void, Boolean>> upgrades = new HashMap<>(this.upgrades);
				ModController mods = ModController.getInstance();

				FileReader fr = new FileReader(save);
				BufferedReader br = new BufferedReader(fr);

				String line;
				while ((line = br.readLine()) != null && line.length() > 0) {
					String[] a = line.split("\\:");
					Mod m = mods.getMod(a[0]);
					Upgrade up = getUpgrade(m, a[1]);

					if (up != null && upgrades.remove(up) != null) {// if mod removed upgrade in update
						up.action();
					}
				}

				br.close();
				fr.close();

				this.upgrades = upgrades;
			} catch (Exception e) {
				System.out.println("the upgrade save is corrupted at this line of code:" + e.getCause());
				save.delete();
			}
		}
	}

	public void stop() {
		if (save.exists())
			save.delete();

		try {
			save.createNewFile();
			FileWriter fw = new FileWriter(save);
			BufferedWriter bw = new BufferedWriter(fw);

			for (Entry<Upgrade, Mod> up : mods.entrySet()) {
				if (!upgrades.containsKey(up.getKey())) {
					bw.write(up.getValue().name() + ":" + up.getKey().name());
					bw.newLine();
				}
			}

			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (save.exists())
				save.delete();
		}
	}

	private final static UpgradeController instance = new UpgradeController();

	public static UpgradeController getInstance() {
		return instance;
	}

	private Map<Upgrade, Mod> mods = new HashMap<>();
	private Map<Upgrade, Function<Void, Boolean>> upgrades = new HashMap<>();
	private List<Upgrade> see = new ArrayList<>();

	public void addUpgrade(Function<Void, Boolean> necessity, Mod mod, Upgrade up) {
		mods.put(up, mod);
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

	public Upgrade getUpgrade(Mod mod, String name) {
		for (Entry<Upgrade, Mod> a : mods.entrySet()) {
			if (a.getKey().name().equalsIgnoreCase(name) && mod.equals(a.getValue()))
				return a.getKey();
		}
		return null;
	}
}
