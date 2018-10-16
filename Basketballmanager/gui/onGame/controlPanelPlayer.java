package Basketballmanager.gui.onGame;

import java.awt.Color;

import javax.swing.JPanel;

import Basketballmanager.constant.Const_Game;
import Basketballmanager.constant.Const_UI;

public class controlPanelPlayer extends JPanel{
	/**
	 * Class of panel designed to manipulate player
	 */
	public playerPortait[] rotation=new playerPortait[Const_Game.maxPlayer-5];
	
	/**
     * Variable list
     * playerPortait: List of players and its detailed information
     */
	controlPanelPlayer()
	{
		this.setSize(Const_UI.CONTROLDETAIL_WIDTH, Const_UI.CONTROLDETAIL_HEIGHT);
		this.setLayout(null);
		for(int i=0;i<Const_Game.maxPlayer-5;i++)
		{
			rotation[i]=new playerPortait();
		}
		rotation[0].setLocation(Const_UI.Reverse_1_X, Const_UI.Reverse_1_Y);
		rotation[1].setLocation(Const_UI.Reverse_2_X, Const_UI.Reverse_2_Y);
		rotation[2].setLocation(Const_UI.Reverse_3_X, Const_UI.Reverse_3_Y);
		rotation[3].setLocation(Const_UI.Reverse_4_X, Const_UI.Reverse_4_Y);
		rotation[4].setLocation(Const_UI.Reverse_5_X, Const_UI.Reverse_5_Y);
		rotation[5].setLocation(Const_UI.Reverse_6_X, Const_UI.Reverse_6_Y);
		rotation[6].setLocation(Const_UI.Reverse_7_X, Const_UI.Reverse_7_Y);

		for(int i=0;i<Const_Game.maxPlayer-5;i++)
		{
			rotation[i].setVisible(true);
			rotation[i].setOpaque(true);
			this.add(rotation[i]);
		}
		this.setBackground(Color.YELLOW);
		this.setVisible(true);

	}
}
