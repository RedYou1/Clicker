package ca.RedYou.Game.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ca.RedYou.Game.*;

public class Player {
	private final File save = new File("save\\Player.sav");

	private Map<Entity, Quantity> entities = new HashMap<>();
	private Quantity money = new Quantity();
	private Quantity clickMult = Quantity.valueOf(1);

	public void start() {
		if (save.exists()) {
			try {
				Map<Entity, Quantity> entities = new HashMap<Entity, Quantity>();

				FileReader fr = new FileReader(save);
				BufferedReader br = new BufferedReader(fr);

				Quantity money = new Quantity(br.readLine());
				Quantity clickMult = new Quantity(br.readLine());
				String line;
				EntityController ents = EntityController.getInstance();
				ModController mods = ModController.getInstance();
				while ((line = br.readLine()) != null && line.length() > 0) {
					String[] l = line.split("\\|");
					String[] m = l[0].split("\\:");

					Mod mod = mods.getMod(m[0]);

					Entity ent = ents.getEntity(mod, m[1]);

					entities.put(ent, new Quantity(l[1]));
				}

				br.close();
				fr.close();

				this.money = money;
				this.clickMult = clickMult;
				this.entities = entities;
			} catch (Exception e) {
				System.out.println("the player save is corrupted at this line of code:" + e.getCause());
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

			bw.write(money.extract());
			bw.newLine();
			bw.write(clickMult.extract());
			bw.newLine();

			EntityController ents = EntityController.getInstance();
			for (Entry<Entity, Quantity> a : entities.entrySet()) {
				bw.write(ents.getMod(a.getKey()).name() + ":" + a.getKey().name() + "|" + a.getValue().extract());
				bw.newLine();
			}

			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (save.exists())
				save.delete();
		}
	}

	private static final Player instance = new Player();

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

	public Quantity getClickMult() {
		return clickMult;
	}
}
