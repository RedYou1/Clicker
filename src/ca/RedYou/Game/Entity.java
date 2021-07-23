package ca.RedYou.Game;

import javax.swing.ImageIcon;

public abstract class Entity {

	public Quantity multiplier = Quantity.valueOf(1);

	public abstract String name();

	public abstract ImageIcon icon();

	public abstract Quantity production(Quantity quantity);

	public abstract Quantity price(Quantity quantity);
}
