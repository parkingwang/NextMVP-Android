package com.github.yoojia.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxViewSimple extends NxContextSimple
        implements NxView {

    @Override
    public void onInit(View container) {

    }

    @Override
    public <T extends Activity> void onInit(T activity) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends Context> R getContext() {
        return (R) (getActivity());
    }

}
