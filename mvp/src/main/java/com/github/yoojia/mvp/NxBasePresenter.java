package com.github.yoojia.mvp;

import android.content.Context;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public abstract class NxBasePresenter<V extends NxView> implements NxPresenter<V> {

    private final V mView;

    public NxBasePresenter(V view) {
        if (view == null) {
            throw new NullPointerException();
        }
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public Context getContext() {
        return getView().getContext();
    }

    @Override
    public void create() {
        // NOP
    }

    @Override
    public void destroy() {
        // NOP
    }

    @Override
    public void start() {
        // NOP
    }

    @Override
    public void stop() {
        // NOP
    }

}