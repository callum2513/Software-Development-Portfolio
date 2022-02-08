package Assignment;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Splash extends JFrame
{
	private Container cPane;
	private JPanel pCenter;
	private JLabel lblImg;
	private ImageIcon img;
	private Timer timer;
	private JFrame frame;
	
	
	Splash()
	{
		cPane = getContentPane();
		cPane.setBackground(new Color(50,50,50));
		pCenter = new JPanel();
		pCenter.setLayout(new FlowLayout(FlowLayout.CENTER,400,25));
		
		
		
		// Center Panel //
		lblImg = new JLabel ("");
		img = new ImageIcon("splash.png");
		img = new ImageIcon(new ImageIcon("splash.png").getImage().getScaledInstance(1200, 630, Image.SCALE_DEFAULT));
		
		lblImg.setIcon(img);
		pCenter.add(lblImg);
	
		cPane.add(pCenter,BorderLayout.CENTER);
		
		ActionListener timeloop = new TimeLoop();
		timer = new Timer(1000, timeloop);
		timer.start();
		
		this.setVisible(false);
					
	}
	class TimeLoop implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			timer.stop();
			AppLayout newAL = new AppLayout();
			newAL.setTitle("UCAS Applications System");
			newAL.setSize(1200, 630);
			newAL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newAL.setVisible(true);	
		}
	}	
}

