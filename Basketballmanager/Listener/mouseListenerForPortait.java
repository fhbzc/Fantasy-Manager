package Basketballmanager.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Basketballmanager.constant.Const_Game;
import Basketballmanager.gui.gamechoose.background;
import Basketballmanager.gui.onGame.gamePanel;

public class mouseListenerForPortait implements MouseListener{

	private int Current_Source=-1;
	private boolean Visible=false;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<Const_Game.maxPlayer-5;i++)
			if(e.getSource()==gamePanel.ControlBar.PlayerDetail.rotation[i])
			{
				
				if(Current_Source==i && Visible==true)
				{
					gamePanel.RotateBar.setVisible(false);
					Visible=false;
				}
				else
				{
					Visible=true;
					gamePanel.RotateBar.Source=i;
					gamePanel.RotateBar.setVisible(true);
				}
				
				
				Current_Source=i;
				break;
			}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
