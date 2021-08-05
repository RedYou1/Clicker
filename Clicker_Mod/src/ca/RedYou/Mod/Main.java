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

	public void addDoubleUp(Entity ent, ImageIcon img, String name, String actiondesc, String desc, Quantity cost,
			Quantity min) {
		UpgradeController ups = UpgradeController.getInstance();
		ups.addUpgrade(l -> {
			return Player.getInstance().getEntityQuantity(ent).compareTo(min) > -1;
		}, this, new Upgrade() {

			@Override
			public Quantity price() {
				return new Quantity(cost);
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public String actiondesc() {
				return actiondesc;
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
			String mouseAct = "The mouse and cursors are <B>twice</B> as efficient.";
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(1)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return mouseAct;
				}

				@Override
				public String desc() {
					return "\"prod prod\"";
				}

				@Override
				public void action() {
					Player.getInstance().getClickMult().mult(Quantity.valueOf(2));
					cursor.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(1)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return mouseAct;
				}

				@Override
				public String desc() {
					return "\"it... it hurts to click...\"";
				}

				@Override
				public void action() {
					Player.getInstance().getClickMult().mult(Quantity.valueOf(2));
					cursor.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(10)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return mouseAct;
				}

				@Override
				public String desc() {
					return "\"Look ma, both hands!\"";
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
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "The mouse and cursors gain +0.1 cookies for each non-cursor object owned.";
				}

				@Override
				public String desc() {
					return "\"clickity\"";
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
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 5.";
				}

				@Override
				public String desc() {
					return "\"clickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(5));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(100)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 10.";
				}

				@Override
				public String desc() {
					return "\"clickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(10));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(150)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"clickityclickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(200)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"clickityclickityclickityclickityclickity\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(250)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"man, just go click click click click click, it’s real easy, man.\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(300)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"sometimes things just click\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(350)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"[cursory flavor text]\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(400)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"Turns out you can quite put your finger on it.\"";
				}

				@Override
				public void action() {
					cursorBoost.mult(Quantity.valueOf(20));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(cursor).compareTo(Quantity.valueOf(450)) > -1;
			}, this, new Upgrade() {

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
				public String actiondesc() {
					return "Multiplies the gain from Thousand fingers by 20.";
				}

				@Override
				public String desc() {
					return "\"Only for the freakiest handshakes.\"";
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

			String grandmaAct = "Grandmas are <B>twice</B> as efficient.";

			addDoubleUp(grandma, grandma0PNG, "Forwards from grandma", grandmaAct,
					"\"RE:RE:thought you'd get a kick out of this ;))\"", Quantity.valueOf(1000), Quantity.valueOf(1));
			addDoubleUp(grandma, grandma1PNG, "Steel-plated rolling pins", grandmaAct, "\"Just what you kneaded.\"",
					Quantity.valueOf(5000), Quantity.valueOf(5));
			addDoubleUp(grandma, grandma2PNG, "Lubricated dentures", grandmaAct, "\"squish\"", Quantity.valueOf(50000),
					Quantity.valueOf(25));
			addDoubleUp(grandma, grandma3PNG, "Prune juice", grandmaAct, "\"Gets me going.\"",
					Quantity.valueOf(5000000), Quantity.valueOf(50));
			addDoubleUp(grandma, grandma4PNG, "Double-thick glasses", grandmaAct,
					"\"Oh... so THAT's what I've been baking.\"", Quantity.valueOf(500000000), Quantity.valueOf(100));
			addDoubleUp(grandma, grandma5PNG, "Aging agents", grandmaAct,
					"\"Counter-intuitively, grandmas have the uncanny ability to become more powerful the older they get.\"",
					Quantity.valueOf(50000000000l), Quantity.valueOf(150));
			addDoubleUp(grandma, grandma6PNG, "Xtreme walkers", grandmaAct,
					"\"Complete with flame decals and a little horn that goes \"toot\".\"",
					Quantity.valueOf(50000000000000l), Quantity.valueOf(200));
			addDoubleUp(grandma, grandma7PNG, "The Unbridling", grandmaAct,
					"\"It might be a classic tale of bad parenting, but let's see where grandma is going with this.\"",
					Quantity.valueOf(50000000000000000l), Quantity.valueOf(250));
			addDoubleUp(grandma, grandma8PNG, "Reverse dementia", grandmaAct,
					"\"Extremely unsettling, and somehow even worse than the regular kind.\"",
					Quantity.valueOf("50000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(grandma, grandma9PNG, "Timeproof hair dyes", grandmaAct,
					"\"Why do they always have those strange wispy pink dos? What do they know about candy floss that we don't?\"",
					Quantity.valueOf("50000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(grandma, grandma10PNG, "Good manners", grandmaAct,
					"\"Apparently these ladies are much more amiable if you take the time to learn their strange, ancient customs, which seem to involve saying \"please\" and \"thank you\" and staring at the sun with bulging eyes while muttering eldritch curses under your breath.\"",
					Quantity.valueOf("500000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(grandma, grandma11PNG, "Generation degeneration", grandmaAct,
					"\"Genetic testing shows that most of your grandmas are infected with a strange degenerative disease that only seems to further their powers; the more time passes, the older they get. This should concern you.\"",
					Quantity.valueOf("5000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(grandma, grandma12PNG, "Visits", grandmaAct,
					"\"In an extensive double-blind study (sample size: 12 millions), your researchers have found evidence that grandmas are up to twice as productive if you just come by and say hi once in a while. It's nice to check up on your grans! (Do not under any circumstances ingest any tea or tea-like substances the grandmas may offer you.).\"",
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

			String farmAct = "Farms are <B>twice</B> as efficient.";

			addDoubleUp(farm, farm0PNG, "Cheap hoes", farmAct, "\"Rake in the dough!\"", Quantity.valueOf(11000),
					Quantity.valueOf(1));
			addDoubleUp(farm, farm1PNG, "Fertilizer", farmAct, "\"It's chocolate, I swear.\"", Quantity.valueOf(55000),
					Quantity.valueOf(5));
			addDoubleUp(farm, farm2PNG, "Cookie trees", farmAct, "\"A relative of the breadfruit.\"",
					Quantity.valueOf(550000), Quantity.valueOf(25));
			addDoubleUp(farm, farm3PNG, "Genetically-modified cookies", farmAct, "\"All-natural mutations.\"",
					Quantity.valueOf(55000000), Quantity.valueOf(50));
			addDoubleUp(farm, farm4PNG, "Gingerbread scarecrows", farmAct,
					"\"Staring at your crops with mischievous glee.\"", Quantity.valueOf(5500000000l),
					Quantity.valueOf(100));
			addDoubleUp(farm, farm5PNG, "Pulsar sprinklers", farmAct,
					"\"There's no such thing as over-watering. The moistest is the bestest.\"",
					Quantity.valueOf(550000000000l), Quantity.valueOf(150));
			addDoubleUp(farm, farm6PNG, "Fudge fungus", farmAct,
					"\"A sugary parasite whose tendrils help cookie growth.<br>Please do not breathe in the spores. In case of spore ingestion, seek medical help within the next 36 seconds.\"",
					Quantity.valueOf(550000000000000l), Quantity.valueOf(200));
			addDoubleUp(farm, farm7PNG, "Wheat triffids", farmAct,
					"\"Taking care of crops is so much easier when your plants can just walk about and help around the farm.<br>Do not pet. Do not feed. Do not attempt to converse with.\"",
					Quantity.valueOf(550000000000000000l), Quantity.valueOf(250));
			addDoubleUp(farm, farm8PNG, "Humane pesticides", farmAct,
					"\"Made by people, for people, from people and ready to unleash some righteous scorching pain on those pesky insects that so deserve it.\"",
					Quantity.valueOf("550000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(farm, farm9PNG, "Barnstars", farmAct, "\"Ah, yes. These help quite a bit. Somehow.\"",
					Quantity.valueOf("550000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(farm, farm10PNG, "Lindworms", farmAct,
					"\"You have to import these from far up north, but they really help areate the soil!\"",
					Quantity.valueOf("5500000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(farm, farm11PNG, "Global seed vault", farmAct,
					"\"An enormous genetic repository that could outlive an apocalypse. Guarantees the survival of your empire, or at the very least its agricultural components, should civilization fall. Which should be any day now.\"",
					Quantity.valueOf("55000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(farm, farm12PNG, "Reverse-veganism", farmAct,
					"\"Plants aren't for eating, plants are for exploitative agriculture and astronomical profit margins!\"",
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

			String mineAct = "Mines are <B>twice</B> as efficient.";
			addDoubleUp(mine, mine0PNG, "Sugar gas", mineAct,
					"\"A pink, volatile gas, found in the depths of some chocolate caves.\"", Quantity.valueOf(120000),
					Quantity.valueOf(1));
			addDoubleUp(mine, mine1PNG, "Megadrill", mineAct, "\"You're in deep.\"", Quantity.valueOf(600000),
					Quantity.valueOf(5));
			addDoubleUp(mine, mine2PNG, "Ultradrill", mineAct, "\"Finally caved in?\"", Quantity.valueOf(6000000),
					Quantity.valueOf(25));
			addDoubleUp(mine, mine3PNG, "Ultimadrill", mineAct, "\"Pierce the heavens, etc.\"",
					Quantity.valueOf(600000000), Quantity.valueOf(50));
			addDoubleUp(mine, mine4PNG, "H-bomb mining", mineAct,
					"\"Questionable efficiency, but spectacular nonetheless.\"", Quantity.valueOf(60000000000l),
					Quantity.valueOf(100));
			addDoubleUp(mine, mine5PNG, "Coreforge", mineAct,
					"\"You've finally dug a tunnel down to the Earth's core. It's pretty warm down here.\"",
					Quantity.valueOf(6000000000000l), Quantity.valueOf(150));
			addDoubleUp(mine, mine6PNG, "Planetsplitters", mineAct,
					"\"These new state-of-the-art excavators have been tested on Merula, Globort and Flwanza VI, among other distant planets which have been curiously quiet lately.\"",
					Quantity.valueOf(6000000000000000l), Quantity.valueOf(200));
			addDoubleUp(mine, mine7PNG, "Canola oil wells", mineAct,
					"\"A previously untapped resource, canola oil permeates the underground olifers which grant it its particular taste and lucrative properties.\"",
					Quantity.valueOf(6000000000000000000l), Quantity.valueOf(250));
			addDoubleUp(mine, mine8PNG, "Mole people", mineAct,
					"\"Engineered from real human beings within your very labs, these sturdy little folks have a knack for finding the tastiest underground minerals in conditions that more expensive machinery probably wouldn't survive.\"",
					Quantity.valueOf("6000000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(mine, mine9PNG, "Mine canaries", mineAct,
					"\"These aren't used for anything freaky! The miners just enjoy having a pet or two down there.\"",
					Quantity.valueOf("6000000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(mine, mine10PNG, "Bore again", mineAct,
					"\"After extracting so much sediment for so long, you've formed some veritable mountains of your own from the accumulated piles of rock and dirt. Time to dig through those and see if you find anything fun!\"",
					Quantity.valueOf("60000000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(mine, mine11PNG, "Air mining", mineAct,
					"\"You've dug your drills through just about every solid surface you could find. But did you know recent advances have revealed untold riches hiding within non-solid surfaces too?\"",
					Quantity.valueOf("600000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(mine, mine12PNG, "Caramel alloys", mineAct,
					"\"Your geologists have isolated a family of once-overlooked sugary ores that, when combined, may be turned into even more cookie ingredients. Your millions of miles of previously useless tunnels probably house insane amounts of the stuff!\"",
					Quantity.valueOf("6000000000000000000000000000000000000"), Quantity.valueOf(500));

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

			String factoryAct = "Factories are <B>twice</B> as efficient.";
			addDoubleUp(factory, factory0PNG, "Sturdier conveyor belts", factoryAct, "\"You're going places.\"",
					Quantity.valueOf("1300000"), Quantity.valueOf(1));
			addDoubleUp(factory, factory1PNG, "Child labor", factoryAct, "\"Cheaper, healthier workforce.\"",
					Quantity.valueOf("6500000"), Quantity.valueOf(5));
			addDoubleUp(factory, factory2PNG, "Sweatshop", factoryAct, "\"Slackers will be terminated.\"",
					Quantity.valueOf("65000000"), Quantity.valueOf(25));
			addDoubleUp(factory, factory3PNG, "Radium reactors", factoryAct, "\"Gives your cookies a healthy glow.\"",
					Quantity.valueOf("6500000000"), Quantity.valueOf(50));
			addDoubleUp(factory, factory4PNG, "Recombobulators", factoryAct,
					"\"A major part of cookie recombobulation.\"", Quantity.valueOf("650000000000"),
					Quantity.valueOf(100));
			addDoubleUp(factory, factory5PNG, "Deep-bake process", factoryAct,
					"\"A patented process increasing cookie yield two-fold for the same amount of ingredients. Don't ask how, don't take pictures, and be sure to wear your protective suit.\"",
					Quantity.valueOf("65000000000000"), Quantity.valueOf(150));
			addDoubleUp(factory, factory6PNG, "Cyborg workforce", factoryAct,
					"\"Semi-synthetic organisms don't slack off, don't unionize, and have 20% shorter lunch breaks, making them ideal labor fodder.\"",
					Quantity.valueOf("65000000000000000"), Quantity.valueOf(200));
			addDoubleUp(factory, factory7PNG, "78-hour days", factoryAct, "\"Why didn't we think of this earlier?\"",
					Quantity.valueOf("65000000000000000000"), Quantity.valueOf(250));
			addDoubleUp(factory, factory8PNG, "Machine learning", factoryAct,
					"\"You figured you might get better productivity if you actually told your workers to learn how to work the machines. Sometimes, it's the little things...\"",
					Quantity.valueOf("65000000000000000000000"), Quantity.valueOf(300));
			addDoubleUp(factory, factory9PNG, "Brownie point system", factoryAct,
					"\"Oh, these are lovely! You can now reward your factory employees for good behavior, such as working overtime or snitching on coworkers. 58 brownie points gets you a little picture of a brownie, and 178 of those pictures gets you an actual brownie piece for you to do with as you please! Infantilizing? Maybe. Oodles of fun? You betcha!\"",
					Quantity.valueOf("65000000000000000000000000"), Quantity.valueOf(350));
			addDoubleUp(factory, factory10PNG, "\"Volunteer\" interns", factoryAct,
					"\"If you're bad at something, always do it for free.\"",
					Quantity.valueOf("650000000000000000000000000000"), Quantity.valueOf(400));
			addDoubleUp(factory, factory11PNG, "Behavioral reframing", factoryAct,
					"\"Through careful social engineering you've convinced your workers that \"union\" is a slur that only the most vile and repugnant filth among us would ever dare utter! Sometimes progress isn't in the big machines, it's in the little lies!\"",
					Quantity.valueOf("6500000000000000000000000000000000"), Quantity.valueOf(450));
			addDoubleUp(factory, factory12PNG, "The infinity engine", factoryAct,
					"\"In this house, I guess we don't care much for the laws of thermodynamics.\"",
					Quantity.valueOf("6000000000000000000000000000000000000"), Quantity.valueOf(500));

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

//			String factoryAct = "Factories are <B>twice</B> as efficient.";
//			addDoubleUp(factory, factory0PNG, "", factoryAct, "\"\"", Quantity.valueOf("1300000"), Quantity.valueOf(1));

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
	public void stop() {
	}

}
