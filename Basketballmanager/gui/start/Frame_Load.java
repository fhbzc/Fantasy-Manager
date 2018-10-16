package Basketballmanager.gui.start;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Basketballmanager.main.start.Main;
import Basketballmanager.util.ImgUtil;

public class Frame_Load extends JFrame{
	/**
	 * Load Logo of NBA as a image before initilization of game
	 */
	private static final long serialVersionUID = -5475941235030060504L;
	BufferedImage img ;
	
	/**
     * Variable list
     * serialVersionUID: Version control
     * img: Image of logo
     */
	public Frame_Load(){
		img = ImgUtil.getJpgImgByName("logo");
		setUI();
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				showIt();
			}
		};
		new Thread(run).start();
	}

	public void setUI(){
		/**
		 * Set UI
		 * input:
		 * output:
		 */
		setBounds(new Rectangle(550, 400));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
	}

	public void paint(Graphics g){
		/**
		 * Drawing backgrounds
		 */
		if(img!=null){
			g.drawImage(img, 0, 0, null);
		}
	}

	public void showIt(){
		/**
		 * Show logo until the end of game panel initializaton
		 */
		while(!Main.isFinished){}
		setVisible(false);
		dispose();
	}
	
}
