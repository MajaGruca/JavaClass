package Zad2;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Revert {
    static int[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(100);
    static void initArray(int size){
        array = new int[size];
        for(int i=0;i<size;i++){
            array[i]= i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(100000000);
        OdwracanieRown(4);
    }


    static class Odwracanie extends Thread{
        private final int start;
        private final int end;

        Odwracanie(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i = 0;i<(end-start)/2;i++)
            {
                int temp = array[start+i];
                array[start+i]=array[end-1-i];
                array[end-1-i]=temp;
            }
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
        //results.take();
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1);
    }

}