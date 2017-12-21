package com.company;

import java.awt.*;

public class Branch {
    int x;
    int y;
    double scale;
    Color color =new Color(40,160,30);;
    Branch(int x, int y, double s){
        this.x=x;
        this.y=y;
        scale=s;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawPolygon(int[] xPoints, int[] yPoints, int nPoints);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
