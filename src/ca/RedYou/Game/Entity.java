package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.RedDevKit.BigNum;
import ca.RedYou.Game.Controller.Player;

public abstract class Entity {

	public BigNum multiplier = BigNum.valueOf(1);

	public List<Function<Entity, Void>> buyEvents = new ArrayList<>();

	public abstract String name();

	public abstract ImageIcon icon();

	public abstract BigNum production(BigNum quantity);

	public abstract BigNum price(BigNum quantity);

	public List<Function<Entity, Void>> getBuyEvents() {
		return buyEvents;
	}

	public void buy(JButton button) {
		BigNum q = Player.getInstance().getEntityBigNum(this);
		if (Player.getInstance().getMoney().compareTo(price(q)) > -1) {
			Player.getInstance().getMoney().sub(new BigNum(price(q)));
			Main.last.sub(new BigNum(price(q)));

			q.add(BigNum.valueOf(1));

			Main.money.setText(Player.getInstance().getMoney().toString());

			BigNum prod = new BigNum(production(new BigNum(q)));

			prod.mult(multiplier);
			button.setText("<html><B>" + q + " " + name() + "</B><br>" + prod + " cps<br><I>" + price(q)
					+ " cookies</I><html>");

			for (Function<Entity, Void> a : buyEvents) {
				a.apply(this);
			}

			Main.updateUpgrades();
		}
	}
}
