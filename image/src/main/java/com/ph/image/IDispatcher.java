package com.ph.image;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  上午12:52
 */
public interface IDispatcher {


    /**
     * enqueue task to thread pool
     *
     * @param task
     */
    void enqueue(Future<?> task);


    /**
     * 当前任务执行结束
     *
     * @param callable
     */
    void finish(Callable<?> callable);

}
