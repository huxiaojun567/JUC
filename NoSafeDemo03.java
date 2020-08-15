package JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 不安全的集合：
 *1.故障现象
 *  java.util.ConcurrentModificationException
 *
 *2.导致原因
 *  线程不安全，抢占资源
 *
 *3.解决方法
 *  3.1 new Vector()
 *  3.2 Collections.synchronizedList(new ArrayList<>())
 *  3.3  new CopyOnWriteArrayList()
 *4.优化建议
 */
public class NoSafeDemo03 {
    public static void main(String[] args) {
        MapNotSafe();
    }

    private static void MapNotSafe() {
        Map map = new ConcurrentHashMap();//new HashMap();
        for(int i = 1; i <= 30 ; i++){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,3));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void SetNotSafe() {
        Set set = new CopyOnWriteArraySet();//new HashSet();
        for(int i = 1; i <= 30 ; i++){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }


    private static void ListNotSafe() {
        List list = new CopyOnWriteArrayList(); //Collections.synchronizedList(new ArrayList<>());//new Vector();//new ArrayList();

        for(int i = 1; i <= 30 ; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
