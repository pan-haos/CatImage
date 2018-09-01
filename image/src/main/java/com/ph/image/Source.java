package com.ph.image;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

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
public class Source<T> implements Cloneable {

    final Dest<T> dest;
    final Context context;
    private Loader loader;

    public Source(Builder<T> builder) {
        this.dest = builder.dest;
        this.context = builder.context;

    }

    /**
     * 首先必须有一个类持有imageView，但是这个类不能是静态的
     * <p>
     * 　url不同
     * 　imageview　不同
     * activity　不同
     * <p>
     * into　应该进入一个构建好了BitmapInfo和url/res/drawable的类中
     *
     * @param imageView
     */


    public void into(ImageView imageView) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        //TODO　按顺序来说:先配置好参数　- >　设置好加载时机和机制　 - > 去特定的地方加载
        ImageView.ScaleType scaleType = imageView.getScaleType();
        int baseline = imageView.getBaseline();
        boolean baselineAlignBottom = imageView.getBaselineAlignBottom();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            boolean cropToPadding = imageView.getCropToPadding();
            boolean adjustViewBounds = imageView.getAdjustViewBounds();
            ColorFilter colorFilter = imageView.getColorFilter();
            CharSequence accessibilityClassName = imageView.getAccessibilityClassName();
        }
        //参数这里配置好?
        into(width, height, imageView);
    }

    private void into(int width, int height, ImageView imageView) {
        //现在要去加载图片了,而且是根据不同的内容去转换
        if (dest.source.equals(Integer.class)) {
            Integer res = (Integer) dest.data;
            imageView.setImageResource(res);
        } else if (dest.source.equals(Drawable.class)) {
            Drawable drawable = (Drawable) dest.data;
            imageView.setImageDrawable(drawable);
        } else if (dest.source.equals(File.class)) {
            File file = (File) dest.data;
            imageView.setImageURI(Uri.fromFile(file));
        } else if (dest.source.equals(String.class)) {
            String url = (String) dest.data;
            Drawable drawable = Loader.getLoader().load(url);
            imageView.setImageDrawable(drawable);
        }
    }

    public static class Builder<V> {

        private Dest<V> dest;
        private Context context;

        public Builder<V> dest(Dest<V> dest) {
            this.dest = dest;
            return this;
        }

        public Builder<V> dest(Class<V> source, V data) {
            this.dest = new Dest<>(source, data);
            return this;
        }

        public Builder<V> context(Context context) {
            this.context = context;
            return this;
        }

        public Source<V> build() {
            return new Source<>(this);
        }

    }
}
