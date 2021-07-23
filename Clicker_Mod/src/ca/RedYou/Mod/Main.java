package ca.RedYou.Mod;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ca.RedYou.Game.Entity;
import ca.RedYou.Game.Mod;
import ca.RedYou.Game.Quantity;

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

			setEntity(new Entity() {

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
					q.div(Quantity.valueOf(10));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(0, quantity);
				}
			});
			setEntity(new Entity() {

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
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(8));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(2, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(47));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(3, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(260));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(4, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(1400));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(5, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(7800));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(6, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(44000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(7, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(260000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(8, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(1600000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(9, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(10000000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(10, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(65000000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(11, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(430000000));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(12, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(2900000000l));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(13, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(21000000000l));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(14, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(150000000000l));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(15, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(1100000000000l));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(16, quantity);
				}
			});
			setEntity(new Entity() {

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
					q.mult(Quantity.valueOf(8300000000000l));
					return q;
				}

				@Override
				public Quantity price(Quantity quantity) {
					return getPrice(17, quantity);
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
