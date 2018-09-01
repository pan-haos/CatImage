package com.ph.image;

import android.util.LruCache;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 17:46
 * <p>
 * judge if the
 */
public class MemoryInterceptor implements Interceptor {

    @Override
    public Target intercept(Chain chain) {
        LruCache<String, Target> memoryCache = chain.policy().catImage.getMemoryCache();
        Target target = memoryCache.get(chain.url());
        return target != null ? target : chain.get(chain.url());
    }
}
