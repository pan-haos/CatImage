package com.ph.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.LruCache;

import java.io.File;
import java.io.IOException;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午10:45
 * <p>
 * Input arguments : url    imageView    context
 * <p>
 * outPut Result : Drawable  InputStream   bytes
 * <p>
 * 整体思路：按照　输入--->输出的方式，先建立一套流程，即通过输入得到最终的输出结果，
 * 再根据中间过程查看能改进的地方
 * <p>
 * IO----从网络获取/本地图片
 * <p>
 * url是否需要做缓存----必须缓存，否则同一张图片多次加载，占用不必要的IO 内存缓存/磁盘缓存
 * url 也需要一份内存缓存,目的在内存操作时，直接加载
 * 磁盘缓存什么时候加载的问题
 */
public class CatImage {

    private final LruCache<String, Target> memoryCache;

    private final IPlugin plugin;

    public CatImage() {
        this(new Builder());
    }

    public CatImage(Builder builder) {
        this.memoryCache = builder.memoryCache;
        this.plugin = builder.plugin;
    }

    public IPlugin getPlugin() {
        return plugin;
    }

    public LruCache<String, Target> getMemoryCache() {
        return memoryCache;
    }

    /**
     * 两种处理方式：
     * １.load那个地方修改不立即去下载，因为此时还不清楚bitmap的参数
     * <p>
     * 2.这里直接修改返回对象，然后在从这个对象调用load的时候加载Target
     *
     * @param context
     * @return
     */
    public Policy context(Context context) {
        return new Policy(this, context);
    }

    public static class Builder {

        private static final int LRU_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

        private File diskLruCacheDir;

        private static final int KB = 1024;

        private LruCache<String, Target> memoryCache;

        private DiskLruCache diskCache;

        private IPlugin plugin;

        public Builder() {
            this.memoryCache = new LruCache<String, Target>(LRU_SIZE) {
                @Override
                protected int sizeOf(String key, Target target) {
                    Bitmap bitmap = ((BitmapDrawable) target.drawable).getBitmap();
                    return bitmap.getRowBytes() * bitmap.getHeight() / KB;
                }
            };
            try {
                this.diskCache = DiskLruCache.open(diskLruCacheDir, 1, 1, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.plugin = new DefaultPlugin();
        }

        public Builder plugin(IPlugin plugin) {
            if (plugin != null) {
                this.plugin = plugin;
            }
            return this;
        }

        public Builder memoryCache(LruCache<String, Target> memoryCache) {
            if (memoryCache != null) {
                this.memoryCache = memoryCache;
            }
            return this;
        }

        public Builder diskCache(DiskLruCache diskCache) {
            if (diskCache != null) {
                this.diskCache = diskCache;
            }
            return this;
        }


        public CatImage build() {
            //如果没有手动赋值，那么就设为默认的plugin
            if (this.plugin == null) {
                plugin = new DefaultPlugin();
            }
            return new CatImage(this);
        }

    }


}
