package com.lhj.java.thread.future;

/**
 * @author 17822
 */
public interface Future<T> {

    public T get() throws InterruptedException;

}
