package Basketballmanager.gui.onGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.client.Myclient;
import Basketballmanager.constant.Const_UI;

public class controlBarChoose extends JPanel implements ActionListener{
	/**
	 * class of control bar(left side in manipulation panel) for selecting the right side control bar
	 */
	public JButton TimeOutShort=new JButton("TimeOut(5)");
	public JButton Offense=new JButton("Offense Strategy");
	public JButton Defense=new JButton("Defense Strategy");
	public JButton Player=new JButton("Player Information");
	
	/**
     * Variable list
     * TimeOutShort: Button for calling timeout
     * Offense: Button for selecting offense panel
     * Defense: Button for selecting defense panel
     * Player: Button for selecting player panel
     */
	
	
	controlBarChoose()
	{
		this.setLocation(0, 0);
		this.setSize(Const_UI.CONTROLSTATE_WIDTH, Const_UI.CONTROLSTATE_HEIGHT);
		this.setLayout(new GridLayout(4,1));
		TimeOutShort.addActionListener(this);
		Offense.addActionListener(this);
		Defense.addActionListener(this);
		Player.addActionListener(this);

		this.add(TimeOutShort);
		this.add(Offense);
		this.add(Defense);
		this.add(Player);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * response to mouse action
		 * have effect on the selection of different panels
		 */
		// TODO Auto-generated method stub
		if(e.getSource()==TimeOutShort)
		{
			/* call timeout */
			Myclient.TimeoutThis=true;
		}
		else if(e.getSource()==Offense)
		{
			/* if choose offense panel */
			gamePanel.ControlBar.OffenseDetail.setVisible(true);
			gamePanel.ControlBar.PlayerDetail.setVisible(false);
			gamePanel.ControlBar.DefenseDetail.setVisible(false);

		}
		else if(e.getSource()==Defense)
		{
			/* if choose defense panel */
			gamePanel.ControlBar.OffenseDetail.setVisible(false);
			gamePanel.ControlBar.PlayerDetail.setVisible(false);
			gamePanel.ControlBar.DefenseDetail.setVisible(true);

		}
		else if(e.getSource()==Player)
		{
			/* if choose player panel */
			gamePanel.ControlBar.OffenseDetail.setVisible(false);
			gamePanel.ControlBar.PlayerDetail.setVisible(true);
			gamePanel.ControlBar.DefenseDetail.setVisible(false);

		}
	}
}
