package com.lhj.java.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Executors 工具类 使用Executors创建线程池可能会导致OOM(OutOfMemory ,内存溢出)
 * @author 17822
 */
public class ExecutorsTest {

    public static void main(String[] args) {

        /**
         * newCachedThreadPool
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         * 它是一个可以无限扩大的线程池；它比较适合处理执行时间比较小的任务；
         * corePoolSize为0，maximumPoolSize为无限大，意味着线程数量可以无限大；
         * keepAliveTime为60S，意味着线程空闲时间超过60S就会被杀死；
         * 采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。
         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0,1000).forEach(i->{
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"===="+i);
                try {
                    TimeUnit.MICROSECONDS.sleep(i);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        });

        /**
         * FixedThreadPool
         * new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         * 它是一种固定大小的线程池；corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
         * keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；但这里keepAliveTime无效；
         * 阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(100);//设置100个核心线程,也就是同一时间最多可以100个线程工作

        /**
         * SingleThreadExecutor
         * new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         * 它只会创建一条工作线程处理任务；
         * 采用的阻塞队列为LinkedBlockingQueue；
         */
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        /**
         * ScheduledThreadPool 它用来处理延时任务或定时任务
         *
         * 它接收SchduledFutureTask类型的任务，有两种提交任务的方式：
         * 1.scheduledAtFixedRate
         * 2.scheduledWithFixedDelay
         *
         * SchduledFutureTask接收的参数：
         * 1.time：任务开始的时间
         * 2.sequenceNumber：任务的序号
         * 3.period：任务执行的时间间隔
         *
         * 它采用DelayQueue存储等待的任务
         * DelayQueue内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
         * DelayQueue也是一个无界队列；
         *
         * 工作线程的执行过程：
         * 工作线程会从DelayQueue取已经到期的任务去执行；
         * 执行结束后重新设置任务的到期时间，再次放回DelayQueue
         */



    }

}
