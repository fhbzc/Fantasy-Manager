package Basketballmanager.gui.onGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.constant.Const_Game;
import Basketballmanager.constant.Const_UI;

public class defenseSt extends JPanel implements ActionListener {
	/**
	 * Class of panel designed to select defense strategy
	 */
	
	
	JButton[] button = new JButton[10];
	private int choose=0;

	/**
     * Variable list
     * button: List of buttons for choosing one specific strategy
     * choose: Current selected strategy
     */
	defenseSt()
	{
		this.setSize(Const_UI.Meddling_WIDTH, Const_UI.Meddling_HEIGHT);
		this.setVisible(false);
		this.setLayout(new GridLayout(2,5,3,2));
		for(int i=0;i<10;i++)
		{
			button[i]=new JButton(Const_Game.Defst[i]);
			button[i].setFocusPainted(false);
			button[i].addActionListener(this);
			this.add(button[i]);
		}
		button[0].setBackground(Color.YELLOW);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * response to mouse event
		 * set the backrgound and panel
		 * update strategy
		 */
		// TODO Auto-generated method stub

		for(int i=0;i<10;i++)
		{
			if(e.getSource()==button[i])
			{
				choose=i;
				SetBackground(i);
				break;
			}
		}
	}
	
	private void SetBackground(int index)
	{
		/**
		 * set the background of selected button
		 */
		for(int i=0;i<10;i++)
		{
			button[i].setBackground(null);
		}
		button[index].setBackground(Color.YELLOW);
	}
	
	public int getChoose()
	{
		return choose;
	}
}
