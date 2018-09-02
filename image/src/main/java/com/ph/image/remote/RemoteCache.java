package com.ph.image.remote;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.LruCache;

import com.ph.image.DiskLruCache;
import com.ph.image.Target;

import java.io.File;
import java.io.IOException;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-9-2 15:46
 */
public class RemoteCache {

    private static volatile LruCache<String, Target> memoryCache;

    private static volatile DiskLruCache diskLruCache;

    private static final int KB = 1024;

    private RemoteCache() {
    }

    public static LruCache<String, Target> getMemoryCache(int cacheSize) {
        if (memoryCache == null) {
            synchronized (RemoteCache.class) {
                if (memoryCache == null) {
                    memoryCache = new LruCache<String, Target>(cacheSize) {
                        @Override
                        protected int sizeOf(String key, Target target) {
                            Bitmap bitmap = ((BitmapDrawable) target.getDrawable()).getBitmap();
                            return bitmap.getRowBytes() * bitmap.getHeight() / KB;
                        }
                    };
                }
            }
        }
        return memoryCache;
    }


    public static DiskLruCache getDiskLruCache(String filePath) throws IOException {
        if (diskLruCache == null) {
            synchronized (RemoteCache.class) {
                if (diskLruCache == null) {
                    diskLruCache = DiskLruCache.open(new File(filePath), 1, 1, 1);
                }
            }
        }
        return diskLruCache;
    }


}
