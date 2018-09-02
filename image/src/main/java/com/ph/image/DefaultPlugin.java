package com.ph.image;

import android.graphics.Bitmap;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午11:21
 */
public class DefaultPlugin implements IPlugin {

    private IDispatcher dispatcher = new IODispatcher();
    private IDispatcher cpuDispatcher = new CPUDispatcher();

    @Override
    public Bitmap load(String url) {
        Callable<Bitmap> callable = new Task(dispatcher, url);
        Future<Bitmap> future = new FutureTask<>(callable);
        dispatcher.enqueue(future);
        Bitmap bitmap = null;
        try {
            bitmap = future.get();
        } catch (InterruptedException e) {
            future.cancel(true);
        } catch (ExecutionException e) {
            future.cancel(true);
        }
        return bitmap;
    }

}
