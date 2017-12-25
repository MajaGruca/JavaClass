package tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public void addBubble(Bubble b){
        shapes.add(b);
    }
    public void addBranch(Branch b) {shapes.add(b);}
    public void addLight(Light b) {shapes.add(b);}
    public void addStar(Star b) {shapes.add(b);}
    public void addTrunk(Trunk b) {shapes.add(b);}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    DrawPanel(){
        int RootX=300;
        int RootY=400;
        int i=0;
        setBackground(new Color(0,55,85));
        Random gen = new Random();
        Star star = new Star(gen.nextInt(1000), gen.nextInt(800), gen.nextDouble());
        addStar(star);
        while(i<50) {
            star = new Star(gen.nextInt(1000), gen.nextInt(800), gen.nextDouble());
            addStar(star);
            i++;
        }
        Trunk trunk = new Trunk(RootX,RootY,1);
        addTrunk(trunk);
        Branch branch = new Branch(RootX,RootY,6);
        addBranch(branch);
        branch=new Branch(RootX,RootY-50,5);
        addBranch(branch);
        branch=new Branch(RootX,RootY-100,4);
        addBranch(branch);
        branch=new Branch(RootX,RootY-140,3);
        addBranch(branch);
        branch=new Branch(RootX,RootY-180,2);
        addBranch(branch);
        Bubble bubble = new Bubble(RootX+40,RootY-10, 1);
        addBubble(bubble);
        bubble = new Bubble(RootX+100,RootY-20, 1.2);
        addBubble(bubble);
        bubble = new Bubble(RootX+55,RootY-60, 1.33);
        addBubble(bubble);
        bubble = new Bubble(RootX-55,RootY-100, 1.1);
        addBubble(bubble);
        bubble = new Bubble(RootX-14,RootY-84, 1.3);
        addBubble(bubble);
        bubble = new Bubble(RootX-40,RootY-30, 1.2);
        addBubble(bubble);
        bubble = new Bubble(RootX+37,RootY-180, 1.1);
        addBubble(bubble);
        bubble = new Bubble(RootX-20,RootY-150, 1.15);
        addBubble(bubble);
        Color f = new Color(120,160,180);
        Color o = new Color(40,100,140);
        bubble = new Bubble(RootX-99,RootY-48, 1.3, o,f);
        addBubble(bubble);
        bubble = new Bubble(RootX+30,RootY-120, 1.2, o,f);
        addBubble(bubble);
        bubble = new Bubble(RootX-10,RootY-20, 1.3, o,f);
        addBubble(bubble);
        bubble = new Bubble(RootX-15,RootY-195, 1.1, o,f);
        addBubble(bubble);
        f = new Color(150,90,180);
        o = new Color(60,20,100);
        bubble = new Bubble(RootX+30,RootY-160, 1.2, o,f);
        addBubble(bubble);
        bubble = new Bubble(RootX-80,RootY-15, 1.1, o,f);
        addBubble(bubble);
        f = new Color(210,220,55);
        o = new Color(160,140,0);
        bubble = new Bubble(RootX-40,RootY-130, 1.2, o,f);
        addBubble(bubble);
        bubble = new Bubble(RootX+20,RootY-50, 1.3, o,f);
        addBubble(bubble);
        Light light = new Light(RootX-123,RootY-13,1);
        addLight(light);
        light = new Light(RootX+100,RootY-63,1);
        addLight(light);
        light = new Light(RootX-80,RootY-113,1);
        addLight(light);
        light = new Light(RootX+60,RootY-153,0.9);
        addLight(light);
        light = new Light(RootX-40,RootY-193,0.8);
        addLight(light);
        f =new Color(255,230,0);
        star = new Star(RootX,RootY-220,2, f);
        addStar(star);
    }
}
