package tree;

import java.awt.*;

public class Branch implements XmasShape{
    int x;
    int y;
    double scale;
    Color color1 =new Color(40,160,30);
    Color color2 =new Color(30,110,30);
    Branch(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
}

    @Override
    public void render(Graphics2D g2d) {
        int[] xPoints = {20,40,0};
        int[] yPoints = {0,20,20};
        g2d.setColor(color1);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(color2);
        g2d.drawPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-scale*20,y-scale*20);
        g2d.scale(scale,scale);
    }
}
