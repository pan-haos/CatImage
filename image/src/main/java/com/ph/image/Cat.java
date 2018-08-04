package com.ph.image;

import android.content.Context;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 01:37
 */
public class Cat {

    private static CatImage catImage;

    public static CatImage init(Context context) {
        catImage = new CatImage.Builder()
                .plugin(new DefaultPlugin())
                .build();
        return catImage;
    }


    public static Policy context(Context context) {
        if (catImage == null)
            throw new NullPointerException("has not init CatImage in your application");
        return catImage.context(context);
    }

    public void hellp() {
        Cat.context(null)
                .load("")
                .into(null);
    }

}
