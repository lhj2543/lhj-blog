package com.lhj.java.thread.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * ThreadPoolExecutor 线程池
 *
 * @author 17822
 */
public class ThreadPoolExectorTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * ThreadPoolExecutor 线程池 构造函数参数说明:
         * @param corePoolSize
         * 核心线程池大小。这个参数是否生效取决于allowCoreThreadTimeOut变量的值，该变量默认是false，即对于核心线程没有超时限制，所以这种情况下，corePoolSize参数是起效的。
         * 如果allowCoreThreadTimeOut为true，那么核心线程允许超时，并且超时时间就是keepAliveTime参数和unit共同决定的值，这种情况下，如果线程池长时间空闲的话最终存活的线程会变为0，也即corePoolSize参数失效
         * @param maximumPoolSize 当workQueue 超出 corePoolSize 就会启用maximumPoolSize
         * 线程池中最大的存活线程数。这个参数比较好理解，对于超出corePoolSize部分的线程，无论allowCoreThreadTimeOut变量的值是true还是false，都会超时，超时时间由keepAliveTime和unit两个参数算出
         * @param keepAliveTime 超时时间 当线程数大于核心数量(corePoolSize)，这是多余空闲线程的最长时间将在终止前等待新任务
         * @param unit 超时时间的单位，秒，毫秒，微秒，纳秒等，与keepAliveTime参数共同决定超时时间
         * @param workQueue
         * 当调用execute方法时，如果线程池中没有空闲的可用线程，那么就会把这个Runnable对象放到该队列中。这个参数必须是一个实现BlockingQueue接口的阻塞队列，因为要保证线程安全。
         * 有一个要注意的点是，只有在调用execute方法是，才会向这个队列中添加任务，那么对于submit方法呢，难道submit方法提交任务时如果没有可用的线程就直接扔掉吗？当然不是，
         * 看一下AbstractExecutorService类中submit方法实现，其实submit方法只是把传进来的Runnable对象或Callable对象包装成一个新的Runnable对象，然后调用execute方法，
         * 并将包装后的FutureTask对象作为一个Future引用返回给调用者。Future的阻塞特性实际是在FutureTask中实现的
         * @param threadFactory 用什么方式创建线程
         * 线程工厂类。用于在需要的时候生成新的线程。默认实现是Executors.defaultThreadFactory()，即new 一个Thread对象，并设置线程名称，daemon等属性。
         * @param handler 线程池对拒绝任务的处理策略 当线程数量大于workQueue+maximumPoolSize 就会被拒绝策略接管
         * 这个参数的作用是当提交任务时既没有空闲线程，任务队列也满了，这时候就会调用handler的rejectedExecution方法。默认的实现是抛出一个RejectedExecutionException异常。
         */

        BlockingQueue<Runnable> ArrayBlockingQueue = new ArrayBlockingQueue<Runnable>(2);

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                //设置守护线程,当线程任务超时时,可以中断所有线程任务
                t.setDaemon(true);
                return t;
            }
        };

        //拒绝策略:
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,30, TimeUnit.SECONDS,ArrayBlockingQueue,threadFactory,abortPolicy);

        myTask myTask = new myTask(5,"任务1");
        threadPoolExecutor.execute(myTask);
        myTask myTask2 = new myTask(2,"任务2");
        threadPoolExecutor.execute(myTask2);
        myTask myTask3 = new myTask(2,"任务3");
        threadPoolExecutor.execute(myTask3);
        myTask myTask4 = new myTask(2,"任务4");
        threadPoolExecutor.execute(myTask4);

        //非阻塞(空闲线程会停止,非空闲线程标记为停止,等到线程任务执行完了后停止)
        //threadPoolExecutor.shutdown();

        List<Runnable> runnables =null;
        try{
            //等待运行中的线程运行玩,然后停止线程,返回队列中等待的线程,队列中等待的线程会结束掉
            runnables = threadPoolExecutor.shutdownNow();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("队列中等待的线程数量BlockingQueue="+runnables.size()+"被停止");

        //等待所有线程任务结束
        //threadPoolExecutor.awaitTermination(1,TimeUnit.HOURS);

        
        System.out.println("===============结束====================");

        TimeUnit.MICROSECONDS.sleep(10);

        int activeCount_a=-1;
        int size_a=-1;

        while (true){
            //获取线程池活跃的数量
            int activeCount = threadPoolExecutor.getActiveCount();
            //获取等待的线程队列
            BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
            int size = queue.size();
            if(activeCount_a!=activeCount || size!=size_a){
                System.out.println("当前活跃线程数量activeCount="+activeCount+";当前等待线程数量:queue="+size);
                activeCount_a = activeCount;
                size_a = size;
                System.out.println("=========================");
            }
        }


    }

}

 class myTask implements  Runnable{
    int sleep;
    String name;
    int i;
     myTask(int sleep,String name){
         this.sleep = sleep;
         this.name = name;
     }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + name +" runing....");
        try {
            IntStream.range(0,100000000).forEach(i->{
               i++;
            });
            //TimeUnit.SECONDS.sleep(sleep);
        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName()+name+"被终止..error="+e.getMessage());
        }
    }

}
