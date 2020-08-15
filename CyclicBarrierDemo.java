package JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * CyclicBarrier 类与CountDountLatch 类似
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("***集齐7颗龙珠，召唤神龙");
        });//执行7次线程以后才会运行cyclicBarrier中定义的Runnable的内容

        for(int i = 1; i <= 7; i++){
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "获取到第" +  tempInt+ "颗龙珠");
                try {
                    cyclicBarrier.await();//线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }


    }


}
