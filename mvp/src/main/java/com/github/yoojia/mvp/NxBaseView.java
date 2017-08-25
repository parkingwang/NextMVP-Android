package com.github.yoojia.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxBaseView implements NxView {

    @Override
    public void init(View container) {

    }

    @Override
    public void init(Activity activity) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
