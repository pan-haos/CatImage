package com.ph.image;

import android.content.Context;
import android.util.LruCache;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 01:37
 */
public class Cat {

    private static CatImage catImage;

    /**
     * 在Application加载会比较好，将磁盘中的内容加入到内存
     * 两种方案: 1.在application初始化的时候就开始将全部的磁盘缓存读入内存
     * 后面只需要直接和内存交互                                                            有
     * 　　　　                                                          ｜-->磁盘找
     * 2.按需加载．例如当前的url对应的图片，内存缓存没找到－>http缓存是否有(是否有发生改变)－－－｜ 无
     * (三级缓存)                                                                  ｜-->网络找
     * build with default params
     *
     * @return
     */
    public static CatImage init() {
        catImage = new CatImage.Builder()
                .plugin(new DefaultPlugin())
                .build();
        return catImage;
    }

    /**
     * build with your params
     *
     * @param plugin
     * @param memoryCache
     * @return
     */
    public static CatImage init(IPlugin plugin, LruCache<String, Target> memoryCache) {
        catImage = new CatImage.Builder()
                .plugin(plugin)
                .memoryCache(memoryCache)
                .build();
        return catImage;
    }


    public static Policy context(Context context) {
        if (catImage == null) {
            throw new NullPointerException("has not init CatImage in your application");
        }
        return catImage.context(context);
    }

}
