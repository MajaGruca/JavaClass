import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BouncingBallsPanel extends JPanel {


    AnimationThread thread = new AnimationThread();

    static class Ball{
        int x;
        int y;
        int r;
        double vx;
        double vy;
        Color color;
        Color white=new Color(255,255,255);
        Color black=new Color(0,0,0);

        public void paint(Graphics g)
        {
            g.setColor(white);
            g.fillOval(x-r,y-r,2*r, 2*r);
            g.setColor(black);
            g.drawOval(x-r,y-r,2*r, 2*r);
        }

        Ball(){
            x=20;
            y=20;
            r=15;
            vx=1;
            vy=1;
        }
    }

    List<Ball> balls = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Ball n: balls){
            n.paint(g2d);
        }
    }

    class AnimationThread extends Thread{
        boolean suspend=true;
        synchronized void wakeup(){
            suspend=false;
            notify();
        }
        void safeSuspend(){
            suspend=true;
        }
        public void run(){
            for(;;) {
                for (Ball n : balls) {
                    synchronized (n) {
                        n.x += n.vx;
                        n.y += n.vy;
                        detectCollision(n);
                    }
                }
                repaint();
                synchronized (this) {
                    try {
                        if (suspend) {
                            wait();
                        }
                    } catch (InterruptedException e) {}
                }
            }
        }
    }

    void detectCollision(Ball b) {
        Dimension d=getSize();
        if(b.x<b.r){b.x=b.r;b.vx*=-1;}
        if(b.x>d.width-b.r){b.x=d.width-b.r;b.vx*=-1;}
        if(b.y<b.r){b.y=b.r;b.vy*=-1;}
        if(b.y>d.height-b.r){b.y=d.height-b.r;b.vy*=-1;}
    }


    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        thread.start();
    }

    void onStart() {
        System.out.println("Start or resume animation thread");
        thread.wakeup();
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        thread.safeSuspend();
    }

    void onPlus(){
        Ball b = new Ball();
        balls.add(b);
        System.out.println("Add a ball");
    }

    void onMinus(){
        if(balls.size()>0) {
            balls.remove(balls.size() - 1);
            System.out.println("Remove a ball");
        }
        else
        {
            System.out.println("No balls to be removed");
        }
    }
}