package com.ph.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 17:43
 * <p>
 * judge if the network cache exit,if exit need request new bytes from net.
 * 这边只会根据url来获取结果
 */
public class NetWorkInterceptor implements Interceptor {
    @Override
    public Target intercept(Chain chain) {
        String url = chain.url();
        Policy policy = chain.policy();
        IPlugin plugin = policy.catImage.getPlugin();
        Response response = plugin.load(url);
        if (response == null) {
            //向上发出事件，设置加载失败
        } else {
            int code = response.respCode();
            if (code == 200) {
                InputStream stream = response.stream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                return new Target(response);
            }
        }


        return chain.get(url);
    }
}
