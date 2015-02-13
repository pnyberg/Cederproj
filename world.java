public class World {
	public final int size;
	private Ground gt; 
	private Ground[][] world;
	public World(int size){
		this.size = size;
		world = generateWorld(this.size)
	}

	private Ground[][] generateWorld(int size){
		for (int y=0;y < size;y++) {
			for (int x=0; x < size, x++){
				world[x][y] = new Ground(x, y);
			}
		}
	}
}