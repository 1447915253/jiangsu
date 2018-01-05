import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by 123 on 2016/10/14.
 */
public class ThreadTest {

    @Test
    public void demo1(){
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("1111111111");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            t.start();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t.isAlive());
            System.out.println(t.getState());
            t.join();
            System.out.println("-------------------");
            System.out.println(t.isAlive());
            System.out.println(t.getState());
            System.out.println("over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo2(){
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("22222222");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("InterruptedException");
                }
            }
        };
        try {
            t.start();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(t.isAlive());
            System.out.println(t.getState());
//            t.interrupt();
            t.join();
            System.out.println("-------------------");
            System.out.println(t.isAlive());
            System.out.println(t.getState());
            System.out.println("over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
