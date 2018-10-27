import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormParking {
	public static Parking<ITransport> parking;
	
	private JFrame frame;
	private JTextField textField;
	private JPanel panel;
	private JPanel panelTake;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormParking window = new FormParking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormParking() {
		initialize();
		parking = new Parking<ITransport>(20, panel.getWidth(), panel.getHeight());
		panel.updateUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new PanelParking();
		panel.setBounds(10, 11, 784, 443);
		frame.getContentPane().add(panel);
		
		JButton buttonSetTractorBase = new JButton("Припарковать трактор");
		buttonSetTractorBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				ITransport tractor = new TractorBase(100, 1000, mainColor);
                parking.Add(tractor);
                panel.updateUI();
			}
		});
		buttonSetTractorBase.setBounds(804, 11, 170, 85);
		frame.getContentPane().add(buttonSetTractorBase);
		
		JButton buttonSetTractor = new JButton("Припарковать бульдозер");
		buttonSetTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				Color dopColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
				ITransport tractor = new Tractor(100, 1000, mainColor, dopColor, true, true);
                parking.Add(tractor);
                panel.updateUI();
			}
		});
		buttonSetTractor.setBounds(804, 107, 170, 85);
		frame.getContentPane().add(buttonSetTractor);
		
		JLabel label = new JLabel("Место:");
		label.setBounds(832, 286, 46, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(888, 283, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		panelTake = new PanelTakeParking();
		panelTake.setBounds(804, 348, 170, 106);
		frame.getContentPane().add(panelTake);
		
		JButton buttonTake = new JButton("Забрать");
		buttonTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() != "")
	            {
	                ITransport tractor = parking.Remove(Integer.parseInt(textField.getText()));
	                panel.updateUI();
	                PanelTakeParking.tractor = tractor;
	                panelTake.updateUI();
	            }
			}
		});
		buttonTake.setBounds(845, 314, 89, 23);
		frame.getContentPane().add(buttonTake);
	}
}
