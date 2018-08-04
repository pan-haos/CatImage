package com.ph.image;

import android.content.Context;
import android.support.annotation.DrawableRes;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午11:14
 */
public class Policy {

    private Context context;
    private CatImage catImage;
    private IPlugin plugin;


    public Policy(CatImage catImage, Context context) {
        this.catImage = catImage;
        this.context = context;
    }


    public Target load(String url) {
        //优先判定是否为图片，再决定是否缓存查找
        if (!ImageUtil.isImage(url)) {
            throw new IllegalArgumentException("url is not a image url, can't load from...${uri}");
        }
        Target target = null;

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
        return target;
    }

    public Target load(@DrawableRes int res) {
        return null;
    }
}
