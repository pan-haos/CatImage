package com.ph.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import java.io.File;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午11:14
 */
public class Policy {

    private final Context context;
    private final CatImage catImage;


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

    public Source load(File file) {
        return generate(File.class, file);
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
    }

    private <T> Source<T> generate(Class<T> source, T data) {
        return new Source.Builder<T>()
                .context(context)
                .dest(source, data)
                .build();
    }

}
