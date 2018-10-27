import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeParking extends JPanel {
	public static ITransport tractor;
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		if (tractor != null)
        {
            tractor.SetPosition(5, 5, this.getWidth(), this.getHeight());
            tractor.DrawTractor(g);
        }
	}
}
