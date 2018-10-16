package Basketballmanager.gui.gamechoose;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Basketballmanager.Listener.mouseListenerFormode;
import Basketballmanager.constant.Const_UI;


public class modeChoose extends JPanel {
	/**
	 * Class to choose the mode of the game
	 */
	private static final long serialVersionUID = 1L;
	public static JButton chooseDynasty=new JButton("Dynasty mode");
	public static JButton chooseCrazy=new JButton("Crazy mode");
	private mouseListenerFormode Listener=new mouseListenerFormode();
	
    /**
     * Variable list
     * serialVersionUID: Version control, not used
     * chooseDynasty: Button for choosing "Dynasty mode"
     * chooseCrazy: Button for choosing "Crazy mode"
	 * Listener: Listener to handle mouse event
     */
	
	
	modeChoose()
	{
		/**
		 * initialization of modeChoose
		 */
		this.setSize(Const_UI.MODE_WIDTH,Const_UI.MODE_HEIGHT);
		this.setLocation(Const_UI.MODE_X, Const_UI.MODE_Y);
		this.setLayout(new GridLayout(11,1));
		chooseDynasty.addMouseListener(Listener);
		chooseCrazy.addMouseListener(Listener);
		this.add(chooseDynasty);
		this.add(chooseCrazy);
		this.setOpaque(false);
		this.setVisible(true);
	}
	
}
