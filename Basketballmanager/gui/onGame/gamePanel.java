package Basketballmanager.gui.onGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Basketballmanager.client.Myclient;
import Basketballmanager.constant.Const_Game;
import Basketballmanager.constant.Const_UI;
import Basketballmanager.deprecated.Mychantting;
import Basketballmanager.util.JTextAreaAutoMove;

public class gamePanel extends JPanel{
	
	/**
	 * Class of main game panel
	 */
    public static Myclient gameThread=new Myclient();
    
    
    
    private playerPortait[] PlayerA=new  playerPortait[5]; 
    private playerPortait[] PlayerB=new  playerPortait[5]; 

    public static controlBar ControlBar=new controlBar();
    public static rotateBar RotateBar=new rotateBar();
    
    JScrollPane scroll;

	public JTextArea Center_boardcast=new JTextArea() ;
	    
	    
	public static JLabel ScoreA=new JLabel();
	public static JLabel ScoreB=new JLabel();

	public static JLabel TeamNameA=new JLabel();
	public static JLabel TeamNameB=new JLabel();
	public static JLabel TimeRemainning=new JLabel();
	
	/**
     * Variable list
     * gameThread: Object of client thread
     * PlayerA: List of portrait object in teamA
     * PlayerB: List of portrait object in teamB
     * ControlBar: Panel of control bar
     * rotateBar: Panel of rotateBar
     * scroll: Objects helping with scrolling of text panel
     * Center_boardcast: Center text panel
     * ScoreA: Panel showing current score of team A
     * ScoreB: Panel showing current score of team B
     * TeamNameA: Panel showing name of team A
     * TeamNameB: Panel showing name of team B
     * TimeRemainning: Panel showing remaining time of the game
     */
	
	
	
	public gamePanel()
	{
		this.setSize(Const_UI.FRAME_WIDTH,Const_UI.FRAME_HEIGHT);
		this.setLayout(null);
		
		
		for(int i=0;i<5;i++)
		{
			PlayerA[i]=new playerPortait();
			PlayerB[i]=new playerPortait();
		}
		
		PlayerA[0].setLocation(Const_UI.Player1A_X, Const_UI.Player1A_Y);
		PlayerA[1].setLocation(Const_UI.Player2A_X, Const_UI.Player2A_Y);
		PlayerA[2].setLocation(Const_UI.Player3A_X, Const_UI.Player3A_Y);
		PlayerA[3].setLocation(Const_UI.Player4A_X, Const_UI.Player4A_Y);
		PlayerA[4].setLocation(Const_UI.Player5A_X, Const_UI.Player5A_Y);
		PlayerB[0].setLocation(Const_UI.Player1B_X, Const_UI.Player1B_Y);
		PlayerB[1].setLocation(Const_UI.Player2B_X, Const_UI.Player2B_Y);
		PlayerB[2].setLocation(Const_UI.Player3B_X, Const_UI.Player3B_Y);
		PlayerB[3].setLocation(Const_UI.Player4B_X, Const_UI.Player4B_Y);
		PlayerB[4].setLocation(Const_UI.Player5B_X, Const_UI.Player5B_Y);
		
		 /* initialize center boardcast */
		 
		Center_boardcast.setSize(Const_UI.MIDDLE_WIDTH,Const_UI.MIDDLE_HEIGHT);
		Center_boardcast.setLocation(Const_UI.MIDDLE_X,Const_UI.MIDDLE_Y);
		Center_boardcast.setBackground(Color.GREEN);
		JTextAreaAutoMove.AutoMove(Center_boardcast);

		scroll=new JScrollPane(Center_boardcast);

		scroll.setSize(Const_UI.MIDDLE_WIDTH,Const_UI.MIDDLE_HEIGHT);
		scroll.setLocation(Const_UI.MIDDLE_X,Const_UI.MIDDLE_Y);
		Center_boardcast.setEditable(false);
		Center_boardcast.setLineWrap(true);
		/* add scroller */
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        
        
        ControlBar.setLocation(Const_UI.CONTROL_X, Const_UI.CONTROL_Y);
        RotateBar.setLocation(Const_UI.RotatePanel_X, Const_UI.RotatePanel_Y);

		
		ScoreA.setSize(Const_UI.SCOREBOARD_WIDTH,Const_UI.SCOREBOARD_HEIGHT);
		ScoreB.setSize(Const_UI.SCOREBOARD_WIDTH,Const_UI.SCOREBOARD_HEIGHT);
		ScoreA.setLocation(Const_UI.SCOREBOARD_A_X, Const_UI.SCOREBOARD_A_Y);
		ScoreB.setLocation(Const_UI.SCOREBOARD_B_X, Const_UI.SCOREBOARD_B_Y);

		ScoreA.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ScoreB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ScoreA.setHorizontalAlignment(JLabel.CENTER);
		ScoreB.setHorizontalAlignment(JLabel.CENTER);

		Center_boardcast.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		TeamNameA.setSize(Const_UI.NAMEBOARD_WIDTH,Const_UI.NAMEBOARD_HEIGHT);
		TeamNameB.setSize(Const_UI.NAMEBOARD_WIDTH,Const_UI.NAMEBOARD_HEIGHT);
		TeamNameA.setLocation(Const_UI.NAMEBOARD_A_X, Const_UI.NAMEBOARD_A_Y);
		TeamNameB.setLocation(Const_UI.NAMEBOARD_B_X, Const_UI.NAMEBOARD_B_Y);
		TimeRemainning.setSize(Const_UI.TIMEBOARD_WIDTH,Const_UI.TIMEBOARD_HEIGHT);
		TimeRemainning.setLocation(Const_UI.TIMEBOARD_X, Const_UI.TIMEBOARD_Y);

		TeamNameA.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TeamNameA.setHorizontalAlignment(JLabel.CENTER);
		TeamNameB.setFont(new Font("Times New Roman", Font.BOLD, 14));
		TeamNameB.setHorizontalAlignment(JLabel.CENTER);

		TimeRemainning.setHorizontalAlignment(JLabel.CENTER);
		TimeRemainning.setFont(new Font("Times New Roman", Font.BOLD, 14));

		ScoreA.setOpaque(true);
		ScoreB.setOpaque(true);
		TeamNameA.setOpaque(true);
		TeamNameB.setOpaque(true);
		TimeRemainning.setOpaque(true);

        
        
		for(int i=0;i<5;i++)
		{
			this.add(PlayerA[i]);
			this.add(PlayerB[i]);

		}
		
		this.add(RotateBar);
		this.add(ScoreA);
		this.add(ScoreB);
		this.add(TeamNameA);
		this.add(TeamNameB);
		this.add(TimeRemainning);

		this.add(scroll);
		this.add(ControlBar);
		this.setOpaque(false);
		
	}

	public void setImageByIcon(int index, Icon image,int teamNumber)
	{
		/**
		 * set image in Icon format according to index and teamNumber
		 * input:
		 * 		index(int): targeted player index
		 * 		image(Icon): image to be set
		 * 		teamNumber(int): targeted team index
		 * output:
		 */
		if(teamNumber==Const_Game.TeamAindex)
		{
			PlayerA[index].setImageByIcon(image);
		}
		else if(teamNumber==Const_Game.TeamBindex){
			PlayerB[index].setImageByIcon(image);
		}
		else{
			System.out.println("Error In GamePanel, setImage!\n");
		}
	}
	
	public void setImageByImageIcon(int index, ImageIcon image,int teamNumber)
	{
		/**
		 * set image in ImageIcon format according to index and teamNumber
		 * input:
		 * 		index(int): targeted player index
		 * 		image(ImageIcon): image to be set
		 * 		teamNumber(int): targeted team index
		 * output:
		 */
		if(teamNumber==Const_Game.TeamAindex)
		{
			PlayerA[index].setImageByImageIcon(image);
		}
		else if(teamNumber==Const_Game.TeamBindex){
			PlayerB[index].setImageByImageIcon(image);
		}
		else{
			System.out.println("Error In GamePanel, setImage!\n");
		}
	}
	
	
	public void setName(int index, String name,int teamNumber)
	{
		/**
		 * set name according to index and teamNumber
		 * input:
		 * 		index(int): targeted player index
		 * 		name(String): name of the player
		 * 		teamNumber(int): targeted team index
		 * output:
		 */
		if(teamNumber==Const_Game.TeamAindex)
		{
			PlayerA[index].setName(name);
		}
		else if(teamNumber==Const_Game.TeamBindex){
			PlayerB[index].setName(name);
		}
		else{
			System.out.println("Error In GamePanel, setName!\n");
		}
	}
}
