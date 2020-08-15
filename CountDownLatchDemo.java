package JUC;

import java.util.concurrent.CountDownLatch;

/**
 * JUC中的CountDownLatch类中的方法可以控制线程的执行顺序
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);//设置countDownLatch 计数器计数的数字
        for(int i = 1;i <= 5;i++){
            new Thread(() -> {
                System.out.println( Thread.currentThread().getName()+ "离开教室");
                countDownLatch.countDown();//计数器减一
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//拦截线程
        System.out.println(Thread.currentThread().getName() + "班长关门");
    }
}
