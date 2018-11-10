import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelParking extends JPanel {	
	
	@Override 
	public void paint(Graphics g) { 
		super.paint(g); 
		//FormParking.parking.Draw(g);
		int selectedParking = FormParking.listBoxParkings.getSelectedIndex();
		FormParking.parking.getParking(selectedParking).Draw(g);
        if(selectedParking != -1) {
	        if(FormParking.parking != null) {
	        	FormParking.parking.getParking(selectedParking).Draw(g);
			}
        }
	}
}
