package Basketballmanager.gui.gamechoose;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.Listener.mouseListenerFormode;
import Basketballmanager.constant.Const_UI;

public class modeChooseLayer3 extends JPanel{
	/**
	 * Class to choose the player of the game
	 */
	
	private static final long serialVersionUID = 1L;
	public static JButton VSComp=new JButton("VS Computer");
	public static JButton VSHum=new JButton("VS Human");
	public static JButton Back=new JButton("Return");
	private mouseListenerFormode Listener=new mouseListenerFormode();
	
    /**
     * Variable list
     * serialVersionUID: Version control, not used
     * VSComp: Button for choosing "VS Computer"
     * VSHum: Button for choosing "VS Human"
	 * Back: Button for choosing "Return"(return to previous menu
	 * Listener: Listener to handle mouse event
     */
	
	
	
	modeChooseLayer3()
	{
		/**
		 * initialization of modeChooseLayer3
		 */
		this.setSize(Const_UI.MODE_WIDTH,Const_UI.MODE_HEIGHT);
		this.setLocation(Const_UI.MODE_X, Const_UI.MODE_Y);
		this.setLayout(new GridLayout(11,1));
		VSComp.addMouseListener(Listener);
		VSHum.addMouseListener(Listener);
		Back.addMouseListener(Listener);
		this.add(VSComp);
		this.add(VSHum);
		this.add(Back);
		this.setOpaque(false);
		this.setVisible(false);
	}
}