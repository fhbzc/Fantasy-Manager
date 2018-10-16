package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.constant.constant;
import server.game.Information;
import server.game.player;

public class Myserver {

	/**
	 * Class of server, accommodating user thread
	 */
	public static player[][][] Plot = new player[constant.type_number][2][constant.maxPlayer];
	public static String[][] TeamName = new String[constant.type_number][2];
	public static Socket[] Gamer = new Socket[constant.type_number]; /* contain waiting thread */
	public static ServerSocket chatServer=null;
	public static volatile int all_player = 0;
	public boolean[] Waiting = new boolean[constant.type_number];

    /**
     * Variable list
     * Plot: List of players for all plots supported
     * TeamName: List of team names for all plots supported
     * Gamer: List of sockets containing all players
     * chatServer: Server for chatting, not finished
     * Waiting: List of boolean telling whether there is someone waiting for connection
     */
	
	
	public static void main(String[] args) {
		Myserver Server = new Myserver();
		Server.initServer();
	}

	Myserver() {
		try {
			Information.loadAll(Plot, TeamName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Myserver");
		}
	}

	private void initServer() {
		while (true) {
			for(int i=0;i<constant.type_number;i++)
			{
				Waiting[i]=false;
			}
			
			
			try {
				/* creat server thread, and assign a port */
				ServerSocket server = new ServerSocket(9090);
				chatServer=new ServerSocket(9000);
				System.out.println("Server Created......");
				/* fetching the request from client thread */
				while (true) {
					Socket temp = server.accept();
					System.out.println("Connection created......");
					InputStream ins = temp.getInputStream(); /* fetching input stream */
					OutputStream ous = temp.getOutputStream(); /* fetching output stream */
					int type = Integer.parseInt(ServerThread.readMsg(ins)); /* fetching game index */
																			
					System.out.println(type);
					if (type < 0 || type >= constant.type_number) {
						ServerThread.sendMsg(ous, "9999");/* Error! */
					} else if (Waiting[type] == false)/* put to wait */
					{
						ServerThread.sendMsg(ous, "0");
						Gamer[type] = temp;
						Waiting[type] = true;
					} else {
						Waiting[type] = false;
						all_player += 2;
						ServerThread st = new ServerThread(Gamer[type], temp, type);
						st.start();
					}
					/* add this player thread to server */
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}