package com.ph.image;

import android.graphics.drawable.Drawable;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-20 09:49
 */
public class Loader {

    private static volatile Loader loader;

    private static final int LRU_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

    private static final int KB = 1024;

    private static LruCache<String, Drawable> memoryCache;

    private Loader() {
        memoryCache = new LruCache<String, Drawable>(LRU_SIZE) {
            @Override
            protected int sizeOf(String key, Drawable target) {
//                return target.getRowBytes() * target.getHeight() / KB;
                return 0;
            }
        };

    }

    public static Loader getLoader() {
        if (loader == null) {
            synchronized (Loader.class) {
                if (loader == null) {
                    loader = new Loader();
                }
            }
        }
        return loader;
    }

    public Drawable load(String url) {
        Interceptor.Chain chain = new RealChain(url);

        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new MemoryInterceptor());
        interceptors.add(new NetWorkInterceptor());
        interceptors.add(new DiskInterceptor());

        chain.get(url);

        //TODO 可以刷一条责任链 这里的map可以采用LruCache来替代 责任链实现同一个接口　构建的思维抽象程度可以更高
        /*if (CatImage.urlCache.containsKey(url)) {
            target = CatImage.urlCache.get(url);
        } else {
            //TODO 去访问http网络缓存--目的为了看图片是否改变，是会产生网络交互，未改变火访问失败仍然去内存缓存拿，拿到结果要刷新内存和磁盘缓存
            //TODO 这里只是一段实例代码，并不完整
            boolean changed = false;
            if (changed) {
                //从网络上拿到图片，
                Response response = plugin.load(url);
                //drc
                target = new Target(context, response);
                CatImage.urlCache.put(url, target);
                //磁盘缓存写入
            } else {
                //TODO 去磁盘拿并且刷新内存缓存
                CatImage.urlCache.put(url, target);
            }
        }*/
        return null;
    }

}
