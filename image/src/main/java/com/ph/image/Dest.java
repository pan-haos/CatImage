package com.ph.image;

/**
 * Author：CatV
 * Project：CatImage
 * Time：18-8-19 23:00
 */
public class Dest<T> {

    Class<T> source;
    T data;

    public Dest(Class<T> source, T data) {
        this.source = source;
        this.data = data;
    }
}
