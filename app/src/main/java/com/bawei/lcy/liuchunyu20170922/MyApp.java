package com.bawei.lcy.liuchunyu20170922;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by LCY on 2017/9/22.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //默认加载图片
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
