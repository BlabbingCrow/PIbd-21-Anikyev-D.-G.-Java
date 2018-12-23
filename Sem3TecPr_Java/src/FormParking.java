import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormParking {
	private JFrame frame;
	private JTextField textField;
	private JPanel panel;
	private JPanel panelTake;
	public static JList listBoxParkings;
	private DefaultListModel model;
	private JMenuBar menuBar;
	
	FileHandler fh;
	private static Logger logger= Logger.getLogger(FormParking.class.getName());
	public static MultiLevelParking parking;
	private static final int countLevel = 5;

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
		parking = new MultiLevelParking(countLevel, panel.getWidth(), panel.getHeight());
		panel.updateUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			fh = new FileHandler("C:\\Users\\aniky\\OneDrive\\Рабочий стол\\University\\2 курс\\3 семестр\\ТехПрог\\fileJava.txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException ex){
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		panel = new PanelParking();
		panel.setBounds(10, 11, 784, 443);
		frame.getContentPane().add(panel);
		
		JButton buttonSetTractor = new JButton("Заказать технику");
		buttonSetTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listBoxParkings.getSelectedIndex() > -1) {
					try {
	                    DialogConfig dConfig = new DialogConfig(frame);
	                    if (dConfig.isSuccessful()) {
	                    	ITransport tractor = dConfig.getTractor();
	                        int i = parking.getParking(listBoxParkings.getSelectedIndex()).Add(tractor);
	                        logger.info("Добавлен автомобиль " + tractor.toString() + " на место " + i);
	                        panel.repaint();
	                    }
					} catch (ParkingOverflowException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
					}
                }
			}
		});
		buttonSetTractor.setBounds(804, 194, 185, 50);
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
					try {
						ITransport tractor = parking.getParking(listBoxParkings.getSelectedIndex()).Remove(Integer.parseInt(textField.getText()));
                    	tractor.SetPosition(5, 5, panelTake.getWidth(), panelTake.getHeight());
                        PanelTakeParking.tractor = tractor;
                        panelTake.updateUI();
                        panel.updateUI();
                        logger.info("Изъят автомобиль " + tractor.toString() + " с места " + textField.getText());
					} catch(ParkingNotFoundException ex) {
	                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
	                	PanelTakeParking.tractor = null;
	                    panelTake.updateUI();
					}
					catch (Exception ex)
                    {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                	}
                }
			}
		});
		buttonTake.setBounds(845, 314, 89, 23);
		frame.getContentPane().add(buttonTake);
		
		
		listBoxParkings = new JList(); 
		listBoxParkings.setBounds(825, 20, 118, 118); 
		frame.getContentPane().add(listBoxParkings);
		
		model = new DefaultListModel();
		for(int i = 0; i < countLevel; i++) {
			model.addElement("Уровень " + (i+1));
		}
		listBoxParkings.setModel(model); 
		listBoxParkings.setSelectedIndex(0); 

		
		listBoxParkings.addListSelectionListener(new ListSelectionListener() { 
			@Override 
			public void valueChanged(ListSelectionEvent e) { 
				panel.updateUI(); 
			} 
		});
		
		Font font = new Font("Verdana", Font.PLAIN, 11);
        menuBar = new JMenuBar();
        menuBar.setFont(font);

        JMenu newMenu = new JMenu("Файл");
        newMenu.setFont(font);
        menuBar.add(newMenu);

        JMenuItem saveFileItem = new JMenuItem("Сохранить");
        saveFileItem.setFont(font);
        newMenu.add(saveFileItem);

        JMenuItem loadFileItem = new JMenuItem("Загрузить");
        loadFileItem.setFont(font);
        newMenu.add(loadFileItem);

        saveFileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChoser = new JFileChooser();
                fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                int ret = fileChoser.showDialog(null, "Сохранить файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChoser.getSelectedFile();
                    try {
                    	parking.saveData(file.getAbsolutePath());
                        JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
                        logger.info("Сохранено в файл " + file.getName());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка при сохранении", 0, null);
                    }
                }
            }
        });

        loadFileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChoser = new JFileChooser();
                fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                int ret = fileChoser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChoser.getSelectedFile();
                    try { 
                    	parking.loadData(file.getAbsolutePath());
                        JOptionPane.showMessageDialog(frame, "Загрузка прошло успешно");
                    	logger.info("Загружено из файла " + file.getName());
                        panel.updateUI();
                    } catch (ParkingOccupiedPlaceException ex) {
                        JOptionPane.showMessageDialog(frame, "Занято место");
                    } catch (Exception ex) {
						JOptionPane.showMessageDialog(null,ex.getMessage(), "Неизвестная ошибка при загрузке", 0, null);
					}
                }
            }
        });

        frame.setJMenuBar(menuBar);
	}
}
