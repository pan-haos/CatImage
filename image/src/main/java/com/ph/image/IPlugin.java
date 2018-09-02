package com.ph.image;

import android.graphics.Bitmap;

/**
 * 作者：潘浩
 * 项目：CatImage
 * 时间：18-7-21  下午11:52
 * Plugin 只负责网络加载图片的模块，通过实现plugin接口可以来实现对网络框架的切换
 * 可以通过引入OkHttp等网络框架来执行网络对图片的加载操作，这样可以方便地实现底层库的切换
 * 在项目中可以保持网络框架的一致性
 */
public interface IPlugin {

    /**
     * load image from network
     *
     * @param url the path of the image
     * @return a result of bitmap
     */
    Bitmap load(String url);
}
