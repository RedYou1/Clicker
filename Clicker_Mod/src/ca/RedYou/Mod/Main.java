package ca.RedYou.Mod;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ca.RedYou.Game.Entity;
import ca.RedYou.Game.Mod;
import ca.RedYou.Game.Quantity;
import ca.RedYou.Game.Upgrade;
import ca.RedYou.Game.Controller.EntityController;
import ca.RedYou.Game.Controller.Player;
import ca.RedYou.Game.Controller.UpgradeController;

public class Main extends Mod {

	public Quantity getPrice(int idint, Quantity amount) {
		Quantity id = Quantity.valueOf(idint);

		Quantity a = new Quantity(id);
		a.add(Quantity.valueOf(9));

		if (id.equals(new Quantity()))
			a.add(Quantity.valueOf(6));

		if (id.compareTo(Quantity.valueOf(5)) >= 0) {
			Quantity l = new Quantity(id);
			l.sub(Quantity.valueOf(5));
			l.pow(Quantity.valueOf(1.75));
			a.add(l);
		}

		Quantity y = Quantity.valueOf(10);
		y.pow(new Quantity(id));
		a.mult(y);
		Quantity b = Quantity.valueOf(10);
		Quantity b1 = new Quantity(a);
		b1.ceil(0);
		b1.log10();
		b1.ceil(0);
		b1.div(Quantity.valueOf(10));
		b.pow(b1);
		b.div(Quantity.valueOf(10));
		a.div(b);
		a.round(0);
		a.mult(b);
		Quantity k = Quantity.valueOf(1.15);
		k.pow(amount);

		a.mult(k);
		a.ceil(0);

		return a;
	}

	public void addDoubleUp(Entity ent, ImageIcon img, String name, String desc, Quantity cost, Quantity min) {
		UpgradeController ups = UpgradeController.getInstance();
		ups.addUpgrade(l -> {
			return Player.getInstance().getEntityQuantity(ent).compareTo(min) > -1;
		}, new Upgrade() {

			@Override
			public Quantity price() {
				return new Quantity(cost);
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public ImageIcon icon() {
				return img;
			}

			@Override
			public String desc() {
				return desc;
			}

			@Override
			public void action() {
				ent.multiplier.mult(Quantity.valueOf(2));
			}
		});
	}

	public Main(File gameFolder) {
		super(gameFolder);
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

			final Quantity cursorUP = new Quantity();

			Entity cursor = setEntity(new Entity() {
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
			Entity grandma = setEntity(new Entity() {

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
			Entity farm = setEntity(new Entity() {
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
			Entity mine = setEntity(new Entity() {
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
			Entity factory = setEntity(new Entity() {
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
			Entity bank = setEntity(new Entity() {
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
			Entity temple = setEntity(new Entity() {
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
			Entity wizard = setEntity(new Entity() {
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
			Entity shipement = setEntity(new Entity() {
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
			Entity alchemy = setEntity(new Entity() {
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
			Entity portal = setEntity(new Entity() {
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
			Entity time = setEntity(new Entity() {
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
			Entity antimatter = setEntity(new Entity() {
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
			Entity prism = setEntity(new Entity() {
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
			Entity chancemaker = setEntity(new Entity() {
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
			Entity fractal = setEntity(new Entity() {
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
			Entity javascript = setEntity(new Entity() {
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
			Entity idleverse = setEntity(new Entity() {

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

			ImageIcon mouse0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP0.png")));
			ImageIcon mouse1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP1.png")));
			ImageIcon mouse2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP2.png")));
			ImageIcon mouse3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP3.png")));
			ImageIcon mouse4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP4.png")));
			ImageIcon mouse5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP5.png")));
			ImageIcon mouse6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP6.png")));
			ImageIcon mouse7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP7.png")));
			ImageIcon mouse8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP8.png")));
			ImageIcon mouse9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP9.png")));
			ImageIcon mouse10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP10.png")));
			ImageIcon mouse11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP11.png")));
			ImageIcon mouse12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mouseUP12.png")));

			UpgradeController ups = UpgradeController.getInstance();

			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(1)) > -1;
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
					cursor.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(1)) > -1;
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
					cursor.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(10)) > -1;
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
					cursor.multiplier.mult(Quantity.valueOf(2));
				}
			});
			final Quantity cursorBoost = Quantity.valueOf(1);
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(25)) > -1;
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
					Entity cursorEnt = cursor;

					Quantity v = new Quantity();

					for (Entity ent : EntityController.getInstance().getEntities()) {
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
					if (v.compareTo(new Quantity()) > 0) {
						v.mult(new Quantity(cursorBoost));
						cursorUP.add(v);
						Player.getInstance().getClickMult().mult(new Quantity(cursorUP));
					}
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(50)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(100)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(150)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(200)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(250)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(300)) > -1;
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(350)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("10000000000000000000");
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(400)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("10000000000000000000000");
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
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(450)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("10000000000000000000000000");
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

			ImageIcon grandma0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP0.png")));
			ImageIcon grandma1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP1.png")));
			ImageIcon grandma2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP2.png")));
			ImageIcon grandma3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP3.png")));
			ImageIcon grandma4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP4.png")));
			ImageIcon grandma5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP5.png")));
			ImageIcon grandma6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP6.png")));
			ImageIcon grandma7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP7.png")));
			ImageIcon grandma8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP8.png")));
			ImageIcon grandma9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP9.png")));
			ImageIcon grandma10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP10.png")));
			ImageIcon grandma11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP11.png")));
			ImageIcon grandma12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/grandmaUP12.png")));

			addDoubleUp(grandma, grandma0PNG, "Forwards from grandma",
					"Grandmas are twice as efficient.<br>\"RE:RE:thought you'd get a kick out of this ;))\"",
					Quantity.valueOf(1000), Quantity.valueOf(1));
			addDoubleUp(grandma, grandma1PNG, "Steel-plated rolling pins",
					"Grandmas are twice as efficient.<br>\"Just what you kneaded.\"", Quantity.valueOf(5000),
					Quantity.valueOf(5));
			addDoubleUp(grandma, grandma2PNG, "Lubricated dentures", "Grandmas are twice as efficient.<br>\"squish\"",
					Quantity.valueOf(50000), Quantity.valueOf(25));
			addDoubleUp(grandma, grandma3PNG, "Prune juice", "Grandmas are twice as efficient.<br>\"Gets me going.\"",
					Quantity.valueOf(5000000), Quantity.valueOf(50));
			addDoubleUp(grandma, grandma4PNG, "Double-thick glasses",
					"Grandmas are twice as efficient.<br>\"Oh... so THAT's what I've been baking.\"",
					Quantity.valueOf(500000000), Quantity.valueOf(100));
			addDoubleUp(grandma, grandma5PNG, "Aging agents",
					"Grandmas are twice as efficient.<br>\"Counter-intuitively, grandmas have the uncanny ability to become more powerful the older they get.\"",
					Quantity.valueOf(50000000000l), Quantity.valueOf(150));
			addDoubleUp(grandma, grandma6PNG, "Xtreme walkers",
					"Grandmas are twice as efficient.<br>\"Complete with flame decals and a little horn that goes \"toot\".\"",
					Quantity.valueOf(50000000000000l), Quantity.valueOf(200));
			addDoubleUp(grandma, grandma7PNG, "The Unbridling",
					"Grandmas are twice as efficient.<br>\"It might be a classic tale of bad parenting, but let's see where grandma is going with this.\"",
					Quantity.valueOf(50000000000000000l), Quantity.valueOf(250));
			addDoubleUp(grandma, grandma8PNG, "Reverse dementia",
					"Grandmas are twice as efficient.<br>\"Extremely unsettling, and somehow even worse than the regular kind.\"",
					Quantity.valueOf("50000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(grandma, grandma9PNG, "Timeproof hair dyes",
					"Grandmas are twice as efficient.<br>\"Why do they always have those strange wispy pink dos? What do they know about candy floss that we don't?\"",
					Quantity.valueOf("50000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(grandma, grandma10PNG, "Good manners",
					"Grandmas are twice as efficient.<br>\"Apparently these ladies are much more amiable if you take the time to learn their strange, ancient customs, which seem to involve saying \"please\" and \"thank you\" and staring at the sun with bulging eyes while muttering eldritch curses under your breath.\"",
					Quantity.valueOf("500000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(grandma, grandma11PNG, "Generation degeneration",
					"Grandmas are twice as efficient.<br>\"Genetic testing shows that most of your grandmas are infected with a strange degenerative disease that only seems to further their powers; the more time passes, the older they get. This should concern you.\"",
					Quantity.valueOf("5000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(grandma, grandma12PNG, "Visits",
					"Grandmas are twice as efficient.<br>\"In an extensive double-blind study (sample size: 12 millions), your researchers have found evidence that grandmas are up to twice as productive if you just come by and say hi once in a while. It's nice to check up on your grans! (Do not under any circumstances ingest any tea or tea-like substances the grandmas may offer you.).\"",
					Quantity.valueOf("50000000000000000000000000000000000"), Quantity.valueOf(500));

			ImageIcon farm0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP0.png")));
			ImageIcon farm1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP1.png")));
			ImageIcon farm2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP2.png")));
			ImageIcon farm3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP3.png")));
			ImageIcon farm4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP4.png")));
			ImageIcon farm5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP5.png")));
			ImageIcon farm6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP6.png")));
			ImageIcon farm7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP7.png")));
			ImageIcon farm8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP8.png")));
			ImageIcon farm9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP9.png")));
			ImageIcon farm10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP10.png")));
			ImageIcon farm11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP11.png")));
			ImageIcon farm12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/farmUP12.png")));

			addDoubleUp(farm, farm0PNG, "Cheap hoes", "Farms are twice as efficient.<br>\"Rake in the dough!\"",
					Quantity.valueOf(11000), Quantity.valueOf(1));
			addDoubleUp(farm, farm1PNG, "Fertilizer", "Farms are twice as efficient.<br>\"It's chocolate, I swear.\"",
					Quantity.valueOf(55000), Quantity.valueOf(5));
			addDoubleUp(farm, farm2PNG, "Cookie trees",
					"Farms are twice as efficient.<br>\"A relative of the breadfruit.\"", Quantity.valueOf(550000),
					Quantity.valueOf(25));
			addDoubleUp(farm, farm3PNG, "Genetically-modified cookies",
					"Farms are twice as efficient.<br>\"All-natural mutations.\"", Quantity.valueOf(55000000),
					Quantity.valueOf(50));
			addDoubleUp(farm, farm4PNG, "Gingerbread scarecrows",
					"Farms are twice as efficient.<br>\"Staring at your crops with mischievous glee.\"",
					Quantity.valueOf(5500000000l), Quantity.valueOf(100));
			addDoubleUp(farm, farm5PNG, "Pulsar sprinklers",
					"Farms are twice as efficient.<br>\"There's no such thing as over-watering. The moistest is the bestest.\"",
					Quantity.valueOf(550000000000l), Quantity.valueOf(150));
			addDoubleUp(farm, farm6PNG, "Fudge fungus",
					"Farms are twice as efficient.<br>\"A sugary parasite whose tendrils help cookie growth.<br>Please do not breathe in the spores. In case of spore ingestion, seek medical help within the next 36 seconds.\"",
					Quantity.valueOf(550000000000000l), Quantity.valueOf(200));
			addDoubleUp(farm, farm7PNG, "Wheat triffids",
					"Farms are twice as efficient.<br>\"Taking care of crops is so much easier when your plants can just walk about and help around the farm.<br>Do not pet. Do not feed. Do not attempt to converse with.\"",
					Quantity.valueOf(550000000000000000l), Quantity.valueOf(250));
			addDoubleUp(farm, farm8PNG, "Humane pesticides",
					"Farms are twice as efficient.<br>\"Made by people, for people, from people and ready to unleash some righteous scorching pain on those pesky insects that so deserve it.\"",
					Quantity.valueOf("550000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(farm, farm9PNG, "Barnstars",
					"Farms are twice as efficient.<br>\"Ah, yes. These help quite a bit. Somehow.\"",
					Quantity.valueOf("550000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(farm, farm10PNG, "Lindworms",
					"Farms are twice as efficient.<br>\"You have to import these from far up north, but they really help areate the soil!\"",
					Quantity.valueOf("5500000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(farm, farm11PNG, "Global seed vault",
					"Farms are twice as efficient.<br>\"An enormous genetic repository that could outlive an apocalypse. Guarantees the survival of your empire, or at the very least its agricultural components, should civilization fall. Which should be any day now.\"",
					Quantity.valueOf("55000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(farm, farm12PNG, "Reverse-veganism",
					"Farms are twice as efficient.<br>\"Plants aren't for eating, plants are for exploitative agriculture and astronomical profit margins!\"",
					Quantity.valueOf("550000000000000000000000000000000000"), Quantity.valueOf(500));

			ImageIcon mine0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP0.png")));
			ImageIcon mine1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP1.png")));
			ImageIcon mine2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP2.png")));
			ImageIcon mine3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP3.png")));
			ImageIcon mine4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP4.png")));
			ImageIcon mine5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP5.png")));
			ImageIcon mine6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP6.png")));
			ImageIcon mine7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP7.png")));
			ImageIcon mine8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP8.png")));
			ImageIcon mine9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP9.png")));
			ImageIcon mine10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP10.png")));
			ImageIcon mine11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP11.png")));
			ImageIcon mine12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/mineUP12.png")));

			ImageIcon factory0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP0.png")));
			ImageIcon factory1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP1.png")));
			ImageIcon factory2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP2.png")));
			ImageIcon factory3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP3.png")));
			ImageIcon factory4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP4.png")));
			ImageIcon factory5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP5.png")));
			ImageIcon factory6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP6.png")));
			ImageIcon factory7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP7.png")));
			ImageIcon factory8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP8.png")));
			ImageIcon factory9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP9.png")));
			ImageIcon factory10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP10.png")));
			ImageIcon factory11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP11.png")));
			ImageIcon factory12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/factoryUP12.png")));

			ImageIcon bank0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP0.png")));
			ImageIcon bank1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP1.png")));
			ImageIcon bank2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP2.png")));
			ImageIcon bank3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP3.png")));
			ImageIcon bank4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP4.png")));
			ImageIcon bank5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP5.png")));
			ImageIcon bank6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP6.png")));
			ImageIcon bank7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP7.png")));
			ImageIcon bank8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP8.png")));
			ImageIcon bank9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP9.png")));
			ImageIcon bank10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP10.png")));
			ImageIcon bank11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP11.png")));
			ImageIcon bank12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/bankUP12.png")));

			ImageIcon temple0PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP0.png")));
			ImageIcon temple1PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP1.png")));
			ImageIcon temple2PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP2.png")));
			ImageIcon temple3PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP3.png")));
			ImageIcon temple4PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP4.png")));
			ImageIcon temple5PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP5.png")));
			ImageIcon temple6PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP6.png")));
			ImageIcon temple7PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP7.png")));
			ImageIcon temple8PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP8.png")));
			ImageIcon temple9PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP9.png")));
			ImageIcon temple10PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP10.png")));
			ImageIcon temple11PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP11.png")));
			ImageIcon temple12PNG = new ImageIcon(ImageIO.read(getSource("upgrades/templeUP12.png")));
//			Player.getInstance().getMoney()
//					.add(Quantity.valueOf("1000000000000000000000000000000000000000000000000000"));
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
