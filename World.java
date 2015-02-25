public class World {
	public int widht, height;
	private Ground gt;
	private Ground[][] world;

	public World(int widht, int height){
		this.world = generateWorld(widht, height);
	}

	private Ground[][] generateWorld(int widht, int height){
		Ground[][] world = new Ground[widht][height];
		for (int y=0 ; y < height ; y++) {
			for (int x=0 ; x < widht ; x++){
				world[x][y] = new Ground(x, y);
			}
		}
		return world;
	}

	public void place(int x, int y, Cederman man){
		this.world[x][y].place(man);
	}

	public void remove(int x, int y){
		this.world[x][y].remove();
	}

	public boolean check(int x, int y){
		try {
			if (x >= 0 && y >= 0){
				if (this.world[x][y].getMan() != null){
					return true;
				}
			}else{
				return false;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e){
			return false;
		}
		return false;
	}
	public Cederman getMan(int x, int y){
		return this.world[x][y].getMan();
	}
	public int getXMax(){
		return widht;
	}
	public int getYMax(){
		return height;
	}
}
