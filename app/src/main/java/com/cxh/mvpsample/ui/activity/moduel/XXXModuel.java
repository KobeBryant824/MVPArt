package com.cxh.mvpsample.ui.activity.moduel;

import com.cxh.mvpsample.contract.XXXContract;

import dagger.Module;
import dagger.Provides;


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
