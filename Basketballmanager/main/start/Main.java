package Basketballmanager.main.start;

import Basketballmanager.gui.main.Frame_Main;
import Basketballmanager.gui.start.Frame_Load;

/**
 * Game: Basketball Management 
 * Author Hongbo Fang
 * Date 2018-01-27
 * This is a multi-player computer game built with socket.
 * Players connect with each other and manipulate players to compete in a virtual NBA game
 * Only one plot is open for now, more work should be done in the future
 */

public class Main {
	/**
	 * Main function
	 * The entrance of the game
	 */
	public static String version = "alpha-17-12-27";/* version control, not used */
	
	public static boolean isFinished;
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {				
				/* Initialation of welcome panel */
				new Frame_Load();
			}
		}).start();
		/* Main panel */
		new Frame_Main();
	}
}