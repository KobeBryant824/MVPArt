package com.cxh.mvpart.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.cxh.mvpart.R;
import com.cxh.mvpart.base.BaseFragment;
import com.cxh.mvpart.contract.UserContract;
import com.cxh.mvpart.model.entity.WelcomeEntity;
import com.cxh.mvpart.presenter.UserPresenter;
import com.cxh.mvpart.ui.activity.UserActivity;
import com.cxh.mvpart.util.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
public class UserFragment extends BaseFragment implements UserContract.View{

    private static final String ACTION_CODE = "ilovekobebryant";

    @BindView(R.id.first_tv)
    TextView mFirstTv;
    @BindView(R.id.showImage)
    ImageView mShowImage;

    private UserPresenter mUserPresenter;

    @Inject
    public UserFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initDagger() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initViewsAndEvents() {
        String path = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg";
        GlideUtils.loadImage(path, mShowImage);

        String action = getActivity().getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            ((UserActivity) getActivity()).showSnackbar(mFirstTv, "带参数的shortcuts");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserPresenter.start();
    }

    @Override
    public void setPresenter(UserPresenter presenter) {
        mUserPresenter = presenter;
    }

    @Override
    public void showContent() {
        ((UserActivity) getActivity()).showContent();
    }

    @Override
    public void showError() {
        ((UserActivity) getActivity()).showError();
    }

    @Override
    public void setData(WelcomeEntity welcomeEntity) {
        mFirstTv.setText(welcomeEntity.getData().toString());
    }
}
