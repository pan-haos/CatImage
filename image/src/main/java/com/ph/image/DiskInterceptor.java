package com.ph.image;

import com.ph.image.remote.RemoteCache;

import java.io.IOException;
import java.io.InputStream;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 18:57
 */
public class DiskInterceptor implements Interceptor {

    @Override
    public Target intercept(Chain chain) throws IOException {
        String url = chain.url();
        String diskFilePath = chain.diskFilePath();
        InputStream inputStream = RemoteCache.getDiskLruCache(diskFilePath).get(url).getInputStream(0);
        return chain.get(url);
    }

}
