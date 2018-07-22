package com.ph.image;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Autho：CatV
 * Project：CatImage
 * Time：18-7-22  上午1:04
 */
public class CPUDispatcher implements IDispatcher {

    /**
     * the max execute task in thread pool at the same time
     */
    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

    private ExecutorService cpuExecutor = new ThreadPoolExecutor(CORE_SIZE, CORE_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

    @Override
    public void enqueue(Future<?> task) {
        cpuExecutor.submit((FutureTask<?>) task);
    }

}
