package com.ph.image;

import com.ph.image.remote.RemoteCache;

import java.io.IOException;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 17:46
 * <p>
 * judgement if memoryCache has contains this url, Otherwise get it from the chain
 */
public class MemoryInterceptor implements Interceptor {

    @Override
    public Target intercept(Chain chain) throws IOException {
        String url = chain.url();
        int memoryCacheSize = chain.memoryCacheSize();

        Target target = RemoteCache.getMemoryCache(memoryCacheSize).get(url);
        if (target == null) {
            // get target form chain and add it to memoryCache
            target = chain.get(url);
            RemoteCache.getMemoryCache(memoryCacheSize).put(url, target);
        }
        return target;
    }
}
