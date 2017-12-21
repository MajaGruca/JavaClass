package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public void addBubble(Bubble b){
        shapes.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    DrawPanel(){
        setBackground(new Color(187,239,239));
        Bubble b1 = new Bubble(20,5, 1);
        addBubble(b1);
        b1 = new Bubble(4,3, 2, new Color(55,23,99),new Color(120,160,180));
        addBubble(b1);
    }
}
