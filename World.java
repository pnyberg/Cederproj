public class World {
	public final int size;
	private Ground gt;
	private Ground[][] world;

	public World(int size){
		this.size = size;
		this.world = generateWorld(this.size);

	}

	private Ground[][] generateWorld(int size){
		Ground[][] world = new Ground[size][size];
		for (int y=0 ; y < size ; y++) {
			for (int x=0 ; x < size ; x++){
				world[x][y] = new Ground(x, y);
			}
		}
		System.out.println("Yo a world has bean created");
		return world;
	}
}
