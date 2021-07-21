package ca.RedYou.Game;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	private static AbstractFrame entites;

	private static boolean running = true;

	public static void main(String[] args) {
		Quantity q = Quantity.valueOf("9999", true);
		System.out.println(q);
		System.out.println(q.extract());
//		ModController modCon = ModController.getInstance();
//		File f = new File("Mods");
//		if (f.exists() && f.isDirectory())
//			for (File mod : f.listFiles()) {
//				try {
//					modCon.setMod(Mod.load(mod));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		frame = new Frame();
//
//		quit.setBackground(Color.RED);
//		quit.addActionListener(l -> {
//			frame.quitter();
//		});
//
//		click.addActionListener(l -> {
//			Player.getInstance().getMoney().addOrSub(1);
//			menu();
//		});
//		menu();
//
//		Thread t = new Thread("Action of Entities") {
//			public void run() {
//				while (running) {
//					try {
//						Player p = Player.getInstance();
//						List<Entity> ents = new ArrayList<>(EntityController.getInstance().getEntities());
//						for (Entity ent : ents) {
//							ent.action(p.getEntityQuantity(ent));
//						}
//						money.setText(Player.getInstance().getMoney().toString());
//						sleep(1000);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		t.start();
	}

	private static void menu() {
//		frame.p.removeAll();
//
//		frame.p.setLabel(1, 0, Player.getInstance().getMoney().toString(), money);
//		frame.p.setBouton("Cookie", 1, 1, click);
//
//		entites = frame.p.setSlider(2, 0, .25, .8, new Slider()).p;
//
//		Collection<Entity> Cents = EntityController.getInstance().getEntities();
//		Entity[] ents = Cents.toArray(new Entity[Cents.size()]);
//		for (int i = 0; i < ents.length; i++) {
//			JButton b = new JButton();
//			b.setIcon(ents[i].icon());
//			final int a = i;
//			b.addActionListener(k -> {
//				if (Player.getInstance().getMoney()
//						.compareTo(ents[a].price(Player.getInstance().getEntityQuantity(ents[a]))) > -1) {
//					Quantity q = new Quantity(ents[a].price(Player.getInstance().getEntityQuantity(ents[a])));
//					q.positif = false;
//					Player.getInstance().getMoney().addOrSub(q);
//
//					q = Player.getInstance().getEntityQuantity(ents[a]);
//					q.addOrSub(1);
//
//					menu();
//				}
//			});
//			Quantity q = Player.getInstance().getEntityQuantity(ents[i]);
//			entites.setBouton(
//					"<html>" + q.toString() + " " + ents[i].name() + "<br>" + ents[i].price(q).toString() + "<html>", 0,
//					i, b);
//		}
//
//		frame.repaint();
	}
}
