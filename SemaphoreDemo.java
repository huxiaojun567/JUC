package JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC中的Semaphore（信号量）类 用于多个共享线程的互斥使用以及并发线程的数量控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类，假设有3个空车位
        for(int i = 1; i <= 6; i++){
                    new Thread(() -> {
                        try {
                            semaphore.acquire();//获取信号量，信号量减一
                            System.out.println(Thread.currentThread().getName() + "\t抢占车位");
                            TimeUnit.SECONDS.sleep(3);//车位占用3秒
                            System.out.println(Thread.currentThread().getName() + "\t离开车位");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            semaphore.release();//释放信号量，信号量加一
                        }
                    },String.valueOf(i)).start();
                }
    }


}
