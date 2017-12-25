package tree;

import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Light implements XmasShape{
    int x;
    int y;
    double scale;
    Color lineColor =new Color(250,255,120);
    Color fillColor = new Color(250,255,200);
    Color lightColor = new Color(250,90,0);
    Light(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillRect(0,6,10,20);
        g2d.setColor(lineColor);
        g2d.drawRect(0,6,10,20);
        g2d.setColor(lightColor);
        QuadCurve2D q = new QuadCurve2D.Float();
        q.setCurve(5, 6,2, 3, 5, 0);
        g2d.fill(q);
        q.setCurve(5, 6,8, 3, 5, 0);
        g2d.fill(q);
        //g2d.draw(q);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-scale*5,y-scale*10);
        g2d.scale(scale,scale);
    }
}
