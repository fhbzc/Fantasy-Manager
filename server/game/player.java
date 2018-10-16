package server.game;

import server.constant.constant;

public class player {
	/**
	 * Class of player information
	 */
	
	public final String name;
	public int[] attribute=new int[constant.attributeNumber];
	public int[] state=new int [constant.stateNumber]; 
	
	
	public volatile boolean onCourt=false;
	public volatile int currentPosition=0; 
    /**
     * Variable list
     * name: Name of the player
     * attribute: List of int storing attributes for this player
     * state: List of int storing state for this player
     * onCourt: boolean for whether this player is on court, False for no, Yes otherwise
     * currentPosition: current position of this player, invalid if player is not onCourt
     */
	player(String Name, int a[])
	{
		/**
		 * initialization of player 
		 * input:
		 * 		name(String): name of player
		 * 		a(int[]): value of attribute of players 	
		 */
		name=Name;
		for(int i=0;i<constant.attributeNumber;i++)
			attribute[i]=a[i];
		state[constant.energy]=attribute[constant.maxEnergy];
	}

	player(player source)
	{
		/**
		 * copy construction 
		 */
		name=source.name;
		for(int i=0;i<constant.attributeNumber;i++)
			attribute[i]=source.attribute[i];
		state[constant.energy]=attribute[constant.maxEnergy];
	}
	public void SetPosition(int position)
	{
		/**
		 * set the player to one position, must on court
		 * input:
		 * 		position(int): targeted position
		 * output:
		 */
		if(position<=0||position>5)
			return;
		currentPosition=position;
	}
	
	public String getNamePlayer()
	{
		/**
		 * get the name of this player
		 * input:
		 * output:
		 * 		String: name of the player
		 */
		return name;
	}
	
	public int getIndexPlayer()
	{
		/**
		 * get the index of this player
		 * input:
		 * output:
		 * 		int: index of the player
		 */
		return attribute[constant.indexPlayer];
	}
	
	public void SetCourt(boolean CourtCondition)
	{
		/**
		 * set whether this player is on court
		 * input:
		 * 		CourtCondition(boolean): set the targeted state of this player
		 * output:
		 * 		
		 */
		onCourt=CourtCondition;
	}
	
	public boolean getCourt()
	{
		/**
		 * get whether this player is on court
		 * input:
		 * output:
		 * 		boolean: get the targeted state of this player
		 */
		return onCourt;
	}
	public int getPosition()
	{
		/**
		 * get the position of this player
		 * input:
		 * output:
		 * 		int: current position of this player
		 */
		return currentPosition;
	}
	public void ChangeAttribute(int attributeIndex,int number)
	{
		/**
		 * set one attribute of this player
		 * input:
		 * 		attributeIndex(int): targeted attribute index
		 * 		number(int): value to be set
		 * output:
		 */
		attribute[attributeIndex]=(attribute[attributeIndex]+number>=0)?attribute[attributeIndex]+number:0;
	}
	
}
