package com.ph.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.RemoteException;
import android.util.LruCache;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-20 09:49
 * <p>
 * the real worker to load img from local memory cache or remote memory/disk/cache and network.
 * we use the four grades of cache to make sure it can provide a powerful cache so need't always
 * get result from network and avoid local memory not enough for it
 */
public class Loader {

    private static volatile Loader loader;

    private static final int LRU_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

    private static final int KB = 1024;

    private static LruCache<String, Target> localMemoryCache;

    private Loader() {
        localMemoryCache = new LruCache<String, Target>(LRU_SIZE) {
            @Override
            protected int sizeOf(String key, Target target) {
                Bitmap bitmap = ((BitmapDrawable) target.getDrawable()).getBitmap();
                return bitmap.getRowBytes() * bitmap.getHeight() / KB;
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

    public Bitmap load(Context context, String url, IImageInterface imageInterface) {
        Bitmap bitmap = null;
        Target target = localMemoryCache.get(url);
        if (target == null) {
            try {
                // put it to local process's memory cache
                bitmap = imageInterface.remoteResponse(url, 30);
                target = new Target(bitmap);
                localMemoryCache.put(url, target);
            } catch (RemoteException e) {
                CatLogger.e("CatImage on RemoteTransform has exception" + e.getMessage());
            }
        }
        return bitmap;
    }

}
