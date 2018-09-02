package com.ph.image;

import java.io.IOException;
import java.util.List;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-21 09:48
 */
public class RealChain implements Interceptor.Chain {
    private List<Interceptor> interceptors;
    private String url;
    private int waitTimeOut;
    private int memoryCacheSize;
    private String diskFilePath;

    public RealChain(List<Interceptor> interceptors, String url, int waitTimeOut, int memoryCacheSize, String diskFilePath) {
        this.interceptors = interceptors;
        this.url = url;
        this.waitTimeOut = waitTimeOut;
        this.memoryCacheSize = memoryCacheSize;
        this.diskFilePath = diskFilePath;
    }

    @Override
    public Target get(String url) throws IOException {
        Target target = null;
        for (Interceptor interceptor : interceptors) {
            target = interceptor.intercept(this);
        }
        return target;
    }

    @Override
    public String url() {
        return this.url;
    }

    @Override
    public int waitTimeOut() {
        return this.waitTimeOut;
    }

    @Override
    public int memoryCacheSize() {
        return this.memoryCacheSize;
    }

    @Override
    public String diskFilePath() {
        return this.diskFilePath;
    }

}
