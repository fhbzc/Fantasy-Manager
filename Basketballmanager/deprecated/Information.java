package Basketballmanager.deprecated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * All files in this package are obsolete or corrupted 
 * they maybe recycled or reused some day
 * @author Fang Hongbo
 *
 */
public class Information {
	/**
	 * load local file
	 */
	//load game data into system
	public static String current_directory;
	public static void loadAll()
	{
		File directory = new File("");//参数为空
		try {
			current_directory = directory.getCanonicalPath() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Load_Crazy();
		Load_Dynasty();

	}
	
	
    private static void Load_Dynasty(){
    	//load dynasty module
        File file = new File(current_directory+"/src/information/Dynasty/1.txt");
        
    }
    
    private static void Load_Crazy(){
    	//load crazy module
        System.out.println("This module is deprecated, Don't use");
    	File file = new File(current_directory+"/src/information/Crazy/1.txt");
        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        String str=null; 
        try {
			while((str=br.readLine())!=null) 
			{ 
			int a=Integer.parseInt(str);
		//	 System.out.println(a);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        
    }
    /**
     * 返回对应的队名
     * @param GameType
     * @param TeamType
     * @return
     */
    public static String Load_Crazy(int GameType,int TeamType)
    {
        File file = new File(current_directory+"/src/information/Dynasty/"+String.valueOf(GameType)+
        		"/"+String.valueOf(TeamType)+".txt");
        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        String str=null; 
        try {
			str=br.readLine();
			return str;
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

    }
    
}
