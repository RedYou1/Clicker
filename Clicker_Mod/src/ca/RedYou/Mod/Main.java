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
				public void action(Quantity quantity) {
					Player.getInstance().getMoney().add(quantity);
				}

				@Override
				public Quantity price(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.add(Quantity.valueOf(1));
					q.mult(Quantity.valueOf(2));
					return q;
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
					Quantity q = new Quantity(quantity);
					q.mult(Quantity.valueOf(2));
					Player.getInstance().getMoney().add(q);
				}

				@Override
				public Quantity price(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.add(Quantity.valueOf(1));
					q.mult(Quantity.valueOf(3));
					return q;
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
					q.mult(Quantity.valueOf(3));
					Player.getInstance().getMoney().add(q);
				}

				@Override
				public Quantity price(Quantity quantity) {
					Quantity q = new Quantity(quantity);
					q.add(Quantity.valueOf(1));
					q.mult(Quantity.valueOf(4));
					return q;
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
