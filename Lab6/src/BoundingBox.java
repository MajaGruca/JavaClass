public class BoundingBox {
    double xmin=Double.NaN;
    double ymin=Double.NaN;
    double xmax=Double.NaN;
    double ymax=Double.NaN;

    void addPoint(double x, double y){
        if(!this.isEmpty()) {
            if (xmin > x)
                xmin = x;
            if (ymin > y)
                ymin = y;
            if (xmax < x)
                xmax = x;
            if (ymax < y)
                ymax = y;
        }
        else
        {
            xmin=x;
            xmax=x;
            ymin=y;
            ymax=y;
        }
    }


    boolean contains(double x, double y){
        if(x>=xmin && x<=xmax && y>=ymin && y<=ymax)
            return true;
        else
            return false;
    }


    boolean contains(BoundingBox bb){
        if(this.contains(bb.xmin,bb.ymax) && this.contains(bb.xmax,bb.ymin))
            return true;
        else
            return false;
    }


    boolean intersects(BoundingBox bb){
        if(this.contains(bb.xmin,bb.ymin)||this.contains(bb.xmin,bb.ymax)||this.contains(bb.xmax,bb.ymin)||this.contains(bb.xmax,bb.ymax)||
                bb.contains(this.xmin,this.ymin)||bb.contains(this.xmin,this.ymax)||bb.contains(this.xmax,this.ymin)||bb.contains(this.xmax,this.ymax))
        {
            return true;
        }
        else
            return false;
    }


    BoundingBox add(BoundingBox bb){
        if(!this.contains(bb))
        {
            this.addPoint(bb.xmin,bb.ymin);
            this.addPoint(bb.xmax,bb.ymax);
        }
        return this;
    }



    boolean isEmpty(){
        if(Double.isNaN(xmin)) //wystarczy sprawdzic jedna wartosc
            return true;
        else
            return false;
    }


    double getCenterX(){
        if(!this.isEmpty())
        {
            return (xmin+xmax)/2;
        }
        else
        {
            throw new NullPointerException("Bounding Box is empty");
        }
    }

    double getCenterY(){
        if(!this.isEmpty())
        {
            return (ymin+ymax)/2;
        }
        else
        {
            throw new NullPointerException("Bounding Box is empty");
        }
    }

    double distanceTo(BoundingBox bbx){
        double dist=0;
        if(!this.isEmpty() && !bbx.isEmpty())
        {
            dist = Haversine.distance(this.getCenterY(),this.getCenterX(), bbx.getCenterY(),bbx.getCenterX());
        }
        else
        {
            throw new NullPointerException("Bounding Box is empty");
        }
        return dist;
    }

    public String toString() {
        String s="P1: ("+xmin+" , "+ymin+")\nP2: ("+xmax+" , "+ymax+")";
        return s;
    }

}
