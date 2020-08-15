package JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("****come in callable****");
        return 1024;
    }
}
/**
 * Callable 接口是第三种实现多线程的方法
 * Callable 接口实现的线程具有返回值
 * FutureTask中的get()方法可以得到线程执行的返回值，一般放在主线程的最后
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask implements Runnable接口与Callable接口
        FutureTask futureTask = new FutureTask<Integer>(new MyThread());

        new Thread(futureTask,"A").start();

        System.out.println("计算完成...");

        System.out.println(futureTask.get());
    }

}
