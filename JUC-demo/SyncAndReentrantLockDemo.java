package JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author plancer16
 * @create 2020-04-20 20:35
 */
//三个线程打印5,10,15个数，重复10轮
class sharedResource{
    private int number=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void print5(){
        lock.lock();
        try
        {
            //1.判断
            while (number!=1){
                c1.await();
            }
            //2.干活
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            number=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try
        {
            while (number!=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try
        {
            while (number!=3){
                c3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        sharedResource sharedResource = new sharedResource();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                sharedResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                sharedResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                sharedResource.print15();
            }
        },"C").start();
    }
}
