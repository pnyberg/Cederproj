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

	public static String[] names = new String[0];

	private int width;
	private int height;
	private int worldSize = 512;

	private Timer timer;
	private LinkedList<Cederman> men;
	public World world;

	public Cederpanel(int frameWidth, int frameHeight) {
		width = frameWidth;
		height = frameHeight - 50;

		men = new LinkedList<Cederman>();
	}

	public void init() {
		timer = new Timer(10, this);
		initNames();
		world = new World(worldSize);

		men.clear();

		Cederman parent1 = null;
		Cederman parent2 = null;
		for (int i = 0 ; i < number ; i++) {
			men.add(new Cederman(
				(int)(Math.random() * width),
				(int)(Math.random() * height),
				(int)(Math.random() * 10) % 4,
				Cederpanel.generateName(),
				Color.red,
				parent1,
				parent2
				));
		}
		//Initial placement of cedermans in the world based on their starting x&y
		for (int i= 0; i < number ; i++ ) {
			//world[men.get(i).getX()][men.get(i).getY()].place(men.get(i));
		}
	}

	public static void initNames() {
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
	}

	public void startSimulation() {
		timer.start();
	}

	/**
	 * Check if two Cedermen are on the same position
	 * If so, do other stuff
	 */
	public void checkCollisions() {
		int currentSize = men.size();
		LinkedList<Cederman> tempList = new LinkedList<Cederman>();

		for (int n = 0 ; n < currentSize ; n++) {
			for (int i = 0 ; i < n ; i++) {
				Cederman person1 = men.get(n);
				Cederman person2 = men.get(i);
				if (person1.getX() == person2.getX() && person1.getY() == person2.getY()) {
//					tempList.add(Cederpanel.generateNewCederman(person1, person2));

//					System.out.println("Collision between " + person1.getName() + "(" + n + ")" +
//										" and " + person2.getName() + "(" + i + ")" + " at " + 
//										person1.getX() + "," + person1.getY());
					if(person1.canGiveBirth() && person2.canGiveBirth() && mayConsume(person1, person2)){
						men.add(Cederpanel.generateNewCederman(person1, person2));
						person1.haveBaby();
						person2.haveBaby();
						System.out.println("Size: " + men.size());
					}
				}
			}
		}

		for (Cederman man : tempList)
			men.add(man);
	}

	public boolean mayConsume(Cederman person1, Cederman person2) {
		return (person1.getPartner() == person2 && person2.getPartner() == person1)
			|| (person1.getPartner() == null && person2.getPartner() == null);
	}

	public static void marry(Cederman parent1, Cederman parent2) {
		parent1.marry(parent2);
		parent2.marry(parent1);
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

		marry(parent1, parent2);

		return new Cederman(x, y, direction, newName, Color.blue, parent1, parent2);
	}

	public static String generateName() {
		int choice = (int)(Math.random() * 100) % Cederpanel.names.length;

		return Cederpanel.names[choice];
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0 ; i < men.size() ; i++){
			//world[men.get(i).getX()][men.get(i).getY()].place(None);
			men.get(i).doStuff();
			if (men.get(i).getAge() > 26000) {
				// ROLL FOR DEATH, EVERYONE COME AND PLAY
				if (Math.random()>0.99999) {
					men.remove(men.get(i));
				}
			}
		}
			//world[men.get(i).getX()][men.get(i).getY()].place(men.get(i));
		checkCollisions(); //

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);
		g.drawString(Integer.toString(men.size()), width-30, 10);

		for (int i = 0 ; i < men.size() ; i++)
			men.get(i).paint(g);
	}
}
