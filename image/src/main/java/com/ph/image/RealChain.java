package com.ph.image;

import java.util.List;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-21 09:48
 */
public class RealChain implements Interceptor.Chain {
    List<Interceptor> interceptors;

    public RealChain(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Target get(String url) {
        Target target = null;
        for (Interceptor interceptor : interceptors) {
            target = interceptor.intercept(this);
        }
        return target;
    }

    @Override
    public Policy policy() {
        return null;
    }

    @Override
    public String url() {
        return null;
    }
}
