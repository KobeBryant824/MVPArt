package com.cxh.mvpart.ui.user;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.cxh.mvpart.App;
import com.cxh.mvpart.rx.RxObserver;
import com.cxh.mvpart.rx.RxScheduler;
import com.cxh.mvpart.rx.function.HttpResultFunc;

import javax.inject.Inject;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserPresenter implements UserContract.Presenter {

    private UserActivity mUserActivity;
    private UserFragment mView;

    @Inject
    UserPresenter(Activity activity) {
        mUserActivity = (UserActivity) activity;
    }


    @Override
    public void attachView(@NonNull UserFragment view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        App.RestfulApi()
                .getStargazers()
                .onErrorResumeNext(new HttpResultFunc<>())
                .compose(RxScheduler.switchSchedulers(mUserActivity))
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void refreshUI(String jsonObject) {
                        mView.showContentView();
                        mView.setData(jsonObject);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        mView.showErrorView();
                    }
                });
    }

}
