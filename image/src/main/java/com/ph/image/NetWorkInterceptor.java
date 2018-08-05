package com.ph.image;

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
        Response load = plugin.load(url);




        return chain.get(url);
    }
}
