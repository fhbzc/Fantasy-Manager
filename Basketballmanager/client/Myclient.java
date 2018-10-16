package Basketballmanager.client;

import java.awt.Toolkit;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

import Basketballmanager.constant.Const_Game;
import Basketballmanager.gui.gamechoose.background;
import Basketballmanager.gui.onGame.controlPanelDefense;
import Basketballmanager.gui.onGame.controlPanelOffense;
import Basketballmanager.gui.onGame.gamePanel;
import Basketballmanager.util.ImgUtil;

public class Myclient extends Thread {
	/**
	 * client thread class
	 * exchange information between server and client
	 * 
	 */
	private int GameType;
	private boolean ExceptionEnd = false;
	private volatile boolean ReadyChange = false;

	private int TeamType;
	public static Socket client = null;
	public static int[] PlayerIndexMe = new int[Const_Game.maxPlayer];
	public static int[] PlayerIndexThem = new int[5];
	public static volatile boolean RotatePlayerThis = false;
	public static volatile boolean TimeoutThis = false;
	public static String[] NameList=new String[Const_Game.maxPlayer];
	public static ImageIcon[][] PlayerPicture = new ImageIcon[2][Const_Game.maxPlayer];
																						
    private int TimeoutNumber=5;

    /**
     * Variable list
     * GameType: Index showing which plot it is in
     * ExceptionEnd: Detecting possible glitches in data transferring, False for no error, True otherwise
     * ReadyChange: Panel Lock, whether to permit the modification of user panel, False for no, True otherwise
     * TeamType: Index showing which team the current player is representing in the plot
     * client: Socket connecting with server
     * PlayerIndexMe: List of int showing the rotation of current player
     * PlayerIndexThem: List of int showing the rotation of rival player
     * RotatePlayerThis: Indicating whether there is a rotating call, False for no, True otherwise
     * TimeoutThis: Indicating whether there is a timeout call, False for no, True otherwise
     * NameList: List of strings showing the name of players in this client's team
     * ImageIcon: List of images showing the pictures of players in this client's team
     */
    
    
	public void setType(int type) {
		/**
		 * Set GameType for current client
		 * input: 
		 *      type(int): the value of current game type
		 * output:
		 */
		GameType = type;
	}

	public void setReadyChange(boolean Change) {
		/**
		 * Set setReadyChange for current client
		 * input: 
		 *      Change(boolean): the value of intended setReadyChange
		 * output:
		 */
		ReadyChange = Change;
	}

	public void run() {
		/**
		 * start a thread automatically exchange information with server
		 * input: 
		 * output:
		 */
		
		BackToInitialization(); /* initialization of thread */

		try {

			client = new Socket("127.0.0.1", 9090);
			System.out.println("Connected");
			/* get the input and output stream from server */
			final InputStream ins = client.getInputStream(); 
			final OutputStream ous = client.getOutputStream();
				
			sendMsg(ous, String.valueOf(GameType)); /* send current game type(index) */
			while (true) {
				String read = readMsg(ins);
				
				if (read .equals("9999") ) {
					/* unexpected response */
					ExceptionEnd = true;
					break;
				} else if (read.equals("1") )
					/* connect successfully */
					break;
				Thread.sleep(100);
			}
			if (ExceptionEnd == true) { /* spotting glitch */
				BackToInitialization();
				return;
			}
			

			while (true) {
				/* change of client panel is only possible as long as ReadyChange is true */
				if (ReadyChange == true)
					break;
				Thread.sleep(100);
			}

			/* initialize client panel */
			background.processBar.setVisible(false);
			
			/*  load local image */
			background.image = Toolkit.getDefaultToolkit()
					.createImage(background.class.getResource("/img/Game2015.png"));
			background.GameMain.setVisible(true);

			
			TeamType = Integer.parseInt(readMsg(ins));

			gamePanel.TeamNameA.setText(readMsg(ins));
			gamePanel.TeamNameB.setText(readMsg(ins));

			/* load team information */
			if (TeamType == Const_Game.TeamAindex) /* if current client is team A */ 
			{
				for (int t = 0; t < 2; t++) {// Á½¸ö¶ÓÎé
					for (int i = 0; i < Const_Game.maxPlayer; i++) {
						PlayerPicture[t][i] = ImgUtil.getImageIcon(GameType, t, i);
					}
				}
			} else { /* if current client is team B */ 

				for (int i = 0; i < Const_Game.maxPlayer; i++) {
					PlayerPicture[0][i] = ImgUtil.getImageIcon(GameType, 1, i);
																				
				}
				for (int i = 0; i < Const_Game.maxPlayer; i++) {
					PlayerPicture[1][i] = ImgUtil.getImageIcon(GameType, 0, i);
																				
				}
			}

			/* receive player information */
			

			for (int i = 0; i < Const_Game.maxPlayer; i++) {
				if (i < 5) {
					NameList[i]=readMsg(ins);
					background.GameMain.setName(i, NameList[i], 0);
					PlayerIndexMe[i] = Integer.parseInt(readMsg(ins)); /* receive player index */
					background.GameMain.setImageByImageIcon(i, PlayerPicture[0][PlayerIndexMe[i]],
							Const_Game.TeamAindex);
				} else {
					NameList[i]=readMsg(ins);
					gamePanel.ControlBar.PlayerDetail.rotation[i - 5].setName(NameList[i]);
					PlayerIndexMe[i] = Integer.parseInt(readMsg(ins)); /* receive player index */
					gamePanel.ControlBar.PlayerDetail.rotation[i - 5]
							.setImageByImageIcon(PlayerPicture[0][PlayerIndexMe[i]]);
				}

			}

			for (int i = 0; i < 5; i++) {
				background.GameMain.setName(i, readMsg(ins), 1);
				PlayerIndexThem[i] = Integer.parseInt(readMsg(ins));
				background.GameMain.setImageByImageIcon(i, PlayerPicture[1][PlayerIndexThem[i]], Const_Game.TeamBindex);
			}
			SendStre(ous);
			while (true) {
				/* loop for information exchange */
				String receiver = readMsg(ins);
				switch (receiver) {
				case "5555":
					background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");/* Game Over */
					background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");/* Final result */
					return;
				case "4444":
					System.out.println("Unexpected Endning");
					break;
				case "8888":/* basic information: describing the content of current possession */
					background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");/* display */
					if (TeamType == Const_Game.TeamAindex) {
						/* if current player represents team A */
						gamePanel.ScoreA.setText(readMsg(ins));
						gamePanel.ScoreB.setText(readMsg(ins));
					} else {
						gamePanel.ScoreB.setText(readMsg(ins));
						gamePanel.ScoreA.setText(readMsg(ins));
					}
					SetTimeRemaining(Integer.parseInt(readMsg(ins)));
					break;
				case "6666":
					/* timeout detected */
					background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");/* display */
					
					
					Thread.sleep(10000);
					background.GameMain.Center_boardcast.append("Time out over\n");

					/* send offense and defense strategy */ 
					SendStre(ous);
					if (RotatePlayerThis == true) {
						sendMsg(ous, "7777");/* timeout with rotating */
						for (int i = 0; i < 5; i++)
							sendMsg(ous, String.valueOf(PlayerIndexMe[i]));		
						RotatePlayerThis = false;
					} else
						sendMsg(ous, "8888");/* timeout without rotating */

					while (readMsg(ins).equals( "9999")==false) {
						AnswerToChangePlayer(ins);
					}
					background.GameMain.Center_boardcast.append("The game is on\n");
					break;
				case "7777":
					/* rotating detected */
					AnswerToChangePlayer(ins);
					break;
				}
				/* sending signals to server */

				if (TimeoutThis == true) { /* call timeout */
					if(TimeoutNumber>0)
					{
					TimeoutThis = false;
					sendMsg(ous, "6666");
					TimeoutNumber--;
					gamePanel.ControlBar.BarChoose.TimeOutShort.setText("Timeout"+"("+String.valueOf(TimeoutNumber)+")");/* adjust the content of panel */
					if(TimeoutNumber<=0)
						gamePanel.ControlBar.BarChoose.TimeOutShort.setEnabled(false);
					}
					else{
						System.out.println("No timeout left");
					}
				} else if (RotatePlayerThis == true) {
					/* call for player rotation */
					sendMsg(ous, "5555");
					for (int i = 0; i < 5; i++)
						sendMsg(ous, String.valueOf(PlayerIndexMe[i]));
					RotatePlayerThis = false;
				} else
					sendMsg(ous, "9999");/* default */

				
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		BackToInitialization();
	}

	public String readMsg(InputStream ins) throws Exception {
		/**
		 * Reading message from input stream
		 * input:
		 * 		ins(InputStream): input stream
		 * output:
		 * 		String: content of message
		 */
		int value = ins.read();
		String str = "";
		while (value != 10) {
			/* unexpected ending of client */
			if (value == -1) {
				throw new Exception();
			}
			str = str + (char) value;
			value = ins.read();
		}
		str = str.trim();
		return str;
	}

	
	public void sendMsg(OutputStream ous, String str) throws Exception {
		/**
		 * Sending message through output stream
		 * input:
		 * 		ous(OutputStream): output stream
		 * 		str(String): content sending to server
		 * output:
		 * 		
		 */
		byte[] bytes = str.getBytes();
		ous.write(bytes);
		ous.write(10);
		ous.flush();
	}

	private void BackToInitialization() {
		/**
		 * reset the thread to initial condition
		 */
		
		
		
		ExceptionEnd = false;
		ReadyChange = false;/* reset */
		gamePanel.ScoreB.setText("0");
		gamePanel.ScoreA.setText("0");
		SetTimeRemaining(Const_Game.MaxPlayTime);

	}


	private void SendStre(OutputStream ous) {

		/**
		 * Sending strategy to server, including 10 numbers for offense, 10 numbers for defense and 2 numbers for the specific tactic
		 * input:
		 * 		ous(OutputStream): output stream
		 * output:		
		 */		
		
		
		int[] a = controlPanelOffense.organize.getStre();
		for (int i = 0; i < 5; i++) {
			try {
				sendMsg(ous, String.valueOf(a[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a = controlPanelOffense.scoreArr.getStre();
		for (int i = 0; i < 5; i++) {
			try {
				sendMsg(ous, String.valueOf(a[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a = controlPanelOffense.attack.getStre();
		for (int i = 0; i < 5; i++) {
			try {
				sendMsg(ous, String.valueOf(a[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a = controlPanelDefense.organize.getStre();
		for (int i = 0; i < 5; i++) {
			try {
				sendMsg(ous, String.valueOf(a[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sendMsg(ous, String.valueOf(controlPanelOffense.Offense.getChoose()));/* send offense strategy */
			sendMsg(ous, String.valueOf(controlPanelDefense.defense.getChoose()));/* send defense strategy */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void AnswerToChangePlayer(InputStream ins) {
		/**
		 * Response to possible player shuffle 
		 * input:
		 * 		ins(InputStream): input stream
		 * output:		
		 */		
		try {
			background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");

			int teamCallingChange = Integer.parseInt(readMsg(ins));
			if (teamCallingChange == TeamType)/* this client calling for player rotation itself */
			{
				for (int i = 0; i < 5; i++) {
					background.GameMain.setName(i, readMsg(ins), 0);
					PlayerIndexMe[i] = Integer.parseInt(readMsg(ins));
					background.GameMain.setImageByImageIcon(i, PlayerPicture[0][PlayerIndexMe[i]],
							Const_Game.TeamAindex);/* set the image */
				}
				
				for(int i=0;i<Const_Game.maxPlayer-5;i++)
				{
					gamePanel.ControlBar.PlayerDetail.rotation[i].setName(NameList[PlayerIndexMe[i+5]]);
					gamePanel.ControlBar.PlayerDetail.rotation[i]
							.setImageByImageIcon(PlayerPicture[0][PlayerIndexMe[i+5]]);
				}
				
				
			} else {/* rival client calling for player rotation */
				for (int i = 0; i < 5; i++) {
					background.GameMain.setName(i, readMsg(ins), 1);
					PlayerIndexThem[i] = Integer.parseInt(readMsg(ins));
					background.GameMain.setImageByImageIcon(i, PlayerPicture[1][PlayerIndexThem[i]],
							Const_Game.TeamBindex);/* set the image */
				}
			}

			background.GameMain.Center_boardcast.append(readMsg(ins) + "\n");/* end of roattion */
		} catch (Exception e) {
			/* exception */
			System.out.println("AnswerToChangePlayer Error");
			e.printStackTrace();

		}
	}
	
	private String GetInput()
	{
		/**
		 * String modification, called innerly
		 * input:
		 * output:
		 */
		String a=null;
		a = gamePanel.ControlBar.ChatPanel.Input.getText();
		if (a.contains("\n") == true) {// ends with \n
			gamePanel.ControlBar.ChatPanel.Input.setText("");
		}
		else{
			a=null;
		}
		return a;
	}
	
	
	private void SetTimeRemaining(int time)
	{
		/**
		 * Set remaining time for this game in the client panel
		 * input:
		 * 		time(int): remaining time with the unit of second
		 * output:
		 */
		if(time>Const_Game.MaxPlayTime)
		{
			/* OT */
			int extra=time-Const_Game.MaxPlayTime;
			int T1=extra/Const_Game.ExtraTime;
			int T2=extra%Const_Game.ExtraTime;
			int T_m=T2/60;
			int T_s=T2%60;
			String second=(T_s>=10)?String.valueOf(T_s):"0"+String.valueOf(T_s);
			gamePanel.TimeRemainning.setText("OverTime"+String.valueOf(T1+1)+" "+"0"+String.valueOf(T_m)+":"+second);
			return;
		}
		if(time==Const_Game.MaxPlayTime)
		{
			gamePanel.TimeRemainning.setText("Quater one 12:00");
			return;
		}
		int past_time=Const_Game.MaxPlayTime-time;/* past time */
		
			int a=past_time/720;
			int b=time%720;
			int minutes=b/60;
			int seconds=b%60;
			String Toset=null;
			switch(a){
			case 0:
				Toset="Quater one ";
				break;
			case 1:
				Toset="Quater two ";
				break;
			case 2:
				Toset="Quater three";
				break;
			case 3:
				Toset="Quater four ";
				break;
			}
			if(minutes>=10)
				Toset+=String.valueOf(minutes);
			else
				Toset+="0"+String.valueOf(minutes);/* remaining minutes */
			Toset+=":";
			if(seconds>=10)
				Toset+=String.valueOf(seconds);
			else
				Toset+="0"+String.valueOf(seconds);/* remaining seconds */
			gamePanel.TimeRemainning.setText(Toset);
		
	}
	
}