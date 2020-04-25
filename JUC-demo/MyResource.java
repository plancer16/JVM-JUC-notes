// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProdConsumer_BlockQueueDemo.java

import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{

    public MyResource(BlockingQueue blockingQueue)
    {
        FLAG = true;
        atomicInteger = new AtomicInteger();
        this.blockingQueue = null;
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()
        throws Exception
    {
        String data = null;
        while(FLAG) 
        {
            data = (new StringBuilder()).append(atomicInteger.incrementAndGet()).append("").toString();
            boolean retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue)
                System.out.println((new StringBuilder()).append(Thread.currentThread().getName()).append("\t \u63D2\u5165\u961F\u5217").append(data).append("\u6210\u529F").toString());
            else
                System.out.println((new StringBuilder()).append(Thread.currentThread().getName()).append("\t \u63D2\u5165\u961F\u5217").append(data).append("\u5931\u8D25").toString());
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println((new StringBuilder()).append(Thread.currentThread().getName()).append("\t FLAG=false,\u751F\u4EA7\u505C\u6B62").toString());
    }

    public void myConsumer()
        throws Exception
    {
        String result = null;
        while(FLAG) 
        {
            result = (String)blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase(""))
            {
                FLAG = false;
                System.out.println((new StringBuilder()).append(Thread.currentThread().getName()).append("\t \u8D85\u8FC72\u79D2\u6CA1\u6709\u53D6\u5230\uFF0C\u6D88\u8D39\u9000\u51FA").toString());
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println((new StringBuilder()).append(Thread.currentThread().getName()).append("\t \u6D88\u8D39\u961F\u5217").append(result).append("\u6210\u529F").toString());
        }
    }

    public void stop()
        throws Exception
    {
        FLAG = false;
    }

    private volatile boolean FLAG;
    private AtomicInteger atomicInteger;
    BlockingQueue blockingQueue;
}
