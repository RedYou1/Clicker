package ca.RedYou.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.*;

import ca.RedDevKit.Pages.AbstractFrame;
import ca.RedDevKit.Pages.Frame;
import ca.RedDevKit.Pages.Slider;
import ca.RedYou.Game.Controller.*;

public class Main {

	private static Frame frame;

	private static JButton click = new JButton();
	private static JButton quit = new JButton();
	private static JLabel money = new JLabel();
	private static JLabel cps = new JLabel();
	private static JLabel clickcps = new JLabel();
	private static AbstractFrame entites;
	private static AbstractFrame upgrades;

	private static boolean running = true;

	public static Quantity last = new Quantity();

	public static void main(String[] args) {
		ModController modCon = ModController.getInstance();
		File f = new File("Mods");
		if (f.exists() && f.isDirectory())
			for (File mod : f.listFiles()) {
				try {
					modCon.setMod(Mod.load(mod));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		frame = new Frame();

		quit.setBackground(Color.RED);
		quit.addActionListener(l -> {
			frame.quitter();
		});

		click.addActionListener(l -> {
			Player.getInstance().getMoney().add(Player.getInstance().getClickMult());
			money.setText(Player.getInstance().getMoney().toString());
		});
		clickcps.setText("0.0 clickcps");
		cps.setText("0.0 cps");
		menu();

		Thread t = new Thread("Action of Entities") {
			public void run() {
				while (running) {
					try {
						Player p = Player.getInstance();
						Quantity a = new Quantity(Player.getInstance().getMoney());
						a.sub(last);
						Main.clickcps.setText(a.toString() + " clickcps");
						a = new Quantity(Player.getInstance().getMoney());

						for (Entity ent : EntityController.getInstance().getEntities()) {
							Quantity i = new Quantity(ent.production(p.getEntityQuantity(ent)));
							i.mult(new Quantity(ent.multiplier));
							Player.getInstance().getMoney().add(i);
						}
						money.setText(Player.getInstance().getMoney().toString());
						Quantity b = new Quantity(Player.getInstance().getMoney());
						b.sub(a);
						cps.setText(b + " cps");

						last = new Quantity(Player.getInstance().getMoney());

						sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

	public static void updateUpgrades() {
		upgrades.removeAll();
		Upgrade[] ups = UpgradeController.getInstance().getUpgrades();
		Dimension d = new Dimension(320, 200);
		for (int i = 0; i < ups.length; i++) {
			final JButton b = new JButton();
			b.setIcon(ups[i].icon());
			final int a = i;
			b.addActionListener(k -> {
				UpgradeController.getInstance().buy(ups[a]);
			});
			upgrades.setBouton(
					"<html>" + ups[i].name() + "<br>" + ups[i].desc() + "<br>" + ups[i].price() + " cookies" + "<html>",
					0, i, b);

			b.setSize(d);
			b.setPreferredSize(d);
			b.setMaximumSize(d);
			b.setMinimumSize(d);
		}
		upgrades.revalidate();
		upgrades.repaint();
	}

	public static void menu() {
		frame.p.removeAll();

		AbstractFrame g = frame.p.setGroup(1, 0);
		g.setLabel(0, 0, Player.getInstance().getMoney().toString(), money);
		g.setLabel(0, 1, clickcps.getText(), clickcps);
		g.setLabel(0, 2, cps.getText(), cps);

		frame.p.setBouton("Cookie", 1, 1, click);

		Slider s = frame.p.setSlider(2, 0, .25, .8, new Slider());

		s.p.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		Dimension d = new Dimension(350, (int) (frame.p.getSize().getHeight() * .8));

		if (d.getHeight() < 1) {
			menu();
			return;
		}

		s.setSize(d);
		s.setPreferredSize(d);
		s.setMaximumSize(d);
		s.setMinimumSize(d);

		entites = s.p;

		Entity[] ents = EntityController.getInstance().getEntities();
		d = new Dimension(320, 100);
		for (int i = 0; i < ents.length; i++) {
			final JButton b = new JButton();
			b.setIcon(ents[i].icon());
			final int a = i;
			b.addActionListener(k -> {
				if (Player.getInstance().getMoney()
						.compareTo(ents[a].price(Player.getInstance().getEntityQuantity(ents[a]))) > -1) {
					Player.getInstance().getMoney()
							.sub(new Quantity(ents[a].price(Player.getInstance().getEntityQuantity(ents[a]))));
					last.sub(new Quantity(ents[a].price(Player.getInstance().getEntityQuantity(ents[a]))));

					Player.getInstance().getEntityQuantity(ents[a]).add(Quantity.valueOf(1));

					money.setText(Player.getInstance().getMoney().toString());

					Quantity prod = new Quantity(
							ents[a].production(new Quantity(Player.getInstance().getEntityQuantity(ents[a]))));

					prod.mult(ents[a].multiplier);

					Quantity q = Player.getInstance().getEntityQuantity(ents[a]);
					b.setText("<html>" + q + " " + ents[a].name() + "<br>" + prod + " cps<br>" + ents[a].price(q)
							+ " cookies" + "<html>");
					updateUpgrades();
				}
			});
			Quantity prod = new Quantity(
					ents[i].production(new Quantity(Player.getInstance().getEntityQuantity(ents[i]))));

			prod.mult(ents[i].multiplier);
			Quantity q = Player.getInstance().getEntityQuantity(ents[i]);
			entites.setBouton("<html>" + q + " " + ents[i].name() + "<br>" + prod + " cps<br>" + ents[i].price(q)
					+ " cookies" + "<html>", 0, i, b);

			b.setSize(d);
			b.setPreferredSize(d);
			b.setMaximumSize(d);
			b.setMinimumSize(d);

		}

		Slider upslider = frame.p.setSlider(3, 0, .25, .8, new Slider());

		upslider.p.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		d = new Dimension(350, (int) (frame.p.getSize().getHeight() * .8));

		if (d.getHeight() < 1) {
			menu();
			return;
		}

		upslider.setSize(d);
		upslider.setPreferredSize(d);
		upslider.setMaximumSize(d);
		upslider.setMinimumSize(d);

		upgrades = upslider.p;

		updateUpgrades();

		frame.repaint();
	}
}
