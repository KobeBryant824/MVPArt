package com.cxh.mvpsample.ui.activity.moduel;

import com.cxh.mvpsample.contract.XXXContract;

import dagger.Module;
import dagger.Provides;

/**
 * Desc:
 * Created by Hai (haigod7@gmail.com) on 2017/6/7 13:47.
 */
@Module
public class XXXModuel {
    private XXXContract.View mView;

    public XXXModuel(XXXContract.View view) {
        mView = view;
    }

    @Provides
    XXXContract.View provideXXXContractView(){
        return mView;
    }
}
