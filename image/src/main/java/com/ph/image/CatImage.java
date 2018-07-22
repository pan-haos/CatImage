package com.ph.image;

import android.content.Context;

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

    public static Policy context(Context context) {
        return new Policy(context);
    }



}
