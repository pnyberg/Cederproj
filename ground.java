public class Ground {
	private final int x, int y;
	public boolean occupied;
	public String type;
	public Cederman isHerer;

	public Ground(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void place(what){
		this.x = what.x;
		this.y = what.y;
		isHerer = what;
	}
}