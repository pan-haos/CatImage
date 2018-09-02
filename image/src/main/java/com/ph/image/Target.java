package com.ph.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-23  下午5:49
 */
public class Target {

    private Bitmap bitmap;
    private Drawable drawable;

    public Target(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Drawable getDrawable() {
        return drawable;
    }

}
