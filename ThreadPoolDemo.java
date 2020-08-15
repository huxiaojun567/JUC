package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池Executor
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //ExecutorService executorService1 = new ThreadPoolExecutor()

        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t处理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
