package Assignment;


import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class RunLayout 
{
	public static void main(String[] args) 
	{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        } 
		    }
		} catch (Exception e)
		{
		    JOptionPane.showMessageDialog(null, "Error with 'Look and Feel' UI Settings - Consult System Developer");		}
		
		Splash app = new Splash();
		app.setSize(1200, 630);
		app.setVisible(true);
	}
}
