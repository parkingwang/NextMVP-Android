/*
 *  Copyright (c) 2017. Xi'an iRain IoT Technology Service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.github.yoojia.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public interface NxView {

    void onInit(View container);

    <T extends Activity> void onInit(T activity);

    <R extends Context> R getContext();

    <R extends Activity> R getActivity();

    <R extends Application> R getApplication();
}
