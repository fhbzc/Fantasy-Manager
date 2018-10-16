package Basketballmanager.constant;


public class Const_UI {
	/**
	 * Definition of constant relating to the layout of game panel
	 */
	
	//窗体的宽度和高度以及坐标
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 700;
	
	//代表选择游戏模式的宽高
	public static final int MODE_WIDTH = 250;
	public static final int MODE_HEIGHT = 550;

	//选择游戏模式面板的位置
	public static final int MODE_X = 50;
	public static final int MODE_Y = 50;
	
	//信息面板的宽高
	public static final int TEXTLABEL_WIDTH = 500;
	public static final int TEXTLABEL_HEIGHT = 550;	
	
	//信息面板的位置
	public static final int TEXTLABEL_X = 400;
	public static final int TEXTLABEL_Y = 50;	
	
	
	//进度条的宽高
	public static final int PROGRESS_WIDTH=600;
	public static final int PROGRESS_HEIGHT=50;

	//进度条的位置
	public static final int PROGRESS_X=200;
	public static final int PROGRESS_Y=550;
	
	//球员信息长宽
	public static final int PLAYER_WIDTH=80;
	public static final int PLAYER_HEIGHT=70;

	//球员位置 A
	public static final int Player1A_X=80;
	public static final int Player1A_Y=70;
	
	public static final int Player2A_X=80;
	public static final int Player2A_Y=160;
	
	public static final int Player3A_X=80;
	public static final int Player3A_Y=250;
	
	public static final int Player4A_X=80;
	public static final int Player4A_Y=340;
	
	public static final int Player5A_X=80;
	public static final int Player5A_Y=430;
	
	//球员位置 B
	public static final int Player1B_X=840;
	public static final int Player1B_Y=70;
	
	public static final int Player2B_X=840;
	public static final int Player2B_Y=160;
	
	public static final int Player3B_X=840;
	public static final int Player3B_Y=250;
	
	public static final int Player4B_X=840;
	public static final int Player4B_Y=340;
	
	public static final int Player5B_X=840;
	public static final int Player5B_Y=430;
	
	//肖像宽高
	public static final int PORTAIT_WIDTH=PLAYER_WIDTH;
	public static final int PORTAIT_HEIGHT=PLAYER_HEIGHT-15;
	//中央比分牌宽高
	public static final int MIDDLE_WIDTH=600;
	public static final int MIDDLE_HEIGHT=380;
	
	//中央比分牌位置
	public static final int MIDDLE_X=200;
	public static final int MIDDLE_Y=120;
	
	//控制栏宽高
	public static final int CONTROL_WIDTH=FRAME_WIDTH;
	public static final int CONTROL_HEIGHT=120;
	
	
	//控制栏位置
	public static final int CONTROL_X=0;
	public static final int CONTROL_Y=FRAME_HEIGHT-CONTROL_HEIGHT-35;
	
	//控制栏状态框宽高
	public static final int CONTROLSTATE_WIDTH=120;
	public static final int CONTROLSTATE_HEIGHT=CONTROL_HEIGHT;
	
	//控制栏细节区宽高
	public static final int CONTROLDETAIL_WIDTH=680;
	public static final int CONTROLDETAIL_HEIGHT=CONTROL_HEIGHT;
	
	//控制栏细节区位置
	public static final int CONTROLDETAIL_X=CONTROLSTATE_WIDTH;
	public static final int CONTROLDETAIL_Y=0;
	
	//控制聊天区位置
	public static final int CHAT_X=CONTROLDETAIL_WIDTH+CONTROLSTATE_WIDTH;
	public static final int CHAT_Y=0;
	
	//控制聊天区宽高
	public static final int CHAT_WIDTH=FRAME_WIDTH-CONTROLDETAIL_WIDTH+CONTROLSTATE_WIDTH;
	public static final int CHAT_HEIGHT=CONTROL_HEIGHT;
	
	//控制聊天输入区宽高
	public static final int CHATINPUT_WIDTH=CHAT_WIDTH;
	public static final int CHATINPUT_HEIGHT=35;
	
	
	//控制聊天输入区位置
	public static final int CHATINPUT_X=0;
	public static final int CHATINPUT_Y=CONTROL_HEIGHT-CHATINPUT_HEIGHT;
	
	//控制聊天输出区宽高
	public static final int CHATOUTPUT_WIDTH=CHAT_WIDTH;
	public static final int CHATOUTPUT_HEIGHT=CHATINPUT_Y;
	
	//控制比分牌宽高
	public static final int SCOREBOARD_WIDTH=120;
	public static final int SCOREBOARD_HEIGHT=70;
	
	//控制比分牌位置
	public static final int SCOREBOARD_A_X=280;
	public static final int SCOREBOARD_A_Y=35;
	
	public static final int SCOREBOARD_B_X=600;
	public static final int SCOREBOARD_B_Y=35;
	
	//控制姓名牌宽高
	public static final int NAMEBOARD_WIDTH=80;
	public static final int NAMEBOARD_HEIGHT=70;
	
	//控制姓名牌位置
	public static final int NAMEBOARD_A_X=200;
	public static final int NAMEBOARD_A_Y=35;
	
	public static final int NAMEBOARD_B_X=720;
	public static final int NAMEBOARD_B_Y=35;
	
	//控制剩余时间宽高
	public static final int TIMEBOARD_WIDTH=200;
	public static final int TIMEBOARD_HEIGHT=35;
	
	//控制剩余时间位置
	public static final int TIMEBOARD_X=400;
	public static final int TIMEBOARD_Y=70;
	
	//替补肖像位置
	public static final int Reverse_1_X=15;
	public static final int Reverse_1_Y=20;
	
	public static final int Reverse_2_X=110;
	public static final int Reverse_2_Y=20;
	
	public static final int Reverse_3_X=205;
	public static final int Reverse_3_Y=20;
	
	public static final int Reverse_4_X=300;
	public static final int Reverse_4_Y=20;
	
	public static final int Reverse_5_X=395;
	public static final int Reverse_5_Y=20;
	
	public static final int Reverse_6_X=490;
	public static final int Reverse_6_Y=20;
	
	public static final int Reverse_7_X=585;
	public static final int Reverse_7_Y=20;
	
	//控制栏选择框挡板宽高
	public static final int ControlPanelChoose_WIDTH=680;	
	public static final int ControlPanelChoose_HEIGHT=35;	

	//具体操作选项的位置的宽高
	public static final int Meddling_WIDTH=ControlPanelChoose_WIDTH;	
	public static final int Meddling_HEIGHT=CONTROL_HEIGHT-ControlPanelChoose_HEIGHT;	
	
	//具体操作选项的位置的位置
	public static final int Meddling_X=0;	
	public static final int Meddling_Y=ControlPanelChoose_HEIGHT;	
	
	//JSlider 长宽
	public static final int Slider_WIDTH=120;	
	public static final int Slider_HEIGHT=30;	
	
	//JSlider 位置
	public static final int Slider_R0_C0_X=80;	
	public static final int Slider_R0_C0_Y=5;	
	
	public static final int Slider_R1_C0_X=80;	
	public static final int Slider_R1_C0_Y=50;	
	
	public static final int Slider_R0_C1_X=280;	
	public static final int Slider_R0_C1_Y=5;	
	
	public static final int Slider_R1_C1_X=280;	
	public static final int Slider_R1_C1_Y=50;	
	
	public static final int Slider_R0_C2_X=480;	
	public static final int Slider_R0_C2_Y=5;	
	
	//与JSlider 对应的JLabel 位置，用offset 表示
	public static final int SliderLabelOffset=40;	

	//与JSlider 对应的JLabel 宽高
	public static final int SliderLabel_WIDTH=40;	
	public static final int SliderLabel_HEIGHT=35;	

	//换人选项框宽高
	public static final int RotatePanel_WIDTH=FRAME_WIDTH;	
	public static final int RotatePanel_HEIGHT=30;	
	
	//换人选项框位置
	public static final int RotatePanel_X=0;	
	public static final int RotatePanel_Y=CONTROL_Y-RotatePanel_HEIGHT;	

}
