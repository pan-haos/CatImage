package com.ph.image;

import android.content.Context;
import android.support.annotation.DrawableRes;

/**
 * Autho：CatV
 * Project：CatImage
 * Time：18-7-22  下午11:14
 */
public class Policy {

    private Context context;
    private static IPlugin iPlugin;

    public Policy(Context context) {
        this.context = context;
    }

    public static void plugin(IPlugin plugin) {
        iPlugin = plugin;
    }

    public static Response load(String url) {
        //优先判定是否为图片，再决定是否解析域名之类
        if (!ImageUtil.isImage(url)) {
            throw new IllegalArgumentException("url is not a image url, can't load from...${uri}");
        }
        return iPlugin.load(url);
    }

    public static void load(@DrawableRes int res) {

    }
}
