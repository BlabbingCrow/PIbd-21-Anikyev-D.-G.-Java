import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class DialogConfig extends JDialog {
	ITransport tractor = null;
	PanelConfig tractorPanel;
	boolean succes;
	
	public DialogConfig(JFrame parent) {
		super(parent, true);
		initialize();
	}
	
	public boolean isSuccessful() {
        setVisible(true);
        return succes;
    }
	
	private void initialize() {
		this.getContentPane().setBackground(SystemColor.controlHighlight);
        this.setBounds(100, 100, 420, 300);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

        JLabel labelTractorBase = new JLabel("Трактор");
        labelTractorBase.setHorizontalAlignment(SwingConstants.CENTER);
        labelTractorBase.setBounds(10, 29, 89, 24);
        labelTractorBase.setBorder(border);
        getContentPane().add(labelTractorBase);

        JLabel labeltractor = new JLabel("Бульдозер");
        labeltractor.setHorizontalAlignment(SwingConstants.CENTER);
        labeltractor.setBounds(10, 64, 89, 24);
        labeltractor.setBorder(border);
        getContentPane().add(labeltractor);

        JLabel labelMainColor = new JLabel("Основной цвет");
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelMainColor.setBounds(146, 149, 122, 30);
        labelMainColor.setBorder(border);
        getContentPane().add(labelMainColor);

        JLabel labelSecondColor = new JLabel("Доп. цвет");
        labelSecondColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelSecondColor.setBounds(146, 193, 122, 30);
        labelSecondColor.setBorder(border);
        getContentPane().add(labelSecondColor);

        tractorPanel = new PanelConfig();
        tractorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        FlowLayout flowLayout = (FlowLayout) tractorPanel.getLayout();
        tractorPanel.setBounds(126, 29, 153, 110);
        this.getContentPane().add(tractorPanel);

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent) e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }
        };

        labelTractorBase.addMouseListener(ml);
        labeltractor.addMouseListener(ml);
        labeltractor.setTransferHandler(new TransferHandler("text"));
        labelTractorBase.setTransferHandler(new TransferHandler("text"));

        tractorPanel.setDropTarget(new DropTarget() {

            public void drop(DropTargetDropEvent e) {

                try {
                    for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                        if (e.getTransferable().getTransferData(df) == "Трактор") {
                            tractor = new TractorBase(10, 10, Color.YELLOW);
                            tractorPanel.setTractor(tractor);
                            tractor.SetPosition(20, 20, tractorPanel.getWidth(), tractorPanel.getHeight());
                        } else if (e.getTransferable().getTransferData(df) == "Бульдозер") {
                            tractor = new Tractor(30, 2, Color.YELLOW, Color.RED, true, true);
                            tractorPanel.setTractor(tractor);
                            tractor.SetPosition(20, 20, tractorPanel.getWidth(), tractorPanel.getHeight());
                        }
                        tractorPanel.repaint();
                    }
                } catch (Exception ex) {
                }

            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        JPanel panelYellow = new JPanel();
        panelYellow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelYellow.setName("yellow");
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBounds(347, 83, 31, 39);
        this.getContentPane().add(panelYellow);

        JPanel panelWhite = new JPanel();
        panelWhite.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelWhite.setName("white");
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBounds(306, 29, 31, 39);
        this.getContentPane().add(panelWhite);

        JPanel panelBlue = new JPanel();
        panelBlue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelBlue.setName("blue");
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(347, 29, 31, 39);
        this.getContentPane().add(panelBlue);

        JPanel panelRed = new JPanel();
        panelRed.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelRed.setName("red");
        panelRed.setBackground(Color.RED);
        panelRed.setBounds(347, 187, 31, 39);
        this.getContentPane().add(panelRed);

        JPanel panelGreen = new JPanel();
        panelGreen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelGreen.setName("green");
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(306, 133, 31, 39);
        this.getContentPane().add(panelGreen);

        JPanel panelGrey = new JPanel();
        panelGrey.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelGrey.setName("grey");
        panelGrey.setBackground(Color.GRAY);
        panelGrey.setBounds(306, 83, 31, 39);
        this.getContentPane().add(panelGrey);

        JPanel panelBlack = new JPanel();
        panelBlack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelBlack.setName("black");
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBounds(306, 187, 31, 39);
        this.getContentPane().add(panelBlack);

        JPanel panelOrange = new JPanel();
        panelOrange.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelOrange.setName("orange");
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBounds(347, 133, 31, 39);
        this.getContentPane().add(panelOrange);

        panelWhite.addMouseListener(ml);
        panelWhite.setTransferHandler(new TransferHandler("name"));

        panelBlue.addMouseListener(ml);
        panelBlue.setTransferHandler(new TransferHandler("name"));

        panelRed.addMouseListener(ml);
        panelRed.setTransferHandler(new TransferHandler("name"));

        panelGrey.addMouseListener(ml);
        panelGrey.setTransferHandler(new TransferHandler("name"));

        panelBlack.addMouseListener(ml);
        panelBlack.setTransferHandler(new TransferHandler("name"));

        panelOrange.addMouseListener(ml);
        panelOrange.setTransferHandler(new TransferHandler("name"));

        panelYellow.addMouseListener(ml);
        panelYellow.setTransferHandler(new TransferHandler("name"));

        panelGreen.addMouseListener(ml);
        panelGreen.setTransferHandler(new TransferHandler("name"));

        JButton btnAdd = new JButton("Добавить");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                succes = true;
                dispose();
            }
        });
        btnAdd.setBounds(10, 133, 106, 23);
        this.getContentPane().add(btnAdd);

        JButton btnCancell = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
        btnCancell.setBounds(10, 187, 106, 23);
        this.getContentPane().add(btnCancell);
        btnCancell.addActionListener((ActionEvent e) -> {
            succes = false;
            dispose();
        });

        labelMainColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (tractor != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            tractor.setMainColor(e.getTransferable().getTransferData(df).toString());
                            tractorPanel.setTractor(tractor);
                            tractorPanel.repaint();
                        }
                    } catch (Exception ex) {
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        labelSecondColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (tractor != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            ((Tractor) tractor).setDopColor(e.getTransferable().getTransferData(df).toString());
                            tractorPanel.setTractor(tractor);
                            tractorPanel.repaint();
                        }
                    } catch (Exception ex) {
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
	}
	
	public ITransport getTractor() {
        return tractor;
    }
}
