import java.awt.*;

public class House{
    private int x, y;
    private Cederman owner;

    public House(int x, int y, Cederman owner){
        this.x = x;
        this.y = y;
        this.owner = owner;
    }
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.drawRect(x, y, 3, 3);
    }
}
