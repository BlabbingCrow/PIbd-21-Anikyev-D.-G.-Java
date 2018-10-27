import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		FormParking.parking.Draw(g);
	}
}
