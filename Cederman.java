import java.awt.*;

public class Cederman {
	private final int 	NORTH = 0,
						EAST = 1,
						SOUTH = 2,
						WEST = 3;
	private final int size = 1;
	private final int step = 1;
	private int direction;
	private int x, y, age, babyCountdown;
	private String name;
	private Color color;
	private Cederman parent1, parent2;

	public Cederman(int x, int y, int direction, String name, Color color, Cederman parent1, Cederman parent2) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.name = name;
		this.color = color;
		this.parent1 = parent1;
		this.parent2 = parent2;

		babyCountdown = 0;
	}

	public void move() {
		// Ska skrivas om så den tar i betrakktning världen
		if (direction == NORTH) {
			y -= step;
		} else if (direction == EAST) {
			x += step;
		} else if (direction == SOUTH) {
			y += step;
		} else {
			x -= step;
		}

		changeDirection();
	}
	public void fightYeBastards(){

	}
	public void doStuff() {
		move();
		age++;
		if (babyCountdown > 0)
			babyCountdown--;

//		System.out.println(name);
	}

	public void haveBaby() {
		babyCountdown = 20;
	}

	public void changeDirection() {
		direction = (direction + 1 + (int)(Math.random() * 10) % 3) % 4;
	}

	public void paint(Graphics g) {
		/*g.setColor(Color.red);
		g.fillOval(x y size size);
		g.setColor(Color.black);
		g.drawOval(x y size size);
		*/
		if (babyCountdown > 0)
			g.setColor(Color.green);
		else
			g.setColor(color);
		g.drawRect(x, y, size, size);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isParent(Cederman person) {
		return person == parent1 || person == parent2;
	}

	public boolean isSibling(Cederman person) {
		if (parent1 == null || parent2 == null) {
			return false;
		}
		return person.isParent(parent1) || person.isParent(parent2);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirection() {
		return direction;
	}

	public int getBabyCountdown() {
		return babyCountdown;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
	public int getAge(){
		return age;
	}
}
