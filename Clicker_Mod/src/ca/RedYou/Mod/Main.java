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

	public Quantity getPrice(int id, Quantity amount) {
		double a = (id + 9 + (id == 0 ? 6 : 0) + (id < 5 ? 0 : Math.pow(id - 5, 1.75))) * Math.pow(10, id);
		double b = Math.pow(10, (Math.ceil(Math.log10(Math.ceil(a)))) / Math.log(10)) / 10;
		return Quantity.valueOf((long) Math.ceil(Math.round(a / b) * b * Math.pow(1.15, amount.toLong())));
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

			ImageIcon grandma0PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP0.png")));
			ImageIcon grandma1PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP1.png")));
			ImageIcon grandma2PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP2.png")));
			ImageIcon grandma3PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP3.png")));
			ImageIcon grandma4PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP4.png")));
			ImageIcon grandma5PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP5.png")));
			ImageIcon grandma6PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP6.png")));
			ImageIcon grandma7PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP7.png")));
			ImageIcon grandma8PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP8.png")));
			ImageIcon grandma9PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP9.png")));
			ImageIcon grandma10PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP10.png")));
			ImageIcon grandma11PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP11.png")));
			ImageIcon grandma12PNG = new ImageIcon(ImageIO.read(getSource("grandmaUP12.png")));

			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(1)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(1000);
				}

				@Override
				public String name() {
					return "Forwards from grandma";
				}

				@Override
				public ImageIcon icon() {
					return grandma0PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"RE:RE:thought you'd get a kick out of this ;))\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(5)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(5000);
				}

				@Override
				public String name() {
					return "Steel-plated rolling pins";
				}

				@Override
				public ImageIcon icon() {
					return grandma1PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Just what you kneaded.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(25)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(50000);
				}

				@Override
				public String name() {
					return "Lubricated dentures";
				}

				@Override
				public ImageIcon icon() {
					return grandma2PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"squish\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(50)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(5000000);
				}

				@Override
				public String name() {
					return "Prune juice";
				}

				@Override
				public ImageIcon icon() {
					return grandma3PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Gets me going.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(100)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(500000000);
				}

				@Override
				public String name() {
					return "Double-thick glasses";
				}

				@Override
				public ImageIcon icon() {
					return grandma4PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Oh... so THAT's what I've been baking.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(150)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(50000000000l);
				}

				@Override
				public String name() {
					return "Aging agents";
				}

				@Override
				public ImageIcon icon() {
					return grandma5PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Counter-intuitively, grandmas have the uncanny ability to become more powerful the older they get.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(200)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(50000000000000l);
				}

				@Override
				public String name() {
					return "Xtreme walkers";
				}

				@Override
				public ImageIcon icon() {
					return grandma6PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Complete with flame decals and a little horn that goes \"toot\".\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(250)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(50000000000000000l);
				}

				@Override
				public String name() {
					return "The Unbridling";
				}

				@Override
				public ImageIcon icon() {
					return grandma7PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"It might be a classic tale of bad parenting, but let's see where grandma is going with this.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(300)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("50000000000000000000");
				}

				@Override
				public String name() {
					return "Reverse dementia";
				}

				@Override
				public ImageIcon icon() {
					return grandma8PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Extremely unsettling, and somehow even worse than the regular kind.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(350)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("50000000000000000000000");
				}

				@Override
				public String name() {
					return "Timeproof hair dyes";
				}

				@Override
				public ImageIcon icon() {
					return grandma9PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Why do they always have those strange wispy pink dos? What do they know about candy floss that we don't?\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(400)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("500000000000000000000000000");
				}

				@Override
				public String name() {
					return "Good manners";
				}

				@Override
				public ImageIcon icon() {
					return grandma10PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Apparently these ladies are much more amiable if you take the time to learn their strange, ancient customs, which seem to involve saying \"please\" and \"thank you\" and staring at the sun with bulging eyes while muttering eldritch curses under your breath.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(450)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("5000000000000000000000000000000");
				}

				@Override
				public String name() {
					return "Generation degeneration";
				}

				@Override
				public ImageIcon icon() {
					return grandma11PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"Genetic testing shows that most of your grandmas are infected with a strange degenerative disease that only seems to further their powers; the more time passes, the older they get. This should concern you.\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(grandma).compareTo(Quantity.valueOf(500)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("50000000000000000000000000000000000");
				}

				@Override
				public String name() {
					return "Visits";
				}

				@Override
				public ImageIcon icon() {
					return grandma12PNG;
				}

				@Override
				public String desc() {
					return "Grandmas are twice as efficient.<br>\"In an extensive double-blind study (sample size: 12 millions), your researchers have found evidence that grandmas are up to twice as productive if you just come by and say hi once in a while. It's nice to check up on your grans! (Do not under any circumstances ingest any tea or tea-like substances the grandmas may offer you.).\"";
				}

				@Override
				public void action() {
					grandma.multiplier.mult(Quantity.valueOf(2));
				}
			});

			ImageIcon farm0PNG = new ImageIcon(ImageIO.read(getSource("farmUP0.png")));
			ImageIcon farm1PNG = new ImageIcon(ImageIO.read(getSource("farmUP1.png")));
			ImageIcon farm2PNG = new ImageIcon(ImageIO.read(getSource("farmUP2.png")));
			ImageIcon farm3PNG = new ImageIcon(ImageIO.read(getSource("farmUP3.png")));
			ImageIcon farm4PNG = new ImageIcon(ImageIO.read(getSource("farmUP4.png")));
			ImageIcon farm5PNG = new ImageIcon(ImageIO.read(getSource("farmUP5.png")));
			ImageIcon farm6PNG = new ImageIcon(ImageIO.read(getSource("farmUP6.png")));
			ImageIcon farm7PNG = new ImageIcon(ImageIO.read(getSource("farmUP7.png")));
			ImageIcon farm8PNG = new ImageIcon(ImageIO.read(getSource("farmUP8.png")));
			ImageIcon farm9PNG = new ImageIcon(ImageIO.read(getSource("farmUP9.png")));
			ImageIcon farm10PNG = new ImageIcon(ImageIO.read(getSource("farmUP10.png")));
			ImageIcon farm11PNG = new ImageIcon(ImageIO.read(getSource("farmUP11.png")));
			ImageIcon farm12PNG = new ImageIcon(ImageIO.read(getSource("farmUP12.png")));

			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(1)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(11000);
				}

				@Override
				public String name() {
					return "Cheap hoes";
				}

				@Override
				public ImageIcon icon() {
					return farm0PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Rake in the dough!\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(5)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(55000);
				}

				@Override
				public String name() {
					return "Fertilizer";
				}

				@Override
				public ImageIcon icon() {
					return farm1PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"It's chocolate, I swear.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(25)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(550000);
				}

				@Override
				public String name() {
					return "Cookie trees";
				}

				@Override
				public ImageIcon icon() {
					return farm2PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"A relative of the breadfruit.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(50)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(55000000);
				}

				@Override
				public String name() {
					return "Genetically-modified cookies";
				}

				@Override
				public ImageIcon icon() {
					return farm3PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"All-natural mutations.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(100)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(5500000000l);
				}

				@Override
				public String name() {
					return "Gingerbread scarecrows";
				}

				@Override
				public ImageIcon icon() {
					return farm4PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Staring at your crops with mischievous glee.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(150)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(550000000000l);
				}

				@Override
				public String name() {
					return "Pulsar sprinklers";
				}

				@Override
				public ImageIcon icon() {
					return farm5PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"There's no such thing as over-watering. The moistest is the bestest.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(200)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(550000000000000l);
				}

				@Override
				public String name() {
					return "Fudge fungus";
				}

				@Override
				public ImageIcon icon() {
					return farm6PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"A sugary parasite whose tendrils help cookie growth.<br>Please do not breathe in the spores. In case of spore ingestion, seek medical help within the next 36 seconds.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(250)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf(550000000000000000l);
				}

				@Override
				public String name() {
					return "Wheat triffids";
				}

				@Override
				public ImageIcon icon() {
					return farm7PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Taking care of crops is so much easier when your plants can just walk about and help around the farm.<br>Do not pet. Do not feed. Do not attempt to converse with.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(300)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("550000000000000000000");
				}

				@Override
				public String name() {
					return "Humane pesticides";
				}

				@Override
				public ImageIcon icon() {
					return farm8PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Made by people, for people, from people and ready to unleash some righteous scorching pain on those pesky insects that so deserve it.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(350)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("550000000000000000000000");
				}

				@Override
				public String name() {
					return "Barnstars";
				}

				@Override
				public ImageIcon icon() {
					return farm9PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Ah, yes. These help quite a bit. Somehow.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(400)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("5500000000000000000000000000");
				}

				@Override
				public String name() {
					return "Lindworms";
				}

				@Override
				public ImageIcon icon() {
					return farm10PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"You have to import these from far up north, but they really help areate the soil!\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(450)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("55000000000000000000000000000000");
				}

				@Override
				public String name() {
					return "Global seed vault";
				}

				@Override
				public ImageIcon icon() {
					return farm11PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"An enormous genetic repository that could outlive an apocalypse. Guarantees the survival of your empire, or at the very least its agricultural components, should civilization fall. Which should be any day now.\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
			ups.addUpgrade(l -> {
				return Player.getInstance().getEntityQuantity(farm).compareTo(Quantity.valueOf(500)) > -1;
			}, new Upgrade() {

				@Override
				public Quantity price() {
					return Quantity.valueOf("550000000000000000000000000000000000");
				}

				@Override
				public String name() {
					return "Reverse-veganism";
				}

				@Override
				public ImageIcon icon() {
					return farm12PNG;
				}

				@Override
				public String desc() {
					return "Farms are twice as efficient.<br>\"Plants aren't for eating, plants are for exploitative agriculture and astronomical profit margins!\"";
				}

				@Override
				public void action() {
					farm.multiplier.mult(Quantity.valueOf(2));
				}
			});
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
