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
			ImageIcon cursorPNG = new ImageIcon(ImageIO.read(getSource("cursor.png")));
			ImageIcon grandmaPNG = new ImageIcon(ImageIO.read(getSource("grandma.png")));
			ImageIcon farmPNG = new ImageIcon(ImageIO.read(getSource("farm.png")));

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
