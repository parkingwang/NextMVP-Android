package com.github.yoojia.mvp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public abstract class NxUiViewSimple extends NxViewSimple implements NxUiView {

    public static final int WHAT_HIDE_PROGRESS = 11;
    public static final int WHAT_SHOW_PROGRESS = 22;
    public static final int WHAT_SHOW_MESSAGES = 33;

    private WeakReference<ProgressDialog> mWeakDialog;

    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (WHAT_SHOW_PROGRESS == msg.what) {
                final String message = (String) msg.obj;
                onShowProgressImpl(message);
            } else if (WHAT_HIDE_PROGRESS == msg.what) {
                onHideProgressImpl();
            } else if (WHAT_SHOW_MESSAGES == msg.what) {
                final String message = (String) msg.obj;
                MessageType type = MessageType.of(msg.arg1);
                onShowMessageImpl(message, type);
            }
            return true;
        }


    });

    @Override
    final public void showProgress() {
        proxyProgress(getMessageText(R.string.mvp_loading));
    }

    @Override
    final public void showProgress(String message) {
        proxyProgress(message);
    }

    @Override
    final public void showProgress(int message) {
        proxyProgress(getMessageText(message));
    }

    @Override
    final public void hideProgress() {
        mMainThreadHandler.sendEmptyMessage(WHAT_HIDE_PROGRESS);
    }

    @Override
    final public void hideProgress(int timeoutMs) {
        mMainThreadHandler.sendEmptyMessageDelayed(WHAT_HIDE_PROGRESS, timeoutMs);
    }

    @Override
    final public void showMessage(int message) {
        proxyMessage(getMessageText(message), MessageType.TIP);
    }

    @Override
    final public void showMessage(String message) {
        proxyMessage(message, MessageType.TIP);
    }

    @Override
    public void showMessageSuccess(String message) {
        proxyMessage(message, MessageType.SUCCESS);
    }

    @Override
    public void showMessageSuccess(int message) {
        proxyMessage(getMessageText(message), MessageType.SUCCESS);
    }

    @Override
    public void showMessageFailed(String message) {
        proxyMessage(message, MessageType.FAILED);
    }

    @Override
    public void showMessageFailed(int message) {
        proxyMessage(getMessageText(message), MessageType.FAILED);
    }

    @Override
    public void showMessageWarning(String message) {
        proxyMessage(message, MessageType.WARNING);
    }

    @Override
    public void showMessageWarning(int message) {
        proxyMessage(getMessageText(message), MessageType.WARNING);
    }

    @Override
    final public void showMessageLong(String message) {
        proxyMessage(message, MessageType.TIP_LONG);
    }

    @Override
    final public void showMessageLong(int message) {
        proxyMessage(getContext().getString(message), MessageType.TIP_LONG);
    }

    protected void onShowProgressImpl(String message) {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        mWeakDialog = new WeakReference<>(dialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected void onHideProgressImpl() {
        if (mWeakDialog != null) {
            final ProgressDialog dialog = mWeakDialog.get();
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
            mWeakDialog.clear();
            mWeakDialog = null;
        }
    }

    protected void onShowMessageImpl(String message, MessageType type) {
        Toast.makeText(getContext(),
                message,
                MessageType.TIP_LONG.equals(type) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)
                .show();
    }

    private void proxyProgress(String message) {
        // 检查是否在主线程中执行
        if (Looper.getMainLooper() == Looper.myLooper()) {
            onShowProgressImpl(message);
        } else {
            final Message msg = mMainThreadHandler.obtainMessage(WHAT_SHOW_PROGRESS);
            msg.obj = message;
            msg.sendToTarget();
        }
    }

    private void proxyMessage(String message, MessageType type) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            onShowMessageImpl(message, type);
        } else {
            final Message msg = mMainThreadHandler.obtainMessage(WHAT_SHOW_MESSAGES);
            msg.arg1 = type.ordinal();
            msg.obj = message;
            msg.sendToTarget();
        }
    }

    private String getMessageText(int resId) {
        return getContext().getString(resId);
    }

    ////

    public enum MessageType {
        /**
         * 常规提示
         */
        TIP,

        /**
         * 常规提示，长时间显示
         */
        TIP_LONG,

        /**
         * 成功提示
         */
        SUCCESS,

        /**
         * 失败提示
         */
        FAILED,

        /**
         * 警告提示
         */
        WARNING;

        public static MessageType of(int ordinal) {
            if (ordinal == MessageType.WARNING.ordinal()) {
                return MessageType.WARNING;
            } else if (ordinal == MessageType.FAILED.ordinal()) {
                return MessageType.FAILED;
            } else if (ordinal == MessageType.SUCCESS.ordinal()) {
                return MessageType.SUCCESS;
            } else if (ordinal == MessageType.TIP_LONG.ordinal()) {
                return MessageType.TIP_LONG;
            } else {
                return MessageType.TIP;
            }
        }
    }
}
