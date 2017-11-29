package com.github.yoojia.mvp;

import android.app.Application;
import android.content.Context;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
public abstract class NxContextSimple implements NxContext {

    @Override
    public boolean hasContext() {
        return null != getContext();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R extends Application> R getApplication() {
        final Context ctx = getContext();
        if (ctx != null) {
            return (R) ctx.getApplicationContext();
        } else {
            return null;
        }
    }
}
