package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//定义资源类
class Print{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try{
            while(number != 1){
                c1.await();
            }
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            c2.signal();
        }catch(Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            while(number != 2){
                c2.await();
            }
            for(int i = 1; i <= 10; i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            c3.signal();
        }catch(Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            while(number != 3){
                c3.await();
            }
            for(int i = 1; i <= 15; i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            c1.signal();
        }catch(Exception e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }


}

/**
 * 多线程循环实现（使用lock锁）
 * A打印5次，B打印10次，C打印15次  10轮
 * 按照模板来
 */
public class ConditionDemo {
    public static void main(String[] args) {

        Print print = new Print();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                print.print5();
            }
        },"A").start();

        new Thread(() -> {
            for(int i = 1; i<= 10; i++) {
                print.print10();
            }
        },"B").start();

        new Thread(() -> {
            for(int i = 1; i<= 10; i++) {
                print.print15();
            }
        },"C").start();

    }
}
