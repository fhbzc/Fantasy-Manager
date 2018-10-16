package Basketballmanager.constant;

public class Const_Game {
	/**
	 * Definition of In-Game constant
	 */
	public static final int maxPlayer=12;
	
	public static final int Dynasty_2015_final=0;
	public static final int MaxPlayTime=2880;
	
    public static final int TeamAindex=0;
    public static final int TeamBindex=1;
	
	public static final int PG=1;
	public static final int SG=2;
	public static final int SF=3;
	public static final int PF=4;
	public static final int C=5;
	
	//public static final int speed_index=0;

	public static final int ExtraTime=300;
	

	
	
	/* name of offense strategy */
	public static final String[] Offst={
			"Triangle offense",
			"Roll attack",
			"Center posting",
			"Cut and run",
			"Princeton",
			"Spurs system",
			"Uniform attack",
			"Three-bombarding",
			"Singles tactics"
	};
	
	public static final String[] Defst={
			"2-1-2 joint defense",
			"2-3 joint defense",
			"3-2 joint defense",
			"1-3-1 joint defense",
			"Infinite replacement",
			"Marking tactics",
			"Cladding tactics",
			"Expanding defense",
			"Protecting restricted areas",
			"Full court press"
	};	
	
    /**
     * Variable list
     * maxPlayer: The number of max_player
     * Dynasty_2015_final: Index for plot "Dynasty_2015_final"
     * MaxPlayTime: Total amount of time in a regular game, with the units of seconds 
     * TeamAindex: Index for teamA
     * TeamBindex: Index for teamB
     * PG: Index for Point Guard
     * SG: Index for Shooting Guard
     * SF: Index for Small Forward
     * PF: Index for Point Guard
     * C: Index for Center
     * ExtraTime: Extra time for one overtime, with the units of seconds
     * Offst: List of offense strategy
     * Defst: List of defense strategy
     */
}
