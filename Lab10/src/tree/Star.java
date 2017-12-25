package tree;

import java.awt.*;

public class Star implements XmasShape{
    int x;
    int y;
    double scale;
    Color color =new Color(255,255,130);
    Star(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
    }
    Star(int x, int y, double s,Color c){
        this.x=x;
        this.y=y;
        scale=s;
        color=c;
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        int xPoints[] = {9,11,18,13,15,9,3,5,0,7};
        int yPoints[] = {0,6,6,10,16,12,16,10,6,6};
        g2d.fillPolygon(xPoints,yPoints,10);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-scale*9,y-scale*8);
        g2d.scale(scale,scale);
    }
}
