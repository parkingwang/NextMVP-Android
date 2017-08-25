package com.github.yoojia.mvp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxBaseUiView extends NxBaseView implements NxUiView {

    private WeakReference<ProgressDialog> mWeakDialog;
    private Handler mTimeoutHandler;

    @Override
    final public void showProgress() {
        showProgressImpl("LOADING");
    }

    @Override
    final public void showProgress(String message) {
        showProgressImpl(message);
    }

    @Override
    final public void showProgress(int message) {
        showMessage(getContext().getString(message));
    }

    @Override
    final public void hideProgress() {
        if (mWeakDialog != null){
            ProgressDialog dialog = mWeakDialog.get();
            if (dialog != null) {
                dialog.cancel();
            }
            mWeakDialog.clear();
            mWeakDialog = null;
        }
    }

    @Override
    final public void hideProgress(int timeoutMs) {
        if (mTimeoutHandler == null) {
            mTimeoutHandler = new Handler();
        }
        mTimeoutHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgress();
            }
        }, timeoutMs);
    }

    protected void showProgressImpl(String message){
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        mWeakDialog = new WeakReference<>(dialog);
    }

    @Override
    final public void showMessage(int message) {
        showMessage(getContext().getString(message));
    }

    @Override
    final public void showMessage(String message) {
        showMessageImpl(message, false);
    }

    @Override
    final public void showMessageLong(String message) {
        showMessageImpl(message, true);
    }

    @Override
    final public void showMessageLong(int message) {
        showMessage(getContext().getString(message));
    }

    protected void showMessageImpl(String message, boolean isLong){
        Toast.makeText(getContext(), message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)
                .show();
    }
}
