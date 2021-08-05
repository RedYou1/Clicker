package ca.RedYou.Game;

import javax.swing.ImageIcon;

public interface Upgrade {

	public ImageIcon icon();

	public String name();

	public String actiondesc();

	public String desc();

	public Quantity price();

	public void action();
}
