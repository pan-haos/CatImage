package com.ph.image;

import android.content.Context;
import android.util.LruCache;

import java.util.HashMap;
import java.util.Map;

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

    private static final int LRU_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

    public static final Map<String, Target> urlCache = new HashMap<>();

    public static final LruCache<String, Target> LRU_CACHE = new LruCache<>(LRU_SIZE);


    /**
     * 在Application加载会比较好，将磁盘中的内容加入到内存
     * 两种方案: 1.在application初始化的时候就开始将全部的磁盘缓存读入内存
     * 后面只需要直接和内存交互                                                            有
     * 　　　　                                                          ｜-->磁盘找
     * 2.按需加载．例如当前的url对应的图片，内存缓存没找到－>http缓存是否有(是否有发生改变)－－－｜ 无
     * (三级缓存)                                                                  ｜-->网络找
     */
    public static void init() {
    }


    /**
     * 愁绪难别雨花落，秋风难宿人离别
     * 一段情，何故萦绕至如今
     *
     * @param context
     * @return
     */
    public static Policy context(Context context) {
        return new Policy(context);
    }


}
