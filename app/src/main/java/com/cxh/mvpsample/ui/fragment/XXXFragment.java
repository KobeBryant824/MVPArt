package com.cxh.mvpsample.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.cxh.mvpsample.R;
import com.cxh.mvpsample.base.BaseFragment;
import com.cxh.mvpsample.contract.XXXContract;
import com.cxh.mvpsample.model.api.XXXApi;
import com.cxh.mvpsample.presenter.XXXPresenter;
import com.cxh.mvpsample.ui.activity.XXXActivity;
import com.cxh.mvpsample.util.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
public class XXXFragment extends BaseFragment implements XXXContract.View{

    private static final String ACTION_CODE = "ilovekobebryant";
    private String mPath = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg";

    @BindView(R.id.first_tv)
    TextView mFirstTv;
    @BindView(R.id.showImage)
    ImageView mShowImage;

    private XXXPresenter mXXXPresenter;

    @Inject
    public XXXFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_xxx;
    }

    @Override
    protected void initDagger() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initViewsAndEvents() {
        GlideUtils.loadImage(mPath, mShowImage);

        String action = getActivity().getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            ((XXXActivity) getActivity()).showSnackbar(mFirstTv, "带参数的shortcuts");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mXXXPresenter.start();
    }

    @Override
    public void setData(XXXApi.WelcomeEntity data) {
        mFirstTv.setText(data.toString());
    }

    @Override
    public void setPresenter(XXXPresenter presenter) {
        mXXXPresenter = presenter;
    }

    @Override
    public void showContent() {
        ((XXXActivity) getActivity()).showContent();
    }

    @Override
    public void showError() {
        ((XXXActivity) getActivity()).showError();
    }
}
