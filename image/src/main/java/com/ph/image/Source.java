package com.ph.image;

import android.widget.ImageView;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-26 21:28
 * 框架的特点：
 * <p>
 * 1.支持跨进程调用(可以在同一个进程内也可以在不同的进程内)
 * 2.支持三级缓存
 * 3.支持注解(Runtime/class)
 * <p>
 * <p>
 * 输入内容：
 * 1.url:  从网路获取到图片的字节(流)
 * id :  根据id获取到图片的字节(流)
 * file:  根据本机文件获取图片的字节流
 * <p>
 * 2.imageView　根据imageView可以根据其中的参数来对图片进行设置
 * <p>
 * 3.activity/fragment　根据传入的ac/fr来对声明周期进行控制
 * <p>
 * 支持取消/中断　task
 */
public class Source implements Cloneable {
    /**
     * 首先必须有一个类持有imageView，但是这个类不能是静态的
     * <p>
     * 　url不同
     * 　imageview　不同
     * activity　不同
     *
     * @param imageView
     */


    public void into(ImageView imageView) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        //TODO　按顺序来说:先配置好参数　- >　设置好加载时机和机制　 - > 去特定的地方加载
        //跨进程加载


    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Source();
    }


}
