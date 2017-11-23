/*
 *  Copyright (c) 2017. Xi'an iRain IoT Technology Service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.github.yoojia.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public interface NxPresenter<V extends NxView> {

    void onStart();
    void onStop();

    void onCreate();
    void onDestroy();

    V getView();

    <R extends Context> R getContext();

    <R extends Activity> R getActivity();

    <R extends Application> R getApplication();

}
