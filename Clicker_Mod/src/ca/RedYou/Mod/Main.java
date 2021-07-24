package ca.RedYou.Mod;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ca.RedYou.Game.Entity;
import ca.RedYou.Game.Mod;
import ca.RedYou.Game.Quantity;
import ca.RedYou.Game.Upgrade;
import ca.RedYou.Game.Controller.EntityController;
import ca.RedYou.Game.Controller.EntityController.Key;
import ca.RedYou.Game.Controller.Player;
import ca.RedYou.Game.Controller.UpgradeController;

public class Main extends Mod {

	public Quantity getPrice(int id, Quantity amount) {
		double a = (id + 9 + (id == 0 ? 6 : 0) + (id < 5 ? 0 : Math.pow(id - 5, 1.75))) * Math.pow(10, id);
		double b = Math.pow(10, (Math.ceil(Math.log10(Math.ceil(a)))) / Math.log(10)) / 10;
		return Quantity.valueOf((long) Math.ceil(Math.round(a / b) * b * Math.pow(1.15, amount.toLong())));
	}

	public Main(File gameFolder) {
		super(gameFolder);
		Main inst = this;
		try {
			ImageIcon alchemyPNG = new ImageIcon(ImageIO.read(getSource("alchemy.png")));
			ImageIcon antimatterPNG = new ImageIcon(ImageIO.read(getSource("antimatter.png")));
			ImageIcon bankPNG = new ImageIcon(ImageIO.read(getSource("bank.png")));
			ImageIcon chancemakerPNG = new ImageIcon(ImageIO.read(getSource("chancemaker.png")));
			ImageIcon cursorPNG = new ImageIcon(ImageIO.read(getSource("cursor.png")));
			ImageIcon factoryPNG = new ImageIcon(ImageIO.read(getSource("factory.png")));
			ImageIcon farmPNG = new ImageIcon(ImageIO.read(getSource("farm.png")));
			ImageIcon fractalPNG = new ImageIcon(ImageIO.read(getSource("fractal.png")));
			ImageIcon grandmaPNG = new ImageIcon(ImageIO.read(getSource("grandma.png")));
			ImageIcon javascriptPNG = new ImageIcon(ImageIO.read(getSource("javascript.png")));
			ImageIcon minePNG = new ImageIcon(ImageIO.read(getSource("mine.png")));
			ImageIcon portalPNG = new ImageIcon(ImageIO.read(getSource("portal.png")));
			ImageIcon prismPNG = new ImageIcon(ImageIO.read(getSource("prism.png")));
			ImageIcon shipementPNG = new ImageIcon(ImageIO.read(getSource("shipement.png")));
			ImageIcon templePNG = new ImageIcon(ImageIO.read(getSource("temple.png")));
			ImageIcon time_machinePNG = new ImageIcon(ImageIO.read(getSource("time_machine.png")));
			ImageIcon wizardPNG = new ImageIcon(ImageIO.read(getSource("wizard.png")));
			ImageIcon idleversePNG = new ImageIcon(ImageIO.read(getSource("idleverse.png")));

			final Quantity cursorUP = Quantity.valueOf(1);

			Key cursor = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(10);

				@Override
				public String name() {
					return "cursor";
				}

				@Override
				public ImageIcon icon() {
					return cursorPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.div(new Quantity(a));
					if (cursorUP.compareTo(new Quantity()) > 0)
						q.mult(new Quantity(cursorUP));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(0, quantity);
				}
			});
			Key grandma = setEntity(new Entity() {

				@Override
				public String name() {
					return "grandma";
				}

				@Override
				public ImageIcon icon() {
					return grandmaPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					return quantity;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(1, quantity);
				}
			});
			Key farm = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(8);

				@Override
				public String name() {
					return "farm";
				}

				@Override
				public ImageIcon icon() {
					return farmPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(2, quantity);
				}
			});
			Key mine = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(47);

				@Override
				public String name() {
					return "mine";
				}

				@Override
				public ImageIcon icon() {
					return minePNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(3, quantity);
				}
			});
			Key factory = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(260);

				@Override
				public String name() {
					return "factory";
				}

				@Override
				public ImageIcon icon() {
					return factoryPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(4, quantity);
				}
			});
			Key bank = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(1400);

				@Override
				public String name() {
					return "bank";
				}

				@Override
				public ImageIcon icon() {
					return bankPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(5, quantity);
				}
			});
			Key temple = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(7800);

				@Override
				public String name() {
					return "temple";
				}

				@Override
				public ImageIcon icon() {
					return templePNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(6, quantity);
				}
			});
			Key wizard = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(44000);

				@Override
				public String name() {
					return "wizard tower";
				}

				@Override
				public ImageIcon icon() {
					return wizardPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(7, quantity);
				}
			});
			Key shipement = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(260000);

				@Override
				public String name() {
					return "shipement";
				}

				@Override
				public ImageIcon icon() {
					return shipementPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(8, quantity);
				}
			});
			Key alchemy = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(1600000);

				@Override
				public String name() {
					return "alchemy lab";
				}

				@Override
				public ImageIcon icon() {
					return alchemyPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(9, quantity);
				}
			});
			Key portal = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(10000000);

				@Override
				public String name() {
					return "portal";
				}

				@Override
				public ImageIcon icon() {
					return portalPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(10, quantity);
				}
			});
			Key time = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(65000000);

				@Override
				public String name() {
					return "time machine";
				}

				@Override
				public ImageIcon icon() {
					return time_machinePNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(11, quantity);
				}
			});
			Key antimatter = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(430000000);

				@Override
				public String name() {
					return "antimatter condenser";
				}

				@Override
				public ImageIcon icon() {
					return antimatterPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(12, quantity);
				}
			});
			Key prism = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(2900000000l);

				@Override
				public String name() {
					return "prism";
				}

				@Override
				public ImageIcon icon() {
					return prismPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(13, quantity);
				}
			});
			Key chancemaker = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(21000000000l);

				@Override
				public String name() {
					return "chancemaker";
				}

				@Override
				public ImageIcon icon() {
					return chancemakerPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(14, quantity);
				}
			});
			Key fractal = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(150000000000l);

				@Override
				public String name() {
					return "fractal engine";
				}

				@Override
				public ImageIcon icon() {
					return fractalPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(15, quantity);
				}
			});
			Key javascript = setEntity(new Entity() {
				Quantity a = Quantity.valueOf(1100000000000l);

				@Override
				public String name() {
					return "javascript engine";
				}

				@Override
				public ImageIcon icon() {
					return javascriptPNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(16, quantity);
				}
			});
			Key idleverse = setEntity(new Entity() {

				Quantity a = Quantity.valueOf(8300000000000l);

				@Override
				public String name() {
					return "idleverse";
				}

				@Override
				public ImageIcon icon() {
					return idleversePNG;
				}

				@Override
				public Quantity production(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(new Quantity(a));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(17, quantity);
				}
			});

			ImageIcon mouse0PNG = new ImageIcon(ImageIO.read(getSource("mouseUP0.png")));
			ImageIcon mouse1PNG = new ImageIcon(ImageIO.read(getSource("mouseUP1.png")));
			ImageIcon mouse2PNG = new ImageIcon(ImageIO.read(getSource("mouseUP2.png")));
			ImageIcon mouse3PNG = new ImageIcon(ImageIO.read(getSource("mouseUP3.png")));
			ImageIcon mouse4PNG = new ImageIcon(ImageIO.read(getSource("mouseUP4.png")));
			ImageIcon mouse5PNG = new ImageIcon(ImageIO.read(getSource("mouseUP5.png")));
			ImageIcon mouse6PNG = new ImageIcon(ImageIO.read(getSource("mouseUP6.png")));
			ImageIcon mouse7PNG = new ImageIcon(ImageIO.read(getSource("mouseUP7.png")));
			ImageIcon mouse8PNG = new ImageIcon(ImageIO.read(getSource("mouseUP8.png")));
			ImageIcon mouse9PNG = new ImageIcon(ImageIO.read(getSource("mouseUP9.png")));
			ImageIcon mouse10PNG = new ImageIcon(ImageIO.read(getSource("mouseUP10.png")));
			ImageIcon mouse11PNG = new ImageIcon(ImageIO.read(getSource("mouseUP11.png")));
			ImageIcon mouse12PNG = new ImageIcon(ImageIO.read(getSource("mouseUP12.png")));

			UpgradeController ups = UpgradeController.getInstance();

			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(1)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(100);
				}

				@Override
				public String name() {
					return "Reinforced index finger";
				}

				@Override
				public ImageIcon icon() {
					return mouse0PNG;
				}

				@Override
				public String desc() {
					return "The mouse and cursors are twice as efficient.<br>\"prod prod\"";
				}

				@Override
				public void action() {
					Player.getInstance().getClickMult().mult(Quantity.valueOf(2));
					getEntity(cursor).multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(1)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(500);
				}

				@Override
				public String name() {
					return "Carpal tunnel prevention cream";
				}

				@Override
				public ImageIcon icon() {
					return mouse1PNG;
				}

				@Override
				public String desc() {
					return "The mouse and cursors are twice as efficient.<br>\"it... it hurts to click...\"";
				}

				@Override
				public void action() {
					Player.getInstance().getClickMult().mult(Quantity.valueOf(2));
					getEntity(cursor).multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(10)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(10000);
				}

				@Override
				public String name() {
					return "Ambidextrous";
				}

				@Override
				public ImageIcon icon() {
					return mouse2PNG;
				}

				@Override
				public String desc() {
					return "The mouse and cursors are twice as efficient.<br>\"Look ma, both hands!\"";
				}

				@Override
				public void action() {
					Player.getInstance().getClickMult().mult(Quantity.valueOf(2));
					getEntity(cursor).multiplier.mult(Quantity.valueOf(2));
				}
			});
			final Quantity cursorBoost = Quantity.valueOf(0.1);
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(25)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(100000);
				}

				@Override
				public String name() {
					return "Thousand fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse3PNG;
				}

				@Override
				public String desc() {
					return "The mouse and cursors gain +0.1 cookies for each non-cursor object owned.<br>\"clickity\"";
				}

				@Override
				public void action() {
					Entity cursorEnt = getEntity(cursor);

					Quantity v = new Quantity();

					for (Entity ent : EntityController.getInstance().getEntities(inst)) {
						if (!ent.equals(cursorEnt)) {
							v.add(new Quantity(Player.getInstance().getEntityQuantity(ent)));
							ent.getBuyEvents().add(l -> {
								Quantity q = Player.getInstance().getClickMult();
								q.div(new Quantity(cursorUP));
								cursorUP.add(new Quantity(cursorBoost));
								q.mult(new Quantity(cursorUP));
								ca.RedYou.Game.Main.menu();
								return null;
							});
						}
					}
					if (v.compareTo(Quantity.valueOf(0)) > 0) {
						v.mult(new Quantity(cursorBoost));
						cursorUP.add(v);
						Player.getInstance().getClickMult().mult(new Quantity(cursorUP));
					}
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(50)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(10000000);
				}

				@Override
				public String name() {
					return "Million fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse4PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 5.<br>\"clickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(5));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(100)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(100000000);
				}

				@Override
				public String name() {
					return "Billion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse5PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 10.<br>\"clickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(10));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(150)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(1000000000);
				}

				@Override
				public String name() {
					return "Trillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse6PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"clickityclickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(200)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(10000000000l);
				}

				@Override
				public String name() {
					return "Quadrillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse7PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"clickityclickityclickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(250)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(10000000000000l);
				}

				@Override
				public String name() {
					return "Quintillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse8PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"man, just go click click click click click, it’s real easy, man.\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(300)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(10000000000000000l);
				}

				@Override
				public String name() {
					return "Sextillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse9PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"sometimes things just click\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(350)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					Quantity q = Quantity.valueOf(10000000000000000l);
					q.mult(Quantity.valueOf(1000));
					return q;
				}

				@Override
				public String name() {
					return "Septillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse10PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"[cursory flavor text]\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(400)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					Quantity q = Quantity.valueOf(10000000000000000l);
					q.mult(Quantity.valueOf(1000000));
					return q;
				}

				@Override
				public String name() {
					return "Octillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse11PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"Turns out you can quite put your finger on it.\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(getEntity(cursor)).compareTo(Quantity.valueOf(450)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					Quantity q = Quantity.valueOf(10000000000000000l);
					q.mult(Quantity.valueOf(1000000000));
					return q;
				}

				@Override
				public String name() {
					return "Nonillion fingers";
				}

				@Override
				public ImageIcon icon() {
					return mouse12PNG;
				}

				@Override
				public String desc() {
					return "Multiplies the gain from Thousand fingers by 20.<br>\"Only for the freakiest handshakes.\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String name() {
		return "RedClick";
	}

	@Override
	public int version() {
		return 999;
	}

	@Override
	public void start() {
	}

	@Override
	public void end() {
	}

}
