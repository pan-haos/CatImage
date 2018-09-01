package com.ph.image;

import java.io.IOException;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 18:57
 */
public class DiskInterceptor implements Interceptor {

    @Override
    public Target intercept(Chain chain) {
        DiskLruCache diskLruCache = chain.policy().catImage.getDiskLruCache();

        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(chain.url());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
