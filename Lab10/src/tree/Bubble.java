package tree;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor =new Color(99,10,10);
    Color fillColor = new Color(159,10,10);
    Bubble(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
    }
    Bubble(int x, int y, double s,Color lc, Color fc)
    {
        this.x=x;
        this.y=y;
        scale=s;
        lineColor = lc;
        fillColor = fc;
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(0,0,10,10);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0,10,10);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-scale*5,y-scale*5);
        g2d.scale(scale,scale);
    }
}