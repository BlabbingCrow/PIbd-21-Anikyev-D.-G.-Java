import javax.swing.JPanel;
import java.awt.Graphics;

public class PanelTractor extends JPanel {
	public static ITransport tractor;
	public static boolean initialization = false;
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if(initialization) {
			tractor.DrawTractor(g);
		}
	}
	
	public void callRepaint() {
		this.repaint();
	}
}
