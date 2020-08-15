package JUC;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Product{
    private int number = 0;

    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void produce() throws Exception{
//        while( number != 0){
//            this.wait();
//        }
//        number++;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        this.notify();
           lock.lock();
           try{
               while(number != 0) {
                   condition.await();
               }
               number++;
               System.out.println(Thread.currentThread().getName() + "\t" + number);
              condition.signalAll();
           }catch(Exception e) {
               e.printStackTrace();
           } finally{
               lock.unlock();
           }
    }

    public  void buy() throws Exception{
//        while( number == 0){
//            this.wait();
//        }
//        number--;
//        System.out.println(Thread.currentThread().getName() + "\t" + number);
//        this.notify();
        lock.lock();
        try{
            while (number == 0){
               condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        }catch(Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}

/**
 * 生产者消费者问题：有两个线程，可以操作初始值为零的一个变量，实现一个线程对
 * 该变量加1，一个线程实现对变量减1，交替10轮
 *
 *  1. 高内聚，低耦合前提下，线程操作资源类
 *  2. 先判断，在实现内容，最后通知
 *  3. 需要防止虚假唤醒（改用 if(等待结束后获得锁时不会再重新进行判断) 为 while）
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) {

        Product product = new Product();


            new Thread(() -> {
                for (int i = 0; i <= 10; i++) {
                    try {
                        product.produce();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者A").start();

            new Thread(() -> {
                for (int i = 0; i <= 10; i++) {
                    try {
                        product.buy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    product.produce();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "生产者C").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    product.buy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者D").start();

        }
    }



