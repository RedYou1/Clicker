package ca.RedYou.Game;

import javax.swing.ImageIcon;

import ca.RedDevKit.BigNum;

public interface Upgrade {

	public ImageIcon icon();

	public String name();

	public String actiondesc();

	public String desc();

	public BigNum price();

	public void action();
}
