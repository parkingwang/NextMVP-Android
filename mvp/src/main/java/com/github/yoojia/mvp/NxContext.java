package com.github.yoojia.mvp;

import android.app.Application;
import android.content.Context;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
public interface NxContext {

    boolean hasContext();

    <R extends Context> R getContext();

    <R extends Application> R getApplication();
}
