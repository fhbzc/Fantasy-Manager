package Basketballmanager.gui.onGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Basketballmanager.Listener.mouseListenerForPortait;
import Basketballmanager.constant.Const_UI;

public class playerPortait extends JPanel {
	/**
	 * Class of portait of player
	 */
	private JLabel Name=new JLabel();
	private JLabel Picture=new JLabel();
	

	private mouseListenerForPortait listener=new mouseListenerForPortait();
	
	/**
     * Variable list
     * Name: Label showing the name of this player
     * Picture: JLabel showing the image of this player
     * listener: Listener responsing to mouse event
     */
	playerPortait()
	{
		
		this.setSize(Const_UI.PLAYER_WIDTH,Const_UI.PLAYER_HEIGHT);
		this.setLayout(null);
		Name.setSize(Const_UI.PLAYER_WIDTH,Const_UI.PLAYER_HEIGHT-Const_UI.PORTAIT_HEIGHT);
		Name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Name.setHorizontalAlignment(JLabel.CENTER);
		Name.setLocation(0,Const_UI.PORTAIT_HEIGHT);
		Picture.setSize(Const_UI.PORTAIT_WIDTH,Const_UI.PORTAIT_HEIGHT);
		Picture.setLocation(0, 0);
		Picture.setBackground(Color.BLACK);
		
		Name.setOpaque(true);
		Picture.setOpaque(true);
		this.addMouseListener(listener);
		this.add(Picture);
		this.add(Name);
	}
	
	public void setName(String name)
	{
		/**
		 * set the name of player
		 * input:
		 * 		name(String): name of the player
		 * output:
		 */
		Name.setText(name);
	}
	
	public void setImageByIcon(Icon picture)
	{
		/**
		 * set the image(in Icon format) of player
		 * input:
		 * 		picture(Icon): image of the player
		 * output:
		 */
		Picture.setIcon(picture);
	}
	
	public void setImageByImageIcon(ImageIcon picture)
	{
		/**
		 * set the image(in ImageIcon format) of player
		 * input:
		 * 		picture(Icon): ImageIcon of the player
		 * output:
		 */
		picture.setImage(picture.getImage().  
                getScaledInstance(picture.getIconWidth(),picture.getIconHeight(), Image.SCALE_DEFAULT));
		Picture.setIcon(picture);
	}
}
