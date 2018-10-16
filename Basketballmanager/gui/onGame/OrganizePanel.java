package Basketballmanager.gui.onGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Basketballmanager.constant.Const_UI;

public class OrganizePanel extends JPanel implements ChangeListener {

	/**
	 * Panel for detailed control
	 */
	JSlider[] Place = new JSlider[5];
	JLabel[] PlaceLabel = new JLabel[5];

	private int[] JsliderNumber = new int[5];

	
	/**
     * Variable list
     * Place: List of JSliders for detailed manipulation 
     * PlaceLabel: List of JLabels showing the label for five sliders
     * JsliderNumber: List of int registering the value of five sliders
     */
	OrganizePanel() {
		this.setSize(Const_UI.Meddling_WIDTH, Const_UI.Meddling_HEIGHT);
		this.setLayout(null);
		for (int i = 0; i < 5; i++) {
			Place[i] = new JSlider();
			Place[i].setValue(20);
			JsliderNumber[i] = 20;
			Place[i].setSize(Const_UI.Slider_WIDTH, Const_UI.Slider_HEIGHT);
			Place[i].addChangeListener(this);

		}
		Place[0].setLocation(Const_UI.Slider_R0_C0_X, Const_UI.Slider_R0_C0_Y);
		Place[1].setLocation(Const_UI.Slider_R1_C0_X, Const_UI.Slider_R1_C0_Y);
		Place[2].setLocation(Const_UI.Slider_R0_C1_X, Const_UI.Slider_R0_C1_Y);
		Place[3].setLocation(Const_UI.Slider_R1_C1_X, Const_UI.Slider_R1_C1_Y);
		Place[4].setLocation(Const_UI.Slider_R0_C2_X, Const_UI.Slider_R0_C2_Y);
		PlaceLabel[0] = new JLabel("Position 1");
		PlaceLabel[1] = new JLabel("Position 2");
		PlaceLabel[2] = new JLabel("Position 3");
		PlaceLabel[3] = new JLabel("Position 4");
		PlaceLabel[4] = new JLabel("Position 5");
		for (int i = 0; i < 5; i++) {
			PlaceLabel[i].setSize(Const_UI.SliderLabel_WIDTH, Const_UI.SliderLabel_HEIGHT);
		}
		PlaceLabel[0].setLocation(Const_UI.Slider_R0_C0_X - Const_UI.SliderLabelOffset, Const_UI.Slider_R0_C0_Y);
		PlaceLabel[1].setLocation(Const_UI.Slider_R1_C0_X - Const_UI.SliderLabelOffset, Const_UI.Slider_R1_C0_Y);
		PlaceLabel[2].setLocation(Const_UI.Slider_R0_C1_X - Const_UI.SliderLabelOffset, Const_UI.Slider_R0_C1_Y);
		PlaceLabel[3].setLocation(Const_UI.Slider_R1_C1_X - Const_UI.SliderLabelOffset, Const_UI.Slider_R1_C1_Y);
		PlaceLabel[4].setLocation(Const_UI.Slider_R0_C2_X - Const_UI.SliderLabelOffset, Const_UI.Slider_R0_C2_Y);

		for (int i = 0; i < 5; i++) {
			this.add(PlaceLabel[i]);
			this.add(Place[i]);
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		/**
		 * Change the value of sliders
		 */
		// TODO Auto-generated method stub
		if (e.getSource() == Place[0]) {
			Adjust(0);
		} else if (e.getSource() == Place[1]) {
			Adjust(1);
		} else if (e.getSource() == Place[2]) {
			Adjust(2);

		} else if (e.getSource() == Place[3]) {
			Adjust(3);

		} else if (e.getSource() == Place[4]) {
			Adjust(4);

		}
	}

	private void Adjust(int index) {
		/**
		 * Adjust the value of all sliders, make sure that all value sums up to 100
		 * input:
		 * 		index(int): currently changed value
		 * output:
		 * 		
		 */
		int temp = Place[index].getValue();/* current number */
		int distance = JsliderNumber[index] - temp;/* value differential between previous and current */
		final int original=distance;
		final int[] storeFor={JsliderNumber[0],JsliderNumber[1],
				JsliderNumber[2],JsliderNumber[3],JsliderNumber[4]};
		JsliderNumber[index] = temp;/* set this */
		boolean[] average = { true, true, true, true, true };
		average[index] = false;
		float averageNumber = 4;

		if (distance == 0)
			return;
		if (distance > 0) {/* case distance>=0 */
			int deduct = (int) Math.ceil(distance / averageNumber);

			for (int i = 0; i < 5; i++) {
				if (distance == 0) {
					SetValue();
					DetectError(original,storeFor,index);
					return;
				}
				if (average[i] == false)
					continue;
				if (distance < deduct)
					deduct = distance;

				JsliderNumber[i] += deduct;
				distance -= deduct;
			}
		}

		else {
			/* distance<0 */
			distance = -distance;/* change it to positive */
			while (true) {
				boolean Out=true;
				int[] Temp=GenerateDeduct(average,distance);
				for(int i=0;i<5;i++)
				{
					if(average[i]==false)
						continue;
					if(JsliderNumber[i]==Temp[i])
					{
						average[i]=false;
						JsliderNumber[i]=0;
						distance-=Temp[i];
					}
					else if(JsliderNumber[i]>Temp[i])
					{
						distance-=Temp[i];
						JsliderNumber[i]-=Temp[i];
					}
					else{
						/* JsliderNumber[i]<Temp[i] */
						JsliderNumber[i]=0;
						distance-=JsliderNumber[i];
						average[i]=false;
						Out=false;
					}
				}
				if(Out==true)
				{
					SetValue();
					DetectError(original,storeFor,index);
					return;
				}
			}
		}
	}

	private void SetValue() {
		/**
		 * set values for all 5 sliders
		 * input:
		 * output:
		 * 
		 */
		for (int j = 0; j < 5; j++) {
			Place[j].setValue(JsliderNumber[j]);
		}
	}

	private int[] GenerateDeduct(boolean[] Ave, int total) {
		/**
		 * generate the value of the others accordingly
		 * input:
		 * 		Ave(boolean[]): whether this slot cannot change, False for no, Yes otherwise
		 * 		total(int): sum of remaining values
		 * output:
		 * 		int[]: generated value for all 5 slots
		 */
		
		int average = 0;
		int[] result = { 0, 0, 0, 0, 0 };
		for (int i = 0; i < 5; i++) {
			if (Ave[i] == true) {
				average++;
			}
		}
		int deduct = (int) Math.ceil(total*1.0 / average);
		for (int i = 0; i < 5; i++) {
			if (Ave[i] == false)
				continue;
			if (total < deduct) {
				deduct = total;
			}
			
			total -= deduct;
			result[i] = deduct;
		}
		
		
		
		
		
		return result;
	}
	public void PrintNumber()
	{
		for(int i=0;i<5;i++)
		{
			System.out.println(JsliderNumber[i]);
		}
	}
	
	public void DetectError()
	{
		int sum=0;
		for(int i=0;i<5;i++)
		{
			sum+=JsliderNumber[i];
		}
		if(sum!=100)
		{
			System.out.println("ErrorInDetection");
			PrintNumber();
		}
	}
	
	
	private void DetectError(int temp,int a[],int index)
	{
		/**
		 * inner error Detection function
		 * bug pending
		 * 		
		 */
		/*
		int sum=0;
		for(int i=0;i<5;i++)
		{
			sum+=JsliderNumber[i];
		}
		if(sum!=100)
		{
			System.out.println("ErrorInDetection");
			System.out.println("TEMP");

			System.out.println(temp);
			System.out.println("AROUND");

			for(int i=0;i<5;i++)
			{
				System.out.println(a[i]);
			}
			System.out.println("INDEX");
			System.out.println(index);
			System.out.println("Result");
			PrintNumber();
		}
		*/
	}
	
	public int[] getStre()
	{
		return JsliderNumber;
	}
}
