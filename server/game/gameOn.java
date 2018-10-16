package server.game;

import java.util.Random;

import server.Myserver;
import server.ServerThread;
import server.constant.constant;

public class gameOn extends Thread{
    /**
     * Class of game process thread, calculating what happends on court 
     */
	public  final int  gameType;
	public volatile player[][] Team= new player[2][constant.maxPlayer];
	public volatile int[][][] Coach=new int[2][6][];
	ServerThread serverForthis=null;
	private int BallRight=0;
	public int [][][] statistics=new int[2][constant.maxPlayer][constant.statisticNumber];
	private String[] TeamName=new String[2];
	
	private int OverTimeCounter=0;
	
	public int remainingTime=constant.MaxPlayTime;
	public int[] Score=new int[2];
	
    /**
     * Variable list
     * gameType: Index for the plot
     * player: List of players of two teams
     * Coach: List of int showing strategy ordered by two players
     * serverForthis: Server thread
     * BallRight: Indicating who is in control of the ball
     * statistics: Statistics collection for both teams
     * TeamName: Names of two teams
     * OverTimeCounter: Whether the game is currently in overtime
     * remainingTime: Time left for this game
     * Score: score for two teams
     */
	
	
	public gameOn(int type,ServerThread This)
	{
		/**
		 * Caculating what happens on court
		 */
		gameType=type;
		for(int i=0;i<2;i++)
		{
			TeamName[i]=Myserver.TeamName[gameType][i];
			for(int j=0;j<constant.maxPlayer;j++)
			{
				Team[i][j]=new player(Myserver.Plot[gameType][i][j]);//copy constructor
			}
		}
		
		for(int i=0;i<2;i++)
			for(int j=0;j<5;j++)
			{
				Team[i][j].SetCourt(true);
				Team[i][j].SetPosition(j);
			}
		
		/* initialization of coach's decision */
		for(int i=0;i<2;i++)
		{
			Coach[i][constant.organizeArrange]=new int[5];
			Coach[i][constant.scoreArrange]=new int[5];
			Coach[i][constant.scoreCreation]=new int[5];
			Coach[i][constant.defenseKey]=new int[5];
			Coach[i][constant.offenseStrategy]=new int[1];
			Coach[i][constant.defenseStrategy]=new int[1];
		}
		serverForthis=This;
		
		
		/* automatically change the property and condition of players */
		Random change=new Random();
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<constant.maxPlayer;j++)
			{
				for(int t=0;t<constant.attributeNumber;t++)
				{
					if(t==constant.indexPlayer || t==constant.strength ||t==constant.statusInTeam)/* omit this three properties */
						continue;
					int state=(change.nextInt(2)==1)?-1:1;
					int value=change.nextInt(9);
					Team[i][j].ChangeAttribute(t, value*state);
				}

			}
		}
	}
	public void run() {  

		
		
    	while(true)
    	{
    		String backwords=Caculate();/* calcute what happens */
    		while(true)
    		{
    			if(serverForthis.Sending==false)
    				break;
    			//break
    			try {
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		serverForthis.SendingWords=backwords;
    		serverForthis.TimeRemainning=remainingTime;
    		serverForthis.Score[0]=Score[0];
    		serverForthis.Score[1]=Score[1];
    		serverForthis.Sending=true;
    		System.out.println(serverForthis.Sending);
    		
    	}
    	
    }

	public synchronized void ChangeStrategy(int team,int decision[][])
	{
		/**
		 * Apply changed strategy 
		 * input: 
		 * 		team(int): which team changes the strategy
		 * 		decision(int[][]): how the player changes the decision 
		 * output:
		 */
		for(int i=0;i<4;i++)
		{
			
			for(int j=0;j<5;j++)
			{
				Coach[team][i][j]=decision[i][j];
			}

		}
		for(int i=0;i<2;i++)
		{
			
				Coach[team][i+4][0]=decision[i+4][0];
		}
	}
	
	
	
	public synchronized void ChangePlayer(int team,int index[])
	{
		/**
		 * Handle the rotation 
		 * input: 
		 * 		team(int): which team calls for rotation
		 * 		index(int[]): how players change rotation
		 * output:
		 */
		
		for(int i=0;i<constant.maxPlayer;i++)
		{
			Team[team][i].SetCourt(false);
		}
		for(int i=0;i<5;i++)
		{
			Team[team][index[i]].SetCourt(true);
			Team[team][index[i]].SetPosition(i);
		}
		
	}
	public synchronized String Caculate()
	{
		/**
		 * Calculate what happens on court
		 */
		String OutputLanguage=null;
		//calculate and updata the state
		
		int currentTime=0;
		/* matain the position of all players */
		int Position[][][]=new int[2][constant.maxPlayer][constant.PositionType];
		
		/* current ball-handler */
		int ballManIndex=-1;
		
		int[] chooseStart={0,0,0,0,0};
		
		int[][] TeamOncourttt=new int[2][5];
		/* on-court players of team A */
		TeamOncourttt[0]=ReturnOncourt(0);
		/* on-court players of team B */
		TeamOncourttt[1]=ReturnOncourt(1);

		
		for(int i=0;i<5;i++)
		{
			/* chooseStart[i]= tendency to call for ball + player status in team + tendency to run the offense */
			chooseStart[i]=Team[BallRight][TeamOncourttt[BallRight][i]].attribute[constant.desireForBall]
					+Team[BallRight][TeamOncourttt[BallRight][i]].attribute[constant.statusInTeam]
							+Team[BallRight][TeamOncourttt[BallRight][i]].attribute[constant.tendencyToDribble];
		}
		ballManIndex=maxInFive(chooseStart);
		
		
		Random any=new Random();
		
		
		while(true)
		{
			/* initialy start from serving a ball */
			
			int[] next={Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.tendencyToScore]
					+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.RimPositionIndex]
					+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.rimFinish],
					
					Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.tendencyToScore]
					+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.TwoPositionIndex]
					+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.twoPointerFinish],
					
					Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.tendencyToScore]
					+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.ThreePositionIndex]
					+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.threePointerFinish],
					
				Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.tendencyToPass]
						+(constant.maxTimeForOnePossession-currentTime)<<4,
				
				Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.tendencyToDribble]
						+(constant.maxTimeForOnePossession-currentTime)<<4
			};
			int nextDescision=maxInFive(next);

			if(nextDescision==0)
			{
				/* attack around the rim */
				if(Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.rimFinish]
						+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.RimPositionIndex]>any.nextInt(200))
				{
					/* score around the rim */
					Score[BallRight]+=2;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "+
							Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" attacks the rim and score";
					remainingTime-=currentTime;
					BallRight=(BallRight==0)?1:0;

					break;
				}
				else{
					/* fail to score around the rim */

					int other=(BallRight==0)?1:0;
					remainingTime-=currentTime;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "
							+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" fails to score around the rim\t"
							+TeamName[other]+" Team rebound";
					BallRight=other;
					break;
				}
			}
			
			else if(nextDescision==1)
			{
				/* shoot two point ball */
				if(Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.twoPointerFinish]
						+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.TwoPositionIndex]>any.nextInt(200))
				{
					/* score two points */
					Score[BallRight]+=2;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "+
							Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" scores two points with a jumper";
					remainingTime-=currentTime;
					BallRight=(BallRight==0)?1:0;

					break;
				}
				else{
					/* fail to score two points */

					int other=(BallRight==0)?1:0;
					remainingTime-=currentTime;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "
							+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" shoots the ball and misses\t"
							+TeamName[other]+" Team rebound";
					BallRight=other;
					break;
				}
				
				
			}
			else if(nextDescision==2)
				/* shoot a three- point ball */
			{
				if(Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].attribute[constant.threePointerFinish]
						+Position[BallRight][TeamOncourttt[BallRight][ballManIndex]][constant.ThreePositionIndex]>any.nextInt(200))
				{
					/* score three points */
					Score[BallRight]+=3;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "+
							Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" hits a three pointer";
					remainingTime-=currentTime;
					BallRight=(BallRight==0)?1:0;

					break;
				}
				else{
					/* fail to score */

					int other=(BallRight==0)?1:0;
					remainingTime-=currentTime;
					OutputLanguage=HeadString()+" "+TeamName[BallRight]+" "
							+Team[BallRight][TeamOncourttt[BallRight][ballManIndex]].getNamePlayer()+" misses a three pointer\t"
							+TeamName[other]+" Team rebound";
					BallRight=other;
					break;
				}
				
			}
			
			else if(nextDescision==3)
			{
				/* pass the ball */
				int temp;
				while(true)
				{
					temp=maxInFive(chooseStart);
					if(temp!=ballManIndex)
					{
						ballManIndex=temp;
						break;
					}
				}
			}
			/*
			 * pending, unfinished 
			if(true)// miss the ball, rebound
			{
				
			}
			
			
			if(true)// hit the ball
			{
				
			}
			
			
			if(true)/ fouled
			{
				
			}
			
			if(true)/ fouled and still hit
			{
				
			}
			*/

			currentTime+=(any.nextInt(2)+2);
			
			/* time up */
			if(currentTime>=remainingTime)
			{
				if(Score[0]>Score[1])
				{
				OutputLanguage=HeadString()+" "+"Game Over\t"+TeamName[0]+" wins";
				serverForthis.End=true;
				break;
				}
				else if(Score[0]<Score[1])
				{
					OutputLanguage=HeadString()+" "+"Game Over\t"+TeamName[1]+" wins";
					serverForthis.End=true;
					break;

				}
				else{
					if(OverTimeCounter==0)
						OutputLanguage=HeadString()+" "+"Regular time passed, step into Over Time";
					
					else
						OutputLanguage=HeadString()+" "+"Next overtime";
					
					OverTimeCounter++;
					/* enter overtime */
					remainingTime=constant.MaxPlayTime+OverTimeCounter*constant.ExtraTime;
					
				}
			}
			
			if(currentTime>=constant.maxTimeForOnePossession)
			{
				
				OutputLanguage=HeadString()+" "+TeamName[BallRight]+" 24 Seconds Violation!";
				remainingTime-=currentTime;
				BallRight=(BallRight==0)?1:0;
				break;
			}
		}
		
		
		
		
		
		
		
		return OutputLanguage;
	}
	private synchronized String HeadString()
	{	/* overtime unfinished */
		/**
		 * add header of string
		 */
		int a=remainingTime/720;
		switch(a)
		{
		case 0:
			return "Fourth Quarter: ";
		case 1:
			return "Third Quarter: ";
		case 2:
			return "Second Quarter: ";
		case 3:
			return "First Quarter: ";
		}
		a=(remainingTime-constant.MaxPlayTime)/constant.ExtraTime;/* which over time the game is in */
		return "Over Time "+String.valueOf(a+1)+" : ";
	}
	
	private synchronized int maxInFive(int[] attributeNumber)
	{
		int MaxIndex=0;
		int sum=0;
		for(int i=0;i<attributeNumber.length;i++)
			sum+=attributeNumber[i];

		int currentPointer=0;/* 0 to sum-1 */
		int nextPointer=0;
		Random PointerTobeChoose=new Random();
		int toBechoosen=PointerTobeChoose.nextInt(sum);

		for(int i=0;i<attributeNumber.length;i++)
		{
			nextPointer+=attributeNumber[i];
			if(toBechoosen>=currentPointer&& toBechoosen<nextPointer)
			{
				return i;
			}
			currentPointer+=attributeNumber[i];
		}
		System.out.println("Error maxFive");
		return 4;
	}

	private synchronized int[] ReturnOncourt(int teamIndex){
		/**
		 * return index of all five players
		 * input:
		 * 		teamIndex(int): the index of team
		 * output:
		 * 		int[]: index of all five players
		 */
		int[] result=new int[5];
		for(int i=0;i<constant.maxPlayer;i++)
		{
			if(Team[teamIndex][i].getCourt()==true)
				result[Team[teamIndex][i].getPosition()]=i;
		}
		return result;
	}
}
