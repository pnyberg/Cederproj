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
	private String name, finalLastWords;
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
	public String death(){
		finalLastWords=
		"Jag har dött, denna ve och fasa, så hemskt!\n"+
		"Goodbye crule and hearthless world, well not crule yet\n"+
		"but it will come\n"+
		"trust me\n"+
		"Fear me CEDERMANS\n"+
		"for I am not per your beloved god of love\n"+
		"I am Ceder, bringer of death\n";
		return finalLastWords;
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
		//BLOOD FOR THE BLOOD GOD AND SKULLS FOR HIS SKULL THRONE
	}
	public void doStuff() {
		move();
		age++;
		if (babyCountdown > 0)
			babyCountdown--;

//		System.out.println(name);
	}

	public void haveBaby() {
		babyCountdown = 270;
	}

	public void changeDirection() {
		// MATTE 0.0 till 1.0 gånnger 10 = 0 till 10 modolu 4? modelu 5?
		// VAFAN värkar komplicerat
		// direction = (direction + 1 + (int)(Math.random() * 10) % 3) % 4; VARD DET
		direction = ((int)(Math.random() * 4));
		// Detta borde räcka?
		// JUPP tidigate tester anser att det räcker
		// HEJ PER, din matte var för komplicerad!
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
	public boolean canGiveBirth(){
		if (age > 5500 && babyCountdown <= 0){
			return true;
		}else{
			return false;
		}
	}


// Dumma get funktioner som inte behövs i python!



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
