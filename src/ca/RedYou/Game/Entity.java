package ca.RedYou.Game;

import javax.swing.ImageIcon;

public interface Entity {

	public String name();

	public ImageIcon icon();

	public void action(Quantity quantity);

	public Quantity price(Quantity quantity);
}
