package com.ph.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-23  下午5:49
 */
public class Target{

     Context context;
     Response response;
     Drawable drawable;

    public Target(Context context, Response response) {
        this.context = context;
        this.response = response;
    }

    public void into(ImageView imageView) {
        InputStream stream = response.stream();
        Bitmap bmp = BitmapFactory.decodeStream(stream);
        imageView.setImageBitmap(bmp);
    }
}
