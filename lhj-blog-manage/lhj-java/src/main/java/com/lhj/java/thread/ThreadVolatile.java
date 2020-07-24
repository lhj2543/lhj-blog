package com.lhj.java.thread;

/**
 * volatile关键字
 * 一个变量被volatile修饰
 *  1:保证了不同线程间的可见性
 *  2：禁止对其进行重排序，也就是保证了有序性
 *  3:不能保证一致性：
 *  volatile修饰的变量，对一个变量的写操作先于对变量的读操作
 *
 *  volatile 使用场景
 *  1：状态量标记
 *  volatile boolean flag = ture;
 *  2:屏障前后的一致性
 * @author 17822
 */
public class ThreadVolatile {

    private static volatile int i = 0;


    public static void main(String[] args) throws InterruptedException {

        new Thread("t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    System.out.println(this.getName()+"读取数据：="+i);
                }
            }
        }.start();

        Thread.sleep(1000);

        i++;

        new Thread("t2"){
            @Override
            public void run() {
                while (true){
                    i++;
                    try {
                        System.out.println("数据写完：i="+i);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

}
