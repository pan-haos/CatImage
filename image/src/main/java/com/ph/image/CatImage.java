package com.ph.image;

import android.support.annotation.DrawableRes;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午10:45
 * <p>
 * Input arguments : url    imageView    context
 * <p>
 * outPut Result : Drawable  InputStream   bytes
 */
public class CatImage {

    public static void load(String url) {
        //优先判定是否为图片，再决定是否解析域名之类
        if (!ImageUtil.isImage(url)) {
            throw new IllegalArgumentException("url is not a image,can't load from...${uri}");
        }

    }

    public static void load(@DrawableRes int res) {

    }

}
