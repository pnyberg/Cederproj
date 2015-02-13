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

	public void generateNewCederman(Cederman parent1, Cederman parent2) {
		String name1 = parent1.getName();
		String name2 = parent2.getName();

		String newName = "";

		int lettersFromFirst = (int)(Math.random() * 10) % 5;
		for (int i = 0 ; i < lettersFromFirst ; i++) {
			int letterIndex = (int)(Math.random() * 100) % name1.length();
			newName += name1.charAt(letterIndex);
			name1 = name1.substring(0, letterIndex) + (letterIndex, name1.length());
		}

		lettersFromFirst = (int)(Math.random() * 10) % 5;
		for (int i = 0 ; i < lettersFromFirst ; i++) {
			int letterIndex = (int)(Math.random() * 100) % name2.length();
			newName += name2.charAt(letterIndex);
			name2 = name2.substring(0, letterIndex) + (letterIndex, name2.length());
		}

		int x = parent1.getX();
		int y = parent1.getY();
		int direction = ((parent1.getDirection() + parent2.getDirection()) / 2) % 4;

		men.add(new Cederman(x, y, direction, newName));
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