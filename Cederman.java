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
	private String name, goal;
	private Color color;
	private Cederman parent1, parent2, partner, familyLeader, heir;
	private House home;

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

	public void death(){
		if(this.heir != null){

		}
	}
	public void move(int xMin, int yMin, int xMax, int yMax) {
		if (this.direction == NORTH) {
			this.y -= (y > 0 ? step : 0);
		} else if (this.direction == EAST) {
			this.x += (x < xMax ? step : 0);
		} else if (this.direction == SOUTH) {
			this.y += (y < yMax ? step : 0);
		} else {
			this.x -= (x > 0 ? step : 0);
		}
		changeDirection();
	}
	private String createGoal(){
		//SHOULD take into account what the Cedermans status is
		String goal = "SOULSEARCH";
		return goal;
	}
	public World doStuff(World world) {

		//Do i have a goal?
		if (this.goal == null){
			//Get new goal
			this.goal = createGoal();
		}else{
			//Act on goal
			switch (this.goal) {
				case "EAT":
					//Goal is to find food for Cederman is hungry
					//For this to work we need a food resource
					break;
				case "SLEEP":
					//Do nothing for 6-8 cycles
					break;
				case "RAVE":
					break;
				case "REPEAT":
					break;
				case "MATE":
					//I WANT AN OFFSPRING
					break;
				case "BENDER":
					//Random desigins that can end up with impranation with other then partner or killing frinds
					break;
				case "SOULSEARCH":
					//Aimlessly wandering around kinda what is always done
					world.remove(this.x, this.y);
					move(0, 0, world.getXMax(), world.getYMax());
					world.place(this.x, this.y, this);
					break;
				default:
					this.goal = createGoal();
			}
		}
		//React to world

		/*
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
		*/
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
		if (home == null){
			home = new House(this.x, this.y, this);
		}
	}

	public void changeDirection() {
		this.direction = ((int)(Math.random() * 4));
		System.out.println(this.name);
		System.out.println(direction);
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

			this.direction = (4 + yDir + (((int)(Math.random() * 10)) % 2) * xDir) % 4;
			System.out.println(direction);
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
		if (this.home != null){
			home.paint(g);
		}
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

	public int getInfluence(){
		return influence;
	}
}
