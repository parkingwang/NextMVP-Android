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
    public void start() {
        this.onStart();
    }

    @Override
    public void stop() {
        this.onStop();
    }

    @Override
    public void create() {
        this.onCreate();
    }

    @Override
    public void destroy() {
        this.onDestroy();
    }

    @Override
    public void onCreate() {
        // NOP
    }

    @Override
    public void onDestroy() {
        // NOP
    }

    @Override
    public void onStart() {
        // NOP
    }

    @Override
    public void onStop() {
        // NOP
    }

}