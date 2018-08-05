package com.ph.image;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-8-5 17:30
 */
public interface Interceptor {

    /**
     * intercept the chain by the default way
     *
     * @param chain
     * @return
     */
    Target intercept(Chain chain);

    interface Chain {
        /**
         * get target obj form net or memory cache or disk cache
         * it's depend on cache rule
         *
         * @param url
         * @return
         */
        Target get(String url);

        Policy policy();

        String url();
    }
}
