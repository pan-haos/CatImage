package com.ph.image;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 17:43
 * <p>
 * judge if the network cache exit,if exit need request new bytes from net.
 * 这边只会根据url来获取结果
 * <p>
 * 现在的问题在于一些类的配置，是直接在主进程中配置传过来还是在自己子进程中配置
 */
public class NetWorkInterceptor implements Interceptor {
    @Override
    public Target intercept(Chain chain) throws IOException {
        String url = chain.url();
        //plugin在这里才生效，在aidl接口里传类名可能会比较好
        IPlugin plugin = new DefaultPlugin();
        Bitmap bitmap = plugin.load(url);
        return new Target(bitmap);
    }
}
