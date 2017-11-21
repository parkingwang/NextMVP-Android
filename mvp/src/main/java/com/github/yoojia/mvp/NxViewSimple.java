package com.github.yoojia.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxViewSimple implements NxView {

    @Override
    public void init(View container) {
        this.onInit(container);
    }

    @Override
    public void init(Activity activity) {
        this.onInit(activity);
    }

    @Override
    public void onInit(View container) {

    }

    @Override
    public void onInit(Activity activity) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
