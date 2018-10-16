package Basketballmanager.gui.gamechoose;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.Listener.mouseListenerFormode;
import Basketballmanager.constant.Const_UI;

public class modeChooseLayer2 extends JPanel{
	/**
	 * Class to choose the plot of the game
	 */
	private static final long serialVersionUID = 1L;
	public static JButton Dynasty2015=new JButton("2015 Finals Warrior-Caverlier");
	public static JButton Back=new JButton("Return");
	private mouseListenerFormode Listener=new mouseListenerFormode();
	
    /**
     * Variable list
     * serialVersionUID: Version control, not used
     * Dynasty2015: Button for choosing plot "2015 Finals Warrior-Caverlier"
     * Back: Button for choosing "Return"(return to previous menu 
	 * Listener: Listener to handle mouse event
     */
	
	
	modeChooseLayer2()
	{
		/**
		 * initialization of modeChooseLayer2
		 */
		this.setSize(Const_UI.MODE_WIDTH,Const_UI.MODE_HEIGHT);
		this.setLocation(Const_UI.MODE_X, Const_UI.MODE_Y);
		this.setLayout(new GridLayout(11,1));
		Dynasty2015.addMouseListener(Listener);
		Back.addMouseListener(Listener);
		this.add(Dynasty2015);
		this.add(Back);
		this.setOpaque(false);
		this.setVisible(false);
	}
}
