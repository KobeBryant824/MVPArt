package com.cxh.mvpart.ui.user;

import com.cxh.mvpart.RestfulApi;
import com.cxh.mvpart.rx.RxFragmentObserver;
import com.cxh.mvpart.rx.RxScheduler;
import com.cxh.mvpart.rx.function.HttpResultFunc;

import javax.inject.Inject;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/3/6
 */
public class UserPresenter implements UserContract.Presenter {

    private UserActivity mUserActivity;
    private UserContract.View mUserFragment;
    private RestfulApi mRestfulApi;

    @Inject
    UserPresenter(UserActivity activity, RestfulApi restfulApi) {
        mUserActivity = activity;
        mRestfulApi = restfulApi;
    }

    @Override
    public void takeView(UserContract.View view) {
        mUserFragment = view;

        loadData();
    }

    @Override
    public void dropView() {
        mUserFragment = null;
    }

    private void loadData() {
        mRestfulApi
                .getStargazers()
                .onErrorResumeNext(new HttpResultFunc<>())
                .compose(RxScheduler.switchSchedulers(mUserActivity))
                .subscribe(new RxFragmentObserver<String, UserFragment>() {
                    @Override
                    protected void refreshUI(String data) {
                        mUserActivity.showContentView();
                        mUserFragment.setData(data);
                    }
                });
    }
}
