package com.ph.image;

interface IImageInterface {
    /**
     * get the bitmap form other process with url
     */
    Bitmap remoteResponse(String url,int waitTimeOut);

    /**
     * get the bitmap form other process with url and set the lrucache size and disklrucache size
     */
    Bitmap remoteResponseWithConfig(String url, int waitTimeOut, int memoryCacheSize,  String diskFilePath);

}




