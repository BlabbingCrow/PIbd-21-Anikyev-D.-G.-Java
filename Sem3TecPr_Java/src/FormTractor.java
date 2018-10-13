import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class FormTractor {

	private JFrame frame;
	private JPanel panel;
	private JButton buttonUp;
	private JButton buttonDown;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonCreate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTractor window = new FormTractor();
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
	public FormTractor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new PanelTractor();
		panel.setBounds(0, 0, 884, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		buttonCreate = new JButton("Создать");
		buttonCreate.setBounds(10, 5, 92, 23);
		panel.add(buttonCreate);
		
		buttonRight = new JButton("");
		buttonRight.setBounds(824, 391, 50, 50);
		panel.add(buttonRight);
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PanelTractor.initialization) {
					PanelTractor.tractor.MoveTransport(Direction.Right);
				}
				panel.updateUI();
			}
		});
		buttonRight.setIcon(new ImageIcon("E:\\projects\\Java\\Eclipse\\Sem3TecPr1_Java\\Resources\\ArrowRight1.png"));
		
		buttonDown = new JButton("");
		buttonDown.setBounds(764, 391, 50, 50);
		panel.add(buttonDown);
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PanelTractor.initialization) {
					PanelTractor.tractor.MoveTransport(Direction.Down);
				}
				panel.updateUI();
			}
		});
		buttonDown.setIcon(new ImageIcon("E:\\projects\\Java\\Eclipse\\Sem3TecPr1_Java\\Resources\\ArrowDown1.png"));
		
		buttonLeft = new JButton("");
		buttonLeft.setBounds(704, 391, 50, 50);
		panel.add(buttonLeft);
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PanelTractor.initialization) {
					PanelTractor.tractor.MoveTransport(Direction.Left);
				}
				panel.updateUI();
			}
		});
		buttonLeft.setIcon(new ImageIcon("E:\\projects\\Java\\Eclipse\\Sem3TecPr1_Java\\Resources\\ArrowLeft1.png"));
		
		buttonUp = new JButton("");
		buttonUp.setBounds(764, 330, 50, 50);
		panel.add(buttonUp);
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PanelTractor.initialization) {
					PanelTractor.tractor.MoveTransport(Direction.Up);
				}
				panel.updateUI();
			}
		});
		buttonUp.setIcon(new ImageIcon("E:\\projects\\Java\\Eclipse\\Sem3TecPr1_Java\\Resources\\ArrowUp1.png"));
		
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rnd = new Random();
				PanelTractor.tractor = new Tractor(rnd.Next(100, 300), rnd.Next(1000, 2000), Color.blue, Color.yellow);
				PanelTractor.initialization = true;
				PanelTractor.tractor.SetPosition(rnd.Next(10, 100), rnd.Next(10, 100), panel.getWidth(), panel.getHeight());

				panel.updateUI();
			}
		});
	}
}
