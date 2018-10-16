package Basketballmanager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Basketballmanager.gui.gamechoose.background;


public class ImgUtil {
	/**
	 * utility class:
	 * loading image from local address
	 * @param name
	 * @return
	 */
	public static BufferedImage getJpgImgByName(String name){
		BufferedImage bfimg = null;
		try {
			bfimg = ImageIO.read(ClassLoader.getSystemResourceAsStream("img"+"/"+name+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bfimg;
	}
	
	public static BufferedImage getPngImgByName(String name){
		/**
		 * loading image in png format
		 */
		BufferedImage bfimg = null;
		try {
			bfimg = ImageIO.read(ClassLoader.getSystemResourceAsStream("img"+"/"+name+".png"));
		} catch (Exception e) {
			System.out.println("unexpected name:"+name);
			e.printStackTrace();
		}
		return bfimg;
	}
	
	
	public static ImageIcon getImageIcon(int GameType,int TeamType,int Player)
	{
		String direc="/img/Player/"+String.valueOf(GameType)+"/"+
				String.valueOf(TeamType)+"/"+String.valueOf(Player)+".png";

		
		ImageIcon result=new ImageIcon(background.class.getResource(direc));
		

		return result;
	}
}
