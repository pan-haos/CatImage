package com.ph.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午11:14
 */
public class Policy {

    private Context context;
    final CatImage catImage;


    public Policy(CatImage catImage, Context context) {
        this.catImage = catImage;
        this.context = context;
    }


    /**
     * 这里的load()要想办法替换掉，准确来说通用的load应该返回一致的数据类型，根据这里的数据类型再转响应的转换
     * 到后面的into里再解析
     *
     * @param res
     * @return
     */

    public Source load(@DrawableRes int res) {
        return generate(Integer.class, res);
    }

    public Source load(Drawable drawable) {
        return generate(Drawable.class, drawable);
    }


    /**
     * 这里的load不应该立即就去网络加载图片,而是应该将信息存下来
     *
     * @param url
     * @return
     */
    public Source load(String url) {
        //优先判定是否为图片，再决定是否缓存查找
        if (!ImageUtil.isImage(url)) {
            throw new IllegalArgumentException("url is not a image url, can't load from...${uri}");
        }
        return generate(String.class, url);
        /*Target target = null;

        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new NetWorkInterceptor());
        interceptors.add(new MemoryInterceptor());


        for (Interceptor interceptor : interceptors) {
        }*/
        //TODO 可以刷一条责任链 这里的map可以采用LruCache来替代 责任链实现同一个接口　构建的思维抽象程度可以更高
        /*if (CatImage.urlCache.containsKey(url)) {
            target = CatImage.urlCache.get(url);
        } else {
            //TODO 去访问http网络缓存--目的为了看图片是否改变，是会产生网络交互，未改变火访问失败仍然去内存缓存拿，拿到结果要刷新内存和磁盘缓存
            //TODO 这里只是一段实例代码，并不完整
            boolean changed = false;
            if (changed) {
                //从网络上拿到图片，
                Response response = plugin.load(url);
                //drc
                target = new Target(context, response);
                CatImage.urlCache.put(url, target);
                //磁盘缓存写入
            } else {
                //TODO 去磁盘拿并且刷新内存缓存
                CatImage.urlCache.put(url, target);
            }
        }*/
    }

    private <T> Source<T> generate(Class<T> source, T data) {
        return new Source.Builder<T>()
                .context(context)
                .dest(source, data)
                .build();
    }

}
