public class World {
	public int widht, height;
	private Ground gt;
	private Ground[][] world;

	public World(int widht, int height){
		this.world = generateWorld(widht, height);

	}

	private Ground[][] generateWorld(int widht, int height){
		System.out.println(Integer.toString(widht));
		System.out.println(Integer.toString(height));
		Ground[][] world = new Ground[widht][height];
		for (int y=0 ; y < height ; y++) {
			for (int x=0 ; x < widht ; x++){
				world[x][y] = new Ground(x, y);
			}
		}
		System.out.println("Yo a world has bean created");
		return world;
	}
	public void place(int x, int y, Cederman man){
		System.out.println(Integer.toString(x));
		System.out.println(Integer.toString(y));
		this.world[man.getX()][man.getY()].remove();
		this.world[x][y].place(man);
	}
}
