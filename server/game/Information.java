package server.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import server.constant.constant;

public class Information {
	/**
	 * Class for loading data
	 * @param P
	 * @param TeamName
	 * @throws IOException
	 */
	public static void loadAll(player P[][][],String TeamName[][]) throws IOException
	{
		/**
		 * Load player and team information
		 */
		File directory = new File("");
		String current_directory=null;
		try {
			current_directory = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		for(int i=0;i<constant.type_number;i++)
		{
	        File file = new File(current_directory+"/src/file/"+String.valueOf(i)+".txt");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			for(int w=0;w<2;w++)/* read information for players in two teams */
			{
				TeamName[i][w]=br.readLine();//team name
				for(int j=0;j<constant.maxPlayer;j++)
				{
					String name=br.readLine();
					/* read name of players */
			        String str=null; 
					int[] a=new int[constant.attributeNumber];
					/* read attributes of players */
					for(int k=0;k<constant.attributeNumber;k++)
					{
						str=br.readLine();
						a[k]=Integer.parseInt(str);
					}
			        P[i][w][j]=new player(name,a);
				}
			}
		}
		
		
	}
	
    
}
