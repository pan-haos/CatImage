package com.ph.image;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-7-22  下午10:55
 */
public class ImageUtil {

    public static boolean isImage(String uri) {
        return uri.endsWith(".png")
                || uri.endsWith(".jpg")
                || uri.endsWith(".jpeg")
                || uri.endsWith(".webp")
                || uri.endsWith(".svg");
    }

}
