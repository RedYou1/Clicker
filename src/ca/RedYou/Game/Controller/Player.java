package ca.RedYou.Game.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ca.RedDevKit.BigNum;
import ca.RedYou.Game.*;

public class Player {
	private final File save = new File("save\\Player.sav");

	private Map<Entity, BigNum> entities = new HashMap<>();
	private BigNum money = new BigNum();
	private BigNum clickMult = BigNum.valueOf(1);

	public void start() {
		if (save.exists()) {
			try {
				Map<Entity, BigNum> entities = new HashMap<Entity, BigNum>();

				FileReader fr = new FileReader(save);
				BufferedReader br = new BufferedReader(fr);

				BigNum money = BigNum.load(br);
				br.readLine();
				BigNum clickMult = BigNum.load(br);
				br.readLine();

				String line;
				EntityController ents = EntityController.getInstance();
				ModController mods = ModController.getInstance();
				while ((line = br.readLine()) != null && line.length() > 0) {
					String[] m = line.split("\\:");

					Mod mod = mods.getMod(m[0]);

					Entity ent = ents.getEntity(mod, m[1]);

					entities.put(ent, BigNum.load(br));
					br.readLine();
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

			money.save(bw);
			bw.newLine();
			clickMult.save(bw);
			bw.newLine();

			EntityController ents = EntityController.getInstance();
			for (Entry<Entity, BigNum> a : entities.entrySet()) {
				bw.write(ents.getMod(a.getKey()).name() + ":" + a.getKey().name());
				bw.newLine();
				a.getValue().save(bw);
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

	public BigNum getEntityBigNum(Entity ent) {
		if (!entities.containsKey(ent))
			entities.put(ent, new BigNum());
		return entities.get(ent);
	}

	public BigNum getMoney() {
		return money;
	}

	public BigNum getClickMult() {
		return clickMult;
	}
}
