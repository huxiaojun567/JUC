package JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类：票 Ticket 实例变量+实例方法
 */
class Ticket{
    private int number = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "票，还剩下" + number + "张票");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
/**
 *
 * 线程的状态：创建 new，就绪 Runnable，运行，阻塞 blocked，等待 WAITING，死亡 TERMINATED
 */




/**
 *
 * 三个售票员  卖出  30张票
 *
 * 问：如何编程企业级的多线程代码？
 *  1. 在高内聚低耦合的前提下，线程  操作  资源类
 *      1.1 先创建资源类
 *
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {//主线程，程序的入口

        Ticket ticket = new Ticket();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0 ; i <= 40 ; i++){
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0 ; i <= 40 ; i++){
//                    ticket.sale();
//                }
//
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0 ; i <= 40 ; i++){
//                    ticket.sale();
//                }
//            }
//        }, "C").start();
//       Thread.State;
        new Thread(() -> {for (int i = 0 ; i <= 40 ; i++) ticket.sale();},"A").start();
        new Thread(() -> {for (int i = 0 ; i <= 40 ; i++) ticket.sale();},"B").start();
        new Thread(() -> {for (int i = 0 ; i <= 40 ; i++) ticket.sale();},"C").start();

    }
}
