package com.github.yoojia.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * @author 陈哈哈 chenyongjia@parkingwang, yoojiachen@gmail.com
 * @since 1.0.0
 */
public abstract class NxPresenterSimple<V extends NxView> implements NxPresenter<V> {

    private final V mView;

    public NxPresenterSimple(V view) {
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
    public <R extends Activity> R getActivity() {
        return getView().getActivity();
    }

    @Override
    public <R extends Context> R getContext() {
        return getView().getContext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends Application> R getApplication() {
        return (R) getContext().getApplicationContext();
    }

    //// Helper

    protected Runnable newTaskHideProgress() {
        return new NxUiViewRunnable<V>(mView) {
            @Override
            protected void onNxUiView(NxUiView view) {
                view.hideProgress();
            }
        };
    }

    protected Runnable newTaskShowProgress(final String message) {
        return new NxUiViewRunnable<V>(mView) {
            @Override
            protected void onNxUiView(NxUiView view) {
                view.showProgress(message);
            }
        };
    }

    protected Runnable newTaskShowProgress(final int message) {
        return new NxUiViewRunnable<V>(mView) {
            @Override
            protected void onNxUiView(NxUiView view) {
                view.showProgress(message);
            }
        };
    }

    //////

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

    ////

    private abstract class NxUiViewRunnable<V> implements Runnable {

        private final V mView;

        private NxUiViewRunnable(V view) {
            mView = view;
        }

        @Override
        final public void run() {
            if (mView instanceof NxUiView) {
                onNxUiView((NxUiView) mView);
            }
        }

        protected abstract void onNxUiView(NxUiView view);
    }

}