/*
 *  Copyright (c) 2017. Xi'an iRain IoT Technology Service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.github.yoojia.mvp;

import android.content.Context;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public interface NxPresenter<V extends NxView> {

    void onStart();
    void onStop();

    @Deprecated
    void start();

    @Deprecated
    void stop();

    void onCreate();
    void onDestroy();

    @Deprecated
    void create();

    @Deprecated
    void destroy();

    V getView();

    Context getContext();

}
