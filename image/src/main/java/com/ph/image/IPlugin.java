package com.ph.image;

/**
 * 作者：潘浩
 * 项目：CatImage
 * 时间：18-7-21  下午11:52
 */
public interface IPlugin {

    /**
     * load image from network
     *
     * @param uri the path of the image
     * @return a result of response
     */
    Response load(String uri);
}
