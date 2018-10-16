package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import server.constant.constant;
import server.game.gameOn;

public class ServerThread extends Thread {

	/**
	 * Class of server thread, handle the request from client and monitor the game
	 */
	public Socket gamer1;
	public Socket gamer2;

	public InputStream ins1;
	public OutputStream ous1;
	public InputStream ins2;
	public OutputStream ous2;
	public gameOn GAME;
	public volatile boolean End = false;
	public volatile boolean RotateAllowed = false;
	public volatile boolean Sending = false;

	public volatile String SendingWords;
	public volatile int Score[] = new int[2];
	public volatile int TimeRemainning;

	private int[][] Decision = new int[6][];

	
    /**
     * Variable list
     * gamer1: Socket connected as player 1
     * gamer2: Socket connected as player 2
     * ins1: Input stream of player 1
     * ous1: Output stream of player 1
     * ins2: Input stream of player 2
     * ous2: Output stream of player 2
     * GAME: Modelling of game process
     * End: Indicating whether the game is ended, False for no, Yes otherwise
     * RotateAllowed: Indicating whether a rotation is allowed, False for no, Yes otherwise
     * Sending: Set to True after necessary preparation
     * SendingWords: Send words to client thread
     * Score: List of int storing scores of two teams
     * Decision: List of int storing strategy decision by players
     */
	
	
	
	public ServerThread(Socket socket, Socket socket2, int type) throws IOException {
		this.gamer1 = socket;
		this.gamer2 = socket2;
		ins1 = socket.getInputStream();
		ous1 = socket.getOutputStream();
		ins2 = socket2.getInputStream();
		ous2 = socket2.getOutputStream();
		GAME = new gameOn(type, this);
	}

	public void run() {

		/* to store player's decision */
		for (int i = 0; i < 4; i++) {
			Decision[i] = new int[5];
		}
		for (int i = 0; i < 2; i++) {
			Decision[i + 4] = new int[1];
		}

		try {
			
			sendMsg(ous1, "1");/* send to client 1 for successful connection */
			sendMsg(ous2, "1");/* send to client 2 for successful connection */

			
			sendMsg(ous1, "0");/* client one for first team */
			sendMsg(ous2, "1");/* client one for second team */

			
			/* send team name */
			sendMsg(ous1, Myserver.TeamName[GAME.gameType][0]); 
			sendMsg(ous1, Myserver.TeamName[GAME.gameType][1]); 

			sendMsg(ous2, Myserver.TeamName[GAME.gameType][1]); 
			sendMsg(ous2, Myserver.TeamName[GAME.gameType][0]); 

			/* send player name and index */
			for (int i = 0; i < constant.maxPlayer; i++)
			{
				sendMsg(ous1, GAME.Team[constant.TeamA][i].getNamePlayer());
				sendMsg(ous1, String.valueOf(GAME.Team[constant.TeamA][i].getIndexPlayer()));
			}
			for (int i = 0; i < constant.maxPlayer; i++)
			{
				sendMsg(ous2, GAME.Team[constant.TeamB][i].getNamePlayer());
				sendMsg(ous2, String.valueOf(GAME.Team[constant.TeamB][i].getIndexPlayer()));
			}
			for (int i = 0; i < 5; i++)
			{
				sendMsg(ous1, GAME.Team[constant.TeamB][i].getNamePlayer());
				sendMsg(ous1, String.valueOf(GAME.Team[constant.TeamB][i].getIndexPlayer()));
			}
			for (int i = 0; i < 5; i++)
			{
				sendMsg(ous2, GAME.Team[constant.TeamA][i].getNamePlayer());
				sendMsg(ous2, String.valueOf(GAME.Team[constant.TeamA][i].getIndexPlayer()));
			}
			
			refreshStrategy();
			System.out.println("PrePareing..");

			GAME.start();
			System.out.println("Game begin");
			while (true) {
				if (End == true) {/* end of game */
					sendMsg(ous1, "5555");
					sendMsg(ous2, "5555");
					sendMsg(ous1, "Game over");
					sendMsg(ous2, "Game over");
					sendMsg(ous1, SendingWords);
					sendMsg(ous2, SendingWords);
					System.out.println("End of Game");
					break;
				}

				if (Sending == true) {
					/* common possession */
					
					sendMsg(ous1, "8888");
					sendMsg(ous2, "8888");
					
					sendMsg(ous1, SendingWords);
					sendMsg(ous2, SendingWords);

					sendMsg(ous1, String.valueOf(Score[constant.TeamA]));
					sendMsg(ous1, String.valueOf(Score[constant.TeamB]));

					sendMsg(ous2, String.valueOf(Score[constant.TeamA]));
					sendMsg(ous2, String.valueOf(Score[constant.TeamB]));

					sendMsg(ous1, String.valueOf(TimeRemainning));
					sendMsg(ous2, String.valueOf(TimeRemainning));

					Sending = false;
				} else {
					/* default */
					sendMsg(ous1, "9999");
					sendMsg(ous2, "9999");
				}
				String Read = readMsg(ins1);
				/* rotation or timeout */
				if (Read.equals("9999") == false) {
					/* action initialized by client 1 */
					if (Read.equals("4444"))
					{
						sendMsg(ous2, "4444");/* abnormal exit */
						break;

					} else if (Read.equals("6666")) {/* timeout */
						sendMsg(ous1, "6666");
						sendMsg(ous2, "6666");
						sendMsg(ous1, Myserver.TeamName[GAME.gameType][0] + " calls for timeout");
						sendMsg(ous2, Myserver.TeamName[GAME.gameType][0] + " calls for timeout");
						Timeout();
						continue;
					} else if (Read.equals("5555"))/* rotate */
					{
						refreshChangePlayer(constant.TeamA);
					}
				}
				Read = readMsg(ins2);
				if (Read.equals("9999") == false) {
					/* action initialized by client 2 */
					if (Read.equals("4444"))
					{
						sendMsg(ous1, "4444");
						break;

					} else if (Read.equals("6666")) {/* timeout */
						sendMsg(ous1, "6666");
						sendMsg(ous2, "6666");
						sendMsg(ous1, Myserver.TeamName[GAME.gameType][1] + " calls for timeout");
						sendMsg(ous2, Myserver.TeamName[GAME.gameType][1] + " calls for timeout");
						Timeout();
					} else if (Read.equals("5555"))/* rotate */
					{
						refreshChangePlayer(constant.TeamB);
					}
				}
				
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				ins1.close();
				ous1.close();
				ins2.close();
				ous2.close();
				gamer1.close();
				gamer2.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private void refreshStrategy() {
		/**
		 * refresh strategy ordered by two players 
		 */
		try {
			for (int i = 0; i < 4; i++)/* client one */
				for (int j = 0; j < 5; j++)
					Decision[i][j] = Integer.parseInt(readMsg(ins1));
			
			for (int i = 0; i < 2; i++) {
				Decision[i + 4][0] = Integer.parseInt(readMsg(ins1));
			}

			GAME.ChangeStrategy(constant.TeamA, Decision);
			for (int i = 0; i < 4; i++)/* client two */
				for (int j = 0; j < 5; j++)
					Decision[i][j] = Integer.parseInt(readMsg(ins2));

			
			for (int i = 0; i < 2; i++) {
				Decision[i + 4][0] = Integer.parseInt(readMsg(ins2));
			}

			GAME.ChangeStrategy(constant.TeamB, Decision);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void Timeout() {
		/**
		 * response to time out
		 */
		refreshStrategy();/* refresh latest strategy setting */

		String timeoutEndA = null; 
		String timeoutEndB = null;
		try {
			timeoutEndA = readMsg(ins1);
			timeoutEndB = readMsg(ins2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (timeoutEndA.equals("7777"))/* timeout with rotation initialized by A*/
		{
			refreshChangePlayer(constant.TeamA);
		}
		if (timeoutEndB.equals("7777"))/* timeout with rotation initialized by B*/
		{
			refreshChangePlayer(constant.TeamB);
		}

		try {
			sendMsg(ous1, "9999");
			sendMsg(ous2, "9999");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void refreshChangePlayer(int INDEX) {
		/**
		 * response to rotation 
		 */
		InputStream in = (INDEX == constant.TeamA) ? ins1 : ins2;
		String Name = Myserver.TeamName[GAME.gameType][INDEX];

		int[] index = new int[5];

		try {

			sendMsg(ous1, "7777");
			sendMsg(ous2, "7777");

			sendMsg(ous1, Name + " calls for player change");
			sendMsg(ous2, Name + " calls for player change");

			
			sendMsg(ous1, String.valueOf(INDEX));
			sendMsg(ous2, String.valueOf(INDEX));

			for (int i = 0; i < 5; i++) {
				index[i] = Integer.parseInt(readMsg(in));
			}

			GAME.ChangePlayer(INDEX, index);

			for (int i = 0; i < 5; i++) {
				/* transfer the value of 5 slots to client thread */
				/* format name + index */
				sendMsg(ous1, GAME.Team[INDEX][index[i]].getNamePlayer());
				sendMsg(ous1, String.valueOf(index[i]));
				sendMsg(ous2, GAME.Team[INDEX][index[i]].getNamePlayer());
				sendMsg(ous2, String.valueOf(index[i]));
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			/* end of rotation */
			sendMsg(ous1, "Player change over");
			sendMsg(ous2, "Player change over");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static String readMsg(InputStream ins) throws Exception {
		
		int value = ins.read();

		
		String str = "";
		while (value != 10) {
			
			if (value == -1) {
				throw new Exception();
			}
			str = str + ((char) value);
			value = ins.read();
		}
		str = str.trim();
		return str;
	}

	
	public static void sendMsg(OutputStream os, String s) throws IOException {
		
		byte[] bytes = s.getBytes();
		os.write(bytes);
		os.write(10);
		os.flush();

	}

}