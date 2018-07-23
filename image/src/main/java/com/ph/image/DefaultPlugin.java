package com.ph.image;

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

    @Override
    public Response load(String url) {
        Callable<Response> callable = new Task(dispatcher, url);
        Future<Response> future = new FutureTask<>(callable);
        dispatcher.enqueue(future);
        Response response = null;
        try {
            response = future.get();
        } catch (InterruptedException e) {
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            future.cancel(true);
        }
        return response;
    }

}
