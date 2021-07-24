package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.RedYou.Game.Controller.Player;

public abstract class Entity {

	public Quantity multiplier = Quantity.valueOf(1);

	public List<Function<Entity, Void>> buyEvents = new ArrayList<>();

	public abstract String name();

	public abstract ImageIcon icon();

	public abstract Quantity production(Quantity quantity);

	public abstract Quantity price(Quantity quantity);

	public List<Function<Entity, Void>> getBuyEvents() {
		return buyEvents;
	}

	public void buy(JButton button) {
		Quantity q = Player.getInstance().getEntityQuantity(this);
		if (Player.getInstance().getMoney().compareTo(price(q)) > -1) {
			Player.getInstance().getMoney().sub(new Quantity(price(q)));
			Main.last.sub(new Quantity(price(q)));

			q.add(Quantity.valueOf(1));

			Main.money.setText(Player.getInstance().getMoney().toString());

			Quantity prod = new Quantity(production(new Quantity(q)));

			prod.mult(multiplier);
			button.setText("<html>" + q + " " + name() + "<br>" + prod + " cps<br>" + price(q) + " cookies" + "<html>");

			for (Function<Entity, Void> a : buyEvents) {
				a.apply(this);
			}

			Main.updateUpgrades();
		}
	}
}
