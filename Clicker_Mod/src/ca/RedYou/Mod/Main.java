package ca.RedYou.Mod;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ca.RedYou.Game.Entity;
import ca.RedYou.Game.Mod;
import ca.RedYou.Game.Quantity;
import ca.RedYou.Game.Controller.Player;

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

				int restant = 0;

				@Override
				public String name() {
					return "cursor";
				}

				@Override
				public ImageIcon icon() {
					return cursorPNG;
				}

				@Override
				public void action(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					restant += q.div(Quantity.valueOf(10)).toLong();
					if (restant >= 10) {
						q.add(Quantity.valueOf(restant / 10));
						restant = restant % 10;
					}
					Player.getInstance().getMoney().add(q);
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
				public void action(Quantity quantity) {
					Player.getInstance().getMoney().add(quantity);
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
				public void action(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.mult(Quantity.valueOf(8));
					Player.getInstance().getMoney().add(q);
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
