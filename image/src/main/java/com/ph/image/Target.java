package com.ph.image;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-23  下午5:49
 */
public class Target {

    Context context;
    Response response;
    Drawable drawable;

    public Target(Context context, Response response) {
        this.context = context;
        this.response = response;
    }

}
