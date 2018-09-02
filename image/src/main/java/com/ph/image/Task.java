package com.ph.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  上午12:15
 */
public class Task implements Callable<Bitmap> {

    private IDispatcher dispatcher;
    private String url;

    public Task(IDispatcher dispatcher, String url) {
        this.dispatcher = dispatcher;
        this.url = url;
    }

    @Override
    public Bitmap call() throws IOException {
        // TODO 通过httpUrlConnection来获取的InputStream
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (!connection.getDoOutput()) {
            connection.connect();
        }

        int responseCode = connection.getResponseCode();
        Bitmap bitmap = null;
        if (responseCode == 200) {
            //successful
            InputStream inputStream = connection.getInputStream();
            //TODO 把inputStream 封装到response或者其它特定地类里去
            bitmap = BitmapFactory.decodeStream(inputStream);
        } else if (responseCode >= 100 && responseCode < 200) {

        } else if (responseCode > 200 && responseCode < 300) {

        } else if (responseCode >= 300 && responseCode < 400) {
            //重定向之类
        } else if (responseCode >= 400 & responseCode < 500) {
            //resource not found
        } else if (responseCode >= 500) {
            //err
        } else {
            //don't know
        }
        dispatcher.finish(this);
        return bitmap;
    }

}
