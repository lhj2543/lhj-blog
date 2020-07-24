package com.lhj.java.thread;

import java.util.stream.Stream;

/**
 * wait() notify() notifyAll()
 *
 * sleep() 和 wait() 区别
 * 1：sleep 是 Thread的方法，wait 是Object的方法
 * 2：sleep和wait都是休眠作用，sleep指定休眠时间后会自动唤醒，而wait必须通过notify或notifyAll来唤醒。（除wait(1000),设置的休眠时间外）
 * 3：sleep不会释放锁，wait会释放锁,wait被notify唤醒后仍然需要获取锁
 * 4: wait() notify() 必须有 synchronized 不然会报错
 * 生产者和消费者案例
 * @author 17822
 */
public class ThreadWaitNotify {

    //共享数据
    private  int i;

    //是否生产了数据
    private volatile boolean isProduct = false;

    //对象锁
    private final Object LOCK = new Object();

    //生产者
    public void product(){
        synchronized (LOCK){
            while (isProduct){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            isProduct  = true;
            LOCK.notifyAll();
            System.out.println(Thread.currentThread().getName()+"生产了数据："+i);
        }
    }

    //消费者
    public void  consume(){
        synchronized (LOCK){
            while(!isProduct){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+"消费了数据："+i);
            isProduct = false;
            LOCK.notifyAll();
        }
    }

    public static void main(String[] args) {

        ThreadWaitNotify wn = new ThreadWaitNotify();

        Stream.of("p1","p2","p3").forEach(val->{
            new Thread(val){
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        wn.product();
                    }
                }
            }.start();
        });

        Stream.of("c1","c2","c3").forEach(val->{
            new Thread(val){
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        wn.consume();
                    }
                }
            }.start();
        });



    }


}
