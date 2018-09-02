package com.ph.image;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.LruCache;

import com.ph.image.remote.RemoteService;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 01:37
 */
public class Cat {

    private static CatImage catImage;

    private static IImageInterface iImageInterface;

    /**
     * 在Application加载会比较好，将磁盘中的内容加入到内存
     * 两种方案: 1.在application初始化的时候就开始将全部的磁盘缓存读入内存
     * 后面只需要直接和内存交互                                                           有
     * 　　　　                                                          ｜-->磁盘找
     * 2.按需加载．例如当前的url对应的图片，内存缓存没找到－>http缓存是否有(是否有发生改变)－－－｜ 无
     * (三级缓存)                                                        ｜-->网络找
     * build with default params
     *
     * @return
     */
    public static CatImage init(Application application) {
        checkIsBind(application);

        catImage = new CatImage.Builder()
                .plugin(new DefaultPlugin())
                .imageInterface(iImageInterface)
                .build();
        return catImage;
    }

    /**
     * build with your params
     *
     * @param application
     * @param plugin
     * @param memoryCache
     * @return
     */
    public static CatImage init(Application application, IPlugin plugin, LruCache<String, Target> memoryCache) {
        checkIsBind(application);

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


    private static void checkIsBind(Application application) {
        boolean bindService = application.bindService(new Intent(application, RemoteService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iImageInterface = IImageInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iImageInterface = null;
            }
        }, Context.BIND_AUTO_CREATE);

        if (!bindService) {
            throw new IllegalStateException("can't bind to image process");
        }
        if (iImageInterface == null) {
            throw new NullPointerException("can't get binder to connect with image process");
        }
    }


}
