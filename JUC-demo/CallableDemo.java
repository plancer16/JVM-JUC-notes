package JUC;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CallableDemo.java

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
class MyThread
        implements Callable
{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}

public class CallableDemo
{

    public CallableDemo()
    {
    }

    public static void main(String args[])
        throws ExecutionException, InterruptedException
    {
        //FutureTask(Callable<V> callable)
        FutureTask futureTask = new FutureTask(new MyThread());//futureTask的参数实现callable，才能有返回值
        Thread t1 = new Thread(futureTask, "AA");//线程的参数要实现Runnable
        t1.start();
        int result01 = 100;
        int result02 = ((Integer)futureTask.get()).intValue();
        System.out.println((new StringBuilder()).append("result:").append(result01 + result02).toString());
    }


}
