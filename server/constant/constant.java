package server.constant;

public class constant {
	/**
	 * Class of constant used in server thread
	 */
	public static final int type_number=1;
	public static final int maxPlayer=12;
	public static final int attributeNumber=20;
	public static final int stateNumber=20;
	
	
	public static final int TeamA=0;
	public static final int TeamB=1;

	/* offense strategy index */
	public static final int triangleOffense=0;
	public static final int pickAndRoll=1;
	public static final int centerPost=2;
	public static final int cutInOffense=3;
	public static final int PrincetonOffense=4;
	public static final int spurSystem=5;
	public static final int equalOffense=6;
	public static final int threeRain=7;
	public static final int singlePick=8;

	/* defense strategy index */
	public static final int jointDefense212=0;
	public static final int jointDefense23=1;
	public static final int jointDefense32=2;
	public static final int jointDefense131=3;
	public static final int changeInfinite=4;
	public static final int markingDefense=5;
	public static final int doubleTeam=6;
	public static final int expandOffense=7;
	public static final int shrinkOffense=8;
	public static final int allCourtChase=9;

	/* player index */
	public static final int indexPlayer=0;
	public static final int desireForBall=1;
	public static final int statusInTeam=2;
	public static final int threePointerPlace=3;
	public static final int twoPointerPlace=4;
	public static final int closeToRimPlace=5;
	public static final int basicFocus=6;
	public static final int maxEnergy=7;
	public static final int rimFinish=8;
	public static final int twoPointerFinish=9;
	public static final int threePointerFinish=10;
	public static final int freeThrow=11;
	public static final int tendencyToScore=12;
	public static final int tendencyToPass=13;
	public static final int tendencyToDribble=14;
	public static final int defenseForThree=15;
	public static final int defenseForTwo=16;
	public static final int rimProtection=17;
	public static final int defenseMobility=18;
	public static final int strength=19;
	
	/* player condition*/
	public static final int energy=0;
	public static final int focus=1;
	public static final int threePointerPosition=2;
	public static final int twoPointerPosition=3;
	public static final int rimPosition=4;
	public static final int defenseThreePointerPosition=5;
	public static final int defenseTwoPointerPosition=6;
	public static final int defenseRimPosition=7;
	
	/* player decision(part of strategy */
	public static final int organizeArrange=0;
	public static final int scoreArrange=1;
	public static final int scoreCreation=2;
	public static final int defenseKey=3;
	public static final int offenseStrategy=4;
	public static final int defenseStrategy=5;
	
	
	
	public static final int refreshRotation=0;
	public static final int refreshStrategy=1;
	public static final int Timout=2;

	/* statistic collection */
	public static final int statisticNumber=17;
	
	public static final int currentEnergyIndex=0;
	public static final int currentFoulIndex=1;
	public static final int currentFocusIndex=2;
	public static final int ScoreIndex=3;
	public static final int BoardFrontIndex=4;
	public static final int BoardBacktIndex=5;
	public static final int AssistIndex=6;
	public static final int TotalshootIndex=7;
	public static final int TotalInIndex=8;
	public static final int TotalThreeIndex=9;
	public static final int TotalThreeInIndex=10;
	public static final int TotalFoulShootIndex=11;
	public static final int TotalFoulShootInIndex=12;
	public static final int BlockIndex=13;
	public static final int StealIndex=14;
	public static final int TurnOverIndex=15;
	public static final int OnCourtTimeIndex=16;
	
	/* maximum time for single possession */
	public static final int maxTimeForOnePossession=24;

	public static final int PositionType=3;
	public static final int ThreePositionIndex=0;
	public static final int TwoPositionIndex=1;
	public static final int RimPositionIndex=2;
	
	/* sumed regular time */
	public static final int MaxPlayTime=2880;
	public static final int ExtraTime=300;

}