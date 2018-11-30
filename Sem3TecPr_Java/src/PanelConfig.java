import javax.swing.*;
import java.awt.*;

public class PanelConfig extends JPanel {
	private ITransport tractor;

    void setTractor(ITransport transport) {
    	tractor = transport;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (tractor != null) {
        	tractor.DrawTractor(g);
        }
    }
}
