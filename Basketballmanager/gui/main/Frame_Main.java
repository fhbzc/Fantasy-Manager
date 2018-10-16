package Basketballmanager.gui.main;

import java.util.List;

import javax.swing.JFrame;

import Basketballmanager.main.start.Main;
import Basketballmanager.constant.Const_UI;
import Basketballmanager.deprecated.Information;
import Basketballmanager.gui.gamechoose.background;

public class Frame_Main extends JFrame {
	/**
	 * Class of Main panel 
	 */
	private static final long serialVersionUID = -6905419069196737546L;
	public static Frame_Main me;
	private background Background;
    
	/**
     * Variable list
     * serialVersionUID: Version control
     * Frame_Main: Main panel
     * Background: background panel
     */
	
	
	
	public Frame_Main() {
		/**
		 * initialization of Frame_Main and start game
		 */
		me = this;
		GameOn();
	}
	
	private void GameOn()
	{
		/**
		 * initialization of game process
		 */
		CreateUI();
	}
	
	void CreateUI()
	{
		/**
		 * create UI interface
		 */
		System.out.println("Initialization of Panel");
		this.setTitle("BaskballManager"+"--"+Main.version);
		this.setSize(Const_UI.FRAME_WIDTH, Const_UI.FRAME_HEIGHT);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		Background=new background();
		this.setContentPane(Background);//add background panel
		System.out.println("Ready to display panel");
		Main.isFinished = true;
		
		this.setVisible(true);
		System.out.println("Finish");
	}
}
