package com.github.yoojia.mvp;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
@Deprecated
public abstract class NxBasePresenter<V extends NxView> extends NxPresenterSimple<V> {

    public NxBasePresenter(V view) {
        super(view);
    }
}
