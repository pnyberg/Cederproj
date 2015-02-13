import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.Timer;

public class Cederpanel 
extends JPanel 
implements ActionListener {
	private final int number = 50;
	private static final String nameFile = "Cedernames.txt";

	private int width;
	private int height;
	private Timer timer;
	private LinkedList<Cederman> men;

	public Cederpanel(int frameWidth, int frameHeight) {
		width = frameWidth;
		height = frameHeight - 50;

		timer = new Timer(10, this);
		men = new LinkedList<Cederman>();

		for (int i = 0 ; i < number ; i++) {
			men.add(new Cederman((int)(Math.random() * width), (int)(Math.random() * height), (int)(Math.random() * 10) % 4, Cederpanel.generateName()));
		}
	}

	public void startSimulation() {
		timer.start();
	}

	public static Cederman generateNewCederman(Cederman parent1, Cederman parent2) {
		String name1 = parent1.getName();
		String name2 = parent2.getName();

		String newName = "";

		while (true) {
			int lettersFromFirst = (int)(Math.random() * 10) % (name1.length() < 5 ? name1.length() : 5);
			for (int i = 0 ; i < lettersFromFirst ; i++) {
				int letterIndex = (int)(Math.random() * 100) % name1.length();
				newName += name1.charAt(letterIndex);
				name1 = name1.substring(0, letterIndex) + name1.substring(letterIndex, name1.length());
			}

			lettersFromFirst = (int)(Math.random() * 10) % (name2.length() < 5 ? name2.length() : 5);
			for (int i = 0 ; i < lettersFromFirst ; i++) {
				int letterIndex = (int)(Math.random() * 100) % name2.length();
				newName += name2.charAt(letterIndex);
				name2 = name2.substring(0, letterIndex) + name2.substring(letterIndex, name2.length());
			}

			if (newName.length() < 4) {
				newName = "";
			} else {
				newName = newName.substring(0, 1).toUpperCase() + newName.substring(1, newName.length()).toLowerCase();
				break;
			}
		}

		int x = parent1.getX();
		int y = parent1.getY();
		int direction = ((parent1.getDirection() + parent2.getDirection()) / 2) % 4;

		return new Cederman(x, y, direction, newName);
	}

	public static String generateName() {
		int choice = (int)(Math.random() * 100) % Cederpanel.names().length;

		return Cederpanel.names()[choice];
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0 ; i < number ; i++)
			men.get(i).doStuff();

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		for (int i = 0 ; i < number ; i++)
			men.get(i).paint(g);
	}

	public static String[] names() {
		String[] names;

		try {
			Scanner scanner = new Scanner(new File(nameFile));
			LinkedList<String> readNames = new LinkedList<String>();

			while(scanner.hasNext()) {
				String text = scanner.next();
				readNames.add(text);
			}

			names = new String[readNames.size()];

			for (int i = 0 ; i < readNames.size() ; i++) {
				names[i] = readNames.get(i);
			}
		} catch (Exception e) {
			names = new String[0];
		}
		return names;
	}
}