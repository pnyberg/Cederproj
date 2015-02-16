import java.awt.*;

public class Cederman {
	private final int 	NORTH = 0,
						EAST = 1,
						SOUTH = 2,
						WEST = 3;
	private final int size = 1;
	private final int step = 1;
	private int direction;
	private int x, y, age, babyCountdown, childrens, influence;
	private String name;
	private Color color;
	private Cederman parent1, parent2, partner, familyLeader, heir;

	public Cederman(int x, int y, int direction, String name, Color color, Cederman parent1, Cederman parent2) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.name = name;
		this.color = color;
		this.parent1 = parent1;
		this.parent2 = parent2;

		this.heir = null;
		partner = null;
		familyLeader = null;
		influence = 0;
		babyCountdown = 0;
	}

	public void move(int xMin, int yMin, int xMax, int yMax) {
		if (direction == NORTH) {
			this.y -= (y > 0 ? step : 0);
		} else if (direction == EAST) {
			this.x += (x < xMax ? step : 0);
		} else if (direction == SOUTH) {
			this.y += (y < yMax ? step : 0);
		} else {
			this.x -= (x > 0 ? step : 0);
		}
		changeDirection();
	}

	public World doStuff(int xMin, int yMin, int xMax, int yMax, World world) {
		world.remove(this.x, this.y);
		move(xMin, yMin, xMax, yMax);
		world.place(this.x, this.y, this);

		if(familyLeader == this){
			color = Color.orange;
			leadFamily();
		}
		if (babyCountdown > 0)
			babyCountdown--;
		age++;
		return	world;
	}

	public void marry(Cederman partner) {
		this.partner = partner;
		color = Color.white;
		if (familyLeader == null){
			if (this.partner.getFamilyLeader() != null) {
				familyLeader = this.partner.getFamilyLeader();
			}else{
				familyLeader = this;
			}
		}else if(getFamilyLeader().influence < this.partner.getFamilyLeader().influence){
			familyLeader = this.partner.getFamilyLeader();
		}
	}

	public void haveBaby() {
		babyCountdown = 800;
		influence++;
	}

	private void leadFamily(){
		//Behöver hem
	}

	public void changeDirection() {
		direction = ((int)(Math.random() * 4));

		if (partner != null && notCloseEnoughToPartner()) {
			int xDir = 0;
			int yDir = 0;

			if (partner.getX() > x) {
				xDir = 1;
			} else {
				xDir = -1;
			}

			if (partner.getY() > y) {
				yDir = 2;
				xDir *= -1;
			}

			direction = (4 + yDir + (((int)(Math.random() * 10)) % 2) * xDir) % 4;
		}
	}

	private boolean notCloseEnoughToPartner() {
		double x = Math.pow(this.x - partner.getX(), 2);
		double y = Math.pow(this.y - partner.getY(), 2);
		return Math.sqrt(x + y) >= 10;
	}

	public void paint(Graphics g) {
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
		if (age > 5500 && babyCountdown <= 0 && childrens <= 5){
			return true;
		}else{
			return false;
		}
	}

	public void setHeir(Cederman heir){
		if (this.heir == null) {
			this.heir = heir;
		}
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

	public Cederman getPartner() {
		return partner;
	}

	public Cederman getFamilyLeader() {
		return familyLeader;
	}
}
