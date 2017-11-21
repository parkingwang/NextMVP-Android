package com.github.yoojia.mvp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxUiViewSimple extends NxViewSimple implements NxUiView {

    private WeakReference<ProgressDialog> mWeakDialog;
    private Handler mTimeoutHandler;

    @Override
    final public void showProgress() {
        onShowProgressImpl("LOADING");
    }

    @Override
    final public void showProgress(String message) {
        onShowProgressImpl(message);
    }

    @Override
    final public void showProgress(int message) {
        onShowProgressImpl(getMessageText(message));
    }

    @Override
    final public void hideProgress() {
        if (mWeakDialog != null) {
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

    @Override
    final public void showMessage(int message) {
        showMessage(getMessageText(message));
    }

    @Override
    final public void showMessage(String message) {
        onShowMessageImpl(message, MessageType.NORMAL);
    }

    @Override
    final public void showMessageLong(String message) {
        onShowMessageImpl(message, MessageType.NORMAL_LONG);
    }

    @Override
    public void showMessageSuccess(String message) {
        onShowMessageImpl(message, MessageType.SUCCESS);
    }

    @Override
    public void showMessageSuccess(int message) {
        onShowMessageImpl(getMessageText(message), MessageType.SUCCESS);
    }

    @Override
    public void showMessageFailed(String message) {
        onShowMessageImpl(message, MessageType.FAILED);
    }

    @Override
    public void showMessageFailed(int message) {
        onShowMessageImpl(getMessageText(message), MessageType.FAILED);
    }

    @Override
    public void showMessageWarning(String message) {
        onShowMessageImpl(message, MessageType.WARNING);
    }

    @Override
    public void showMessageWarning(int message) {
        onShowMessageImpl(getMessageText(message), MessageType.WARNING);
    }

    @Override
    final public void showMessageLong(int message) {
        showMessage(getContext().getString(message));
    }

    protected void onShowProgressImpl(String message) {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        mWeakDialog = new WeakReference<>(dialog);
    }

    protected void onShowMessageImpl(String message, MessageType type) {
        Toast.makeText(getContext(),
                message,
                MessageType.NORMAL_LONG.equals(type) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)
                .show();
    }

    private String getMessageText(int resId) {
        return getContext().getString(resId);
    }

    ////

    public enum MessageType {
        NORMAL,
        NORMAL_LONG,
        SUCCESS,
        FAILED,
        WARNING
    }
}
