package Basketballmanager.gui.onGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Basketballmanager.constant.Const_UI;

public class ControlOffenseChoose extends JPanel implements ActionListener{
	
	/**
	 * control bar(right side) handling offense manipulation
	 */
	JButton ballhandling=new JButton("Distribution of ball right");
	JButton scorehandling=new JButton("Distribution of shooting");
	JButton createOpportunity=new JButton("Distribution of shooting");
	JButton strategy=new JButton("Selection of offense strategy");

	
	ControlOffenseChoose()
	{
		this.setLayout(new GridLayout(1,4));
		this.setSize(Const_UI.ControlPanelChoose_WIDTH, Const_UI.ControlPanelChoose_HEIGHT);
		ballhandling.setFocusPainted(false);
		scorehandling.setFocusPainted(false);
		createOpportunity.setFocusPainted(false);
		strategy.setFocusPainted(false);

		ballhandling.addActionListener(this);
		scorehandling.addActionListener(this);
		createOpportunity.addActionListener(this);
		strategy.addActionListener(this);
		ballhandling.setBackground(Color.RED);
		scorehandling.setBackground(Color.GRAY);
		createOpportunity.setBackground(Color.GRAY);
		strategy.setBackground(Color.GRAY);

		this.add(ballhandling);
		this.add(scorehandling);
		this.add(createOpportunity);
		this.add(strategy);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * response to mouse event
		 * choose a specific sub-panel
		 */
		// TODO Auto-generated method stub
		if(e.getSource()==ballhandling)
		{
			/* choose the sub-panel of ball handing(ball right) */
			ballhandling.setBackground(Color.RED);
			scorehandling.setBackground(Color.GRAY);
			createOpportunity.setBackground(Color.GRAY);
			strategy.setBackground(Color.GRAY);
			controlPanelOffense.organize.setVisible(true);
			controlPanelOffense.scoreArr.setVisible(false);
			controlPanelOffense.attack.setVisible(false);
			controlPanelOffense.Offense.setVisible(false);

		}
		else if(e.getSource()==scorehandling)
		{
			/* choose the sub-panel of score handing(shooting right) */
			ballhandling.setBackground(Color.GRAY);
			scorehandling.setBackground(Color.RED);
			createOpportunity.setBackground(Color.GRAY);
			strategy.setBackground(Color.GRAY);
			controlPanelOffense.organize.setVisible(false);
			controlPanelOffense.scoreArr.setVisible(true);
			controlPanelOffense.attack.setVisible(false);
			controlPanelOffense.Offense.setVisible(false);

		}
		else if(e.getSource()==createOpportunity)
		{
			/* choose the sub-panel of create(shooting) opportunity */
			ballhandling.setBackground(Color.GRAY);
			scorehandling.setBackground(Color.GRAY);
			createOpportunity.setBackground(Color.RED);
			strategy.setBackground(Color.GRAY);
			controlPanelOffense.organize.setVisible(false);
			controlPanelOffense.scoreArr.setVisible(false);
			controlPanelOffense.attack.setVisible(true);
			controlPanelOffense.Offense.setVisible(false);

		}
		else if(e.getSource()==strategy)
		{
			/* choose the sub-panel of offense strategy */
			ballhandling.setBackground(Color.GRAY);
			scorehandling.setBackground(Color.GRAY);
			createOpportunity.setBackground(Color.GRAY);
			strategy.setBackground(Color.RED);
			controlPanelOffense.organize.setVisible(false);
			controlPanelOffense.scoreArr.setVisible(false);
			controlPanelOffense.attack.setVisible(false);
			controlPanelOffense.Offense.setVisible(true);

		}
	}
}
