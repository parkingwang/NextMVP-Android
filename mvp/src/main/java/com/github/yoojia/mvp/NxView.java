/*
 *  Copyright (c) 2017. Xi'an iRain IoT Technology Service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.github.yoojia.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public interface NxView {

    void init(View container);
    void init(Activity activity);

    void onInit(View container);
    void onInit(Activity activity);

    Context getContext();
    Activity getActivity();
}
