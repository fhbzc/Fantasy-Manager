package Basketballmanager.gui.onGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.client.Myclient;
import Basketballmanager.constant.Const_UI;

public class rotateBar extends JPanel implements ActionListener{
	/**
	 * Class of panel designed to rotate player
	 */
	JButton rotateTo[]=new JButton[5];
	public int Source=-1;
	
	/**
     * Variable list
     * rotateTo: List of button handling how to rotate
     * Source: Rotate source
     */
	
	rotateBar(){
		this.setSize(Const_UI.RotatePanel_WIDTH, Const_UI.RotatePanel_HEIGHT);
		this.setLayout(new GridLayout(1,5,3,2));
		this.setVisible(false);
		for(int i=0;i<rotateTo.length;i++)
		{
			rotateTo[i]=new JButton("Position "+(i+1));
			rotateTo[i].addActionListener(this);
			this.add(rotateTo[i]);
			
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<rotateTo.length;i++)
		{
			if(e.getSource()==rotateTo[i])
			{
				int temp=Myclient.PlayerIndexMe[i];//i is the target
				Myclient.PlayerIndexMe[i]=Myclient.PlayerIndexMe[5+Source];
				Myclient.PlayerIndexMe[5+Source]=temp;
				
				gamePanel.ControlBar.PlayerDetail.rotation[Source].setName("Change to "+"Position "+(i+1));

				Myclient.RotatePlayerThis=true;
				
				break;
			}
		}
	}
}
