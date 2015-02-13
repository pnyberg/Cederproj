import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.Timer;
import javax.swing.*;

public class Cederpanel 
extends JPanel 
implements ActionListener {
	private final int number = 20;

	private int width = 800;
	private int height = 550;
	private Timer timer;
	private LinkedList<Cederman> men;

	public Cederpanel() {
		timer = new Timer(10, this);
		men = new LinkedList<Cederman>();

		for (int i = 0 ; i < number ; i++) {
			men.add(new Cederman(70 + (i % 5) * 100, 70 + 100 * (i / 5), (int)(Math.random() * 10) % 4, "Bob"));
		}
	}

	public void startSimulation() {
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0 ; i < number ; i++)
			men.get(i).doStuff();

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0,0, width, height);

		for (int i = 0 ; i < number ; i++)
			men.get(i).paint(g);
	}
}