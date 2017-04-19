package com.cxh.mvpsample.manager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 管理 CompositeDisposable
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class RxDisposable {
    private static CompositeDisposable sDisposable = new CompositeDisposable();

    public static void add(Disposable d) {
        if (d != null) {
            sDisposable.add(d);
        }
    }

    public static void delete(Disposable d) {
        if (d != null) {
            sDisposable.delete(d);
        }
    }

    public static void remove(Disposable d) {
        if (d != null) {
            sDisposable.remove(d);
        }
    }

    public static void clear() {
        sDisposable.clear();
    }

    public static boolean isDisposed() {
        return sDisposable.isDisposed();
    }

    public static void dispose() {
        sDisposable.dispose();
    }
}
