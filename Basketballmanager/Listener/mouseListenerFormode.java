package Basketballmanager.Listener;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Basketballmanager.constant.Const_Game;
import Basketballmanager.gui.gamechoose.background;
import Basketballmanager.gui.gamechoose.modeChoose;
import Basketballmanager.gui.gamechoose.modeChooseLayer2;
import Basketballmanager.gui.gamechoose.modeChooseLayer3;

public class mouseListenerFormode implements MouseListener{

	/**
	 * Class of listener
	 */
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		
		
		if(e.getSource()==modeChoose.chooseDynasty)
		{
			background.output.setVisible(false);
			background.ChooseMode.setVisible(false);
			background.ChooseMode2.setVisible(true);
		}
		else if(e.getSource()==modeChoose.chooseCrazy)
		{
			background.output.setVisible(false);
			background.ChooseMode.setVisible(false);
			background.ChooseMode3.setVisible(true);
		}
		else if(e.getSource()==modeChooseLayer2.Back)
		{
			background.output.setVisible(false);
			background.ChooseMode2.setVisible(false);
			background.ChooseMode.setVisible(true);
		}
		else if(e.getSource()==modeChooseLayer2.Dynasty2015)//enter dynasty plot
		{
			background.output.setVisible(false);
			background.ChooseMode2.setVisible(false);
			background.image=Toolkit.getDefaultToolkit().createImage(  
	        		background.class  
                    .getResource("/img/final2015.jpg"));  
			background.processBar.setVisible(true);
			EnactProgress(Const_Game.Dynasty_2015_final);
		}
		else if(e.getSource()==modeChooseLayer3.Back)
		{
			background.output.setVisible(false);
			background.ChooseMode3.setVisible(false);
			background.ChooseMode.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==modeChoose.chooseDynasty)
		{
			background.output.setText("Dynasty mode");
			background.output.setVisible(true);
		}
		else if(e.getSource()==modeChoose.chooseCrazy)
		{
			background.output.setText("Crazy mode");
			background.output.setVisible(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==modeChoose.chooseDynasty)
		{
			background.output.setVisible(false);
		}
		else if(e.getSource()==modeChoose.chooseCrazy)
		{
			background.output.setVisible(false);
		}

	}
	private void EnactProgress(int type)
	{
		/**
		 * initialization progress bar
		 */
        new Thread(){  
            
            public void run(){  
            	
            	background.GameMain.gameThread.setType(type);
            	background.GameMain.gameThread.start();

            	/* initialize a new thread */
            	
                for (int i = 0; i < 101; i++) {  
                    try {  
                        Thread.sleep(10);  
                    } catch (InterruptedException e) {  
                        // TODO: handle exception  
                        e.printStackTrace();  
                    }  
                    background.processBar.setValue(i); /* set process bar value */
                }  
                
                background.processBar.setString("Waiting for other players");
                
                background.GameMain.gameThread.setReadyChange(true);
                
            }  
        }.start(); //  启动进度条线程 
	}
}
