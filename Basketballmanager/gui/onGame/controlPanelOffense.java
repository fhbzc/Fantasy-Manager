package Basketballmanager.gui.onGame;

import java.awt.Color;

import javax.swing.JPanel;

import Basketballmanager.constant.Const_UI;

public class controlPanelOffense extends JPanel{
	
	/**
	 * Class of panel designed to manipulate offense
	 */
	
	public static ControlOffenseChoose chooseBar=new ControlOffenseChoose();
	public static OrganizePanel organize=new OrganizePanel();
	public static OrganizePanel scoreArr=new OrganizePanel();
	public static OrganizePanel attack=new OrganizePanel();
	public static offenseSt Offense=new offenseSt();

	/**
     * Variable list
     * chooseBar: Panel for manipulation of offense detail
     * organize: Panel for organizing distribution of ball-right
     * scoreArr: Panel for organizing distribution of score opportunity
     * Offense: Panel for selection of offense strategy
     */
	
	controlPanelOffense()
	{
		this.setSize(Const_UI.CONTROLDETAIL_WIDTH, Const_UI.CONTROLDETAIL_HEIGHT);
		this.setLayout(null);
		
		chooseBar.setLocation(0,0);
		organize.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);
		scoreArr.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);
		attack.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);
		Offense.setLocation(Const_UI.Meddling_X, Const_UI.Meddling_Y);
		
		scoreArr.setVisible(false);
		attack.setVisible(false);

		this.setBackground(Color.BLUE);
		this.add(chooseBar);
		this.add(organize);
		this.add(scoreArr);
		this.add(attack);
		this.add(Offense);

		this.setVisible(false);
	}
}
