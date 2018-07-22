package com.ph.image;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：潘浩
 * 项目：CatImage
 * 时间：18-7-21  下午11:56
 */
public class IODispatcher implements IDispatcher {

    private int maxRequest = 32;

    private volatile ThreadPoolExecutor ioExecutor;

    private Queue<Future<?>> waitingQueen = new ArrayDeque<>();

    private AtomicInteger runningCoreSize;

    public ExecutorService getIOExecutor() {
        if (ioExecutor == null) {
            synchronized (this) {
                if (ioExecutor == null) {
                    ioExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                }
            }
        }
        return ioExecutor;
    }

    @Override
    public void enqueue(Future<?> task) {
        if (runningCoreSize.get() < maxRequest) {
            runningCoreSize.incrementAndGet();
            getIOExecutor().execute((FutureTask<?>) task);
        } else {
            waitingQueen.add(task);
        }

    }

    @Override
    public void finish(Callable<?> callable) {
        runningCoreSize.decrementAndGet();
        handlerWaitingQueen();
    }

    private void handlerWaitingQueen() {
        if (runningCoreSize.get() >= maxRequest) return;
        if (waitingQueen.isEmpty()) return;

        for (int i = 0; i < waitingQueen.size(); i++) {
            runningCoreSize.incrementAndGet();
            getIOExecutor().execute((FutureTask<?>) waitingQueen.remove());
            if (runningCoreSize.get() >= maxRequest) return;
        }
    }


}
