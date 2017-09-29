package com.cxh.mvpart.ui.user;

import android.widget.ImageView;
import android.widget.TextView;

import com.cxh.mvpart.R;
import com.cxh.mvpart.base.BaseFragment;
import com.cxh.mvpart.util.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
public class UserFragment extends BaseFragment implements UserContract.View {

    @BindView(R.id.startsTextView)
    TextView startsTextView;
    @BindView(R.id.showImage)
    ImageView showImage;

    private UserPresenter mUserPresenter;

    @Inject
    UserFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void injectDagger() {
    }

    @Override
    protected void initViewsAndEvents() {
        String path = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg";
        GlideUtils.loadImage(path, showImage);
    }

    @Override
    protected void refreshState() {
        mUserPresenter.start();
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
    public void setData(String data) {
        startsTextView.setText(data);
    }
}
