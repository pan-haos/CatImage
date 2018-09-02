package com.ph.image.remote;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ph.image.DiskInterceptor;
import com.ph.image.IImageInterface;
import com.ph.image.Interceptor;
import com.ph.image.MemoryInterceptor;
import com.ph.image.NetWorkInterceptor;
import com.ph.image.RealChain;
import com.ph.image.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Auth：CatV
 * Project：CatImage
 * Time：18-9-2 15:22
 */
public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IImageInterface.Stub() {
            @Override
            public Bitmap remoteResponse(String url, int waitTimeOut) throws IOException {
                return remoteResponseWithConfig(url, waitTimeOut, RemoteConfig.LRU_MEMORY_SIZE, RemoteConfig.DISK_FILE_PATH);
            }

            @Override
            public Bitmap remoteResponseWithConfig(String url, int waitTimeOut, int memoryCacheSize, String diskFilePath) throws IOException {
                //在新进程创建的时候就应该初始化，在该进程内单例．此时的回调已经在Binder线程池中，耗时操作一旦放在异步必须阻塞
                List<Interceptor> interceptors = new ArrayList<>();
                interceptors.add(new MemoryInterceptor());
                interceptors.add(new DiskInterceptor());
                interceptors.add(new NetWorkInterceptor());

                Interceptor.Chain chain = new RealChain(interceptors, url, waitTimeOut, memoryCacheSize, diskFilePath);
                Target target = chain.get(url);
                Bitmap bitmap = target.getBitmap();
                return bitmap;
            }
        };
    }

}
