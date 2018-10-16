package Basketballmanager.gui.onGame;

import javax.swing.JPanel;

import Basketballmanager.constant.Const_UI;

public class controlBar extends JPanel{
	
	/**
	 * Class of panel designed to manipulate players
	 * 
	 */
	
	public controlBarChoose BarChoose=new controlBarChoose();
	public controlPanelPlayer PlayerDetail= new controlPanelPlayer();
	public controlPanelOffense OffenseDetail= new controlPanelOffense();
	public controlPanelDefense DefenseDetail = new controlPanelDefense();
	public chattingPanel ChatPanel = new chattingPanel();
	
	/**
     * Variable list
     * BarChoose: List of buttons for selection of panel 
     * PlayerDetail: Panel for manipulation of player
     * OffenseDetail: Panel for manipulation of offense
     * DefenseDetail: Panel for manipulation of defense
     * ChatPanel: Panel for chatting
     */
	
	
	
	controlBar()
	{
		this.setSize(Const_UI.CONTROL_WIDTH,Const_UI.CONTROL_HEIGHT);
		this.setLayout(null);
		BarChoose.setLocation(0, 0);
		
		PlayerDetail.setLocation(Const_UI.CONTROLDETAIL_X, Const_UI.CONTROLDETAIL_Y);
		OffenseDetail.setLocation(Const_UI.CONTROLDETAIL_X, Const_UI.CONTROLDETAIL_Y);
		DefenseDetail.setLocation(Const_UI.CONTROLDETAIL_X, Const_UI.CONTROLDETAIL_Y);
		ChatPanel.setLocation(Const_UI.CHAT_X,Const_UI.CHAT_Y);
		
		this.add(BarChoose);
		this.add(PlayerDetail);
		this.add(OffenseDetail);
		this.add(DefenseDetail);
		this.add(ChatPanel);

		
	}
}
