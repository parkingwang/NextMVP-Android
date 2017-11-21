package com.github.yoojia.mvp;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public interface NxUiView extends NxView {

    void showProgress();
    void showProgress(String message);
    void showProgress(int message);

    void hideProgress();
    void hideProgress(int timeoutMs);

    void showMessage(String message);
    void showMessage(int message);

    void showMessageLong(String message);
    void showMessageLong(int message);

    void showMessageSuccess(String message);

    void showMessageSuccess(int message);

    void showMessageFailed(String message);

    void showMessageFailed(int message);

    void showMessageWarning(String message);

    void showMessageWarning(int message);
}
