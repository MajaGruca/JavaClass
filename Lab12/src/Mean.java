import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(128000000);
    }


    static class Odwracanie extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        Odwracanie(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            double sum=0;
            for(int i = start;i<end;i++)
            {
                sum+=array[i];
            }
            mean = sum/(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }


    }

    static void OdwracanieRown(int cnt) throws InterruptedException {
        Odwracanie threads[]=new Odwracanie[cnt];
        int width = array.length/cnt;
        for(int i=0;i<cnt;i++)
        {
            threads[i] = new Odwracanie(i*width,(i+1)*width);
        }
        double t1 = System.nanoTime()/1e6;
        for(Odwracanie mc:threads)
        {
            mc.start();
        }
        double t2 = System.nanoTime()/1e6;
        double mean = 0;
        for(int i=0;i<cnt;i++) {
            mean+=results.take();
        }
        mean=mean/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

}