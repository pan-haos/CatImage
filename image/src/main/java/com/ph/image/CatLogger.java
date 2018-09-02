package com.ph.image;

import android.util.Log;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-9-2 15:32
 */
public class CatLogger {
    public static final String TAG = "com.ph.catimage";

    public static void v(String msg) {
        Log.v(TAG, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }


}
