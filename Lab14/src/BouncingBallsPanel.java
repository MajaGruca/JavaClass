import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    List<Ball> balls = new ArrayList<>();
    AnimationThread thread = new AnimationThread();

    static class Ball{
        int x;
        int y;
        int r=15;
        double vx;
        double vy;
        Color color;
        Color black=new Color(0,0,0);

        public void paint(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(color);
            g2d.fillOval(x-r,y-r,2*r, 2*r);
            g2d.setColor(black);
            g2d.drawOval(x-r,y-r,2*r, 2*r);
        }

        Ball(){
            Random rand = new Random();
            x=15+rand.nextInt(270);
            y=15+rand.nextInt(270);
            vx=1+rand.nextDouble()*10;
            vy=1+rand.nextDouble()*10;
            color=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Ball n: balls){
            n.paint(g);
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
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        for(int i=0;i<5;i++)
        {
            Ball b = new Ball();
            balls.add(b);
        }
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