public class Ground {
	private final int x, y;
	public boolean occupied;
	public String type;
	public Cederman man;

	public Ground(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void place(Cederman man){
		this.man = man;
	}
	public void remove(){
		this.man = null;
	}

	public Cederman getMan(){
		return this.man;
	}
}
