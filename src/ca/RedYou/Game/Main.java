package ca.RedYou.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.*;

import ca.RedDevKit.Pages.AbstractFrame;
import ca.RedDevKit.Pages.Frame;
import ca.RedDevKit.Pages.Slider;
import ca.RedYou.Game.Controller.*;

public class Main {

	public static Frame frame;

	public static JButton click = new JButton();
	public static JButton quit = new JButton();
	public static JLabel money = new JLabel();
	public static JLabel cps = new JLabel();
	public static JLabel clickcps = new JLabel();
	public static AbstractFrame entites;
	public static AbstractFrame upgrades;

	public static boolean running = true;

	public static Quantity last = new Quantity();

	public static void main(String[] args) {
		GameStatus.start();

		frame = new Frame();

		quit.setBackground(Color.RED);
		quit.addActionListener(l -> {
			GameStatus.stop();
			running = false;
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

						Thread t = new Thread("Action of Entities Calc") {
							public void run() {
								Player p = Player.getInstance();
								Quantity m = p.getMoney();

								Quantity a = new Quantity(m);
								a.sub(last);
								clickcps.setText(a.toString() + " clickcps");

								a = new Quantity();

								for (Entity ent : EntityController.getInstance().getEntities()) {
									Quantity i = new Quantity(ent.production(p.getEntityQuantity(ent)));
									i.mult(new Quantity(ent.multiplier));

									a.add(i);
									m.add(i);
								}
								money.setText(m.toString());
								cps.setText(a + " cps");

								last = new Quantity(m);
							}
						};
						t.start();

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

		Arrays.sort(ups, new Comparator<Upgrade>() {
			@Override
			public int compare(Upgrade o1, Upgrade o2) {
				return o1.price().compareTo(o2.price());
			}
		});

		Dimension d = new Dimension(320, 200);
		for (int i = 0; i < ups.length; i++) {
			final JButton b = new JButton();
			b.setIcon(ups[i].icon());
			final int a = i;
			b.addActionListener(k -> {
				UpgradeController.getInstance().buy(ups[a]);
			});
			String t1 = "<html><center>" + ups[i].name() + "<br>" + ups[i].price() + " cookies" + "<html>";
			String t2 = "<html><center>" + ups[i].desc() + "<html>";
			upgrades.setBouton(t1, 0, i, b);

			b.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
					b.setText(t1);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					b.setText(t2);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
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

		JButton refresh = new JButton();
		refresh.addActionListener(l -> {
			menu();
		});

		frame.p.setBouton("Cookie", 1, 1, click);

		frame.p.setBouton("refresh screen", 2, 1, refresh);

		frame.p.setBouton("Save/Quit", 3, 1, quit);

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
				ents[a].buy(b);
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
