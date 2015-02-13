import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Cederoj 
extends JFrame 
implements ActionListener {
	private final int frameWidth = 800;
	private final int frameHeight = 600;
	private Cederpanel panel;
	private JPanel buttonPanel;
	private JButton startButton;

	public Cederoj() {
		panel = new Cederpanel(frameWidth, frameHeight);
		buttonPanel = new JPanel();
		startButton = new JButton("Start");

		startButton.addActionListener(this);

		buttonPanel.add(startButton);

		add(panel);
		add(buttonPanel, BorderLayout.SOUTH);

		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			panel.init();
			panel.startSimulation();
		}
	}

	public static void main(String[] args) {
		new Cederoj();
	}
}