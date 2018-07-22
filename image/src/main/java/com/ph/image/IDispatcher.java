package com.ph.image;

import java.util.concurrent.Future;

/**
 * Autho：CatV
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


}
