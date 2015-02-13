import java.awt.*;

public class Cederman {
	private final int 	NORTH = 0,
						EAST = 1,
						SOUTH = 2,
						WEST = 3;

	private final int size = 20;
	private final int step = 5;
	private int direction;
	private int x, y;

	public Cederman(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void move() {
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

	public void changeDirection() {
		direction = (direction + (int)(Math.random() * 10) % 3) % 4;
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, size, size);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
	}
} 