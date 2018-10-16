package Basketballmanager.deprecated;
/**
 * All files in this package are obsolete or corrupted 
 * they maybe recycled or reused some day
 * @author Fang Hongbo
 *
 */
public class player {
	//attribute measure the condition of player
	public final String name;
	public final static int list_number=14;
	public int attribute[];
	
	
	//dynamic information
	public boolean onCourt=false;//false for on court, true for on court
	public int energy;
	public int currentPosition=0; //no position
	player(String Name, int a[])
	{
		//initial player information
		name=Name;
		attribute=new int[list_number];
		for(int i=0;i<list_number;i++)
			attribute[i]=a[i];
	}
}
