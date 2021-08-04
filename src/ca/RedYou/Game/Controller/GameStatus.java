package ca.RedYou.Game.Controller;

import java.io.File;

public class GameStatus {

	public static void start() {
		ModController.getInstance().start();
		Player.getInstance().start();
		UpgradeController.getInstance().start();
	}

	public static void stop() {
		if (!new File("save").exists())
			new File("save").mkdirs();
		ModController.getInstance().stop();
		Player.getInstance().stop();
		UpgradeController.getInstance().stop();
	}
}
