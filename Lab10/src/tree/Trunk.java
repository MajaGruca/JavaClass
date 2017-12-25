package tree;

import java.awt.*;

public class Trunk implements XmasShape{
    int x;
    int y;
    double scale;
    Color color =new Color(80,45,0);
    Trunk(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        int xPoints[] = {0,8,40,48};
        int yPoints[] = {60,0,0,60};
        g2d.fillPolygon(xPoints,yPoints,4);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-scale*24,y);
        g2d.scale(scale,scale);
    }
}
