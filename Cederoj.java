import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Cederoj 
extends JFrame 
implements ActionListener {
	private final int frameWidth = 800;
	private final int frameHeight = 600;
	private Cederpanel panel;
	private JButton button;

	public Cederoj() {
		panel = new Cederpanel(frameWidth, frameHeight);
		button = new JButton("Start");

		button.addActionListener(this);

		add(panel);
		add(button, BorderLayout.SOUTH);

		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			panel.startSimulation();
		}
	}

	public static void main(String[] args) {
		new Cederoj();
	}
}