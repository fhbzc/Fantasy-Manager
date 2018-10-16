package Basketballmanager.gui.onGame;

import java.awt.Color;

import javax.swing.JPanel;

import Basketballmanager.constant.Const_UI;

public class controlPanelDefense extends JPanel{

	/**
	 * Class of panel designed to manipulate defense
	 */
	public static ControlDefenseChoose chooseBar=new ControlDefenseChoose();
	public static OrganizePanel organize=new OrganizePanel();

	public static defenseSt defense=new defenseSt();

	/**
     * Variable list
     * ControlDefenseChoose: Panel for manipulation of defense detail, first
     * organize: Panel for manipulation of defense detail, second
     * defenseSt: Panel for selection of defense strategy
     */
	
	
	controlPanelDefense()
	{
		this.setSize(Const_UI.CONTROLDETAIL_WIDTH, Const_UI.CONTROLDETAIL_HEIGHT);
		this.setLayout(null);
		
		chooseBar.setLocation(0,0);
		organize.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);

		defense.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);
		

		this.setBackground(Color.BLUE);
		this.add(chooseBar);
		this.add(organize);

		this.add(defense);

		this.setVisible(false);
	}
}
