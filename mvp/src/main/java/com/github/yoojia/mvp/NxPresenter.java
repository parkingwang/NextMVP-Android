/*
 *  Copyright (c) 2017. Xi'an iRain IoT Technology Service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.github.yoojia.mvp;

import android.app.Activity;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public interface NxPresenter<V extends NxView> extends NxContext {

    void onStart();
    void onStop();

    void onCreate();
    void onDestroy();

    V getView();

    <R extends Activity> R getActivity();
}
