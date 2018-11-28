import javax.swing.JPanel;
import java.awt.Graphics;

public class PanelTractor extends JPanel {
	public static boolean initialization = false;
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if(initialization) {
			FormTractor.tractor.DrawTractor(g);
		}
	}
}
