package Basketballmanager.deprecated;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Basketballmanager.gui.onGame.gamePanel;
/**
 * All files in this package are obsolete or corrupted 
 * they maybe recycled or reused some day
 * @author Fang Hongbo
 *
 */
public class Mychantting extends Thread{
	
	Socket client=null;
	public Mychantting(){
		
		try {
			client = new Socket("192.168.229.1", 9000);
			System.out.println("Connect");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run() {
		String a = null;
		
		InputStream ins=null;
		OutputStream ous=null;
		try {
			ins = client.getInputStream();
			ous = client.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
		{
			a = gamePanel.ControlBar.ChatPanel.Input.getText();
			if (a.contains("\n") == true) {// ends with \n
				gamePanel.ControlBar.ChatPanel.Input.setText("");
				//gamePanel.ControlBar.ChatPanel.Output.append(a);
				try {
					sendMsg(ous,a);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a=null;
			}
			else{
				try {
					sendMsg(ous,"");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				a=readMsg(ins);
				if(a.equals("")==false)
				{
					gamePanel.ControlBar.ChatPanel.Output.append(a);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	
	public String readMsg(InputStream ins) throws Exception {
		int value = ins.read();
		String str = "";
		while (value != 10) {
			// 代表客户端不正常关闭
			if (value == -1) {
				throw new Exception();
			}
			str = str + (char) value;
			value = ins.read();
		}
		str = str.trim();
		return str;
	}

	// 发送消息的函数
	public void sendMsg(OutputStream ous, String str) throws Exception {
		byte[] bytes = str.getBytes();
		ous.write(bytes);
		ous.write(10);
		ous.flush();
	}
}
