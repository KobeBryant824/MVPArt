package com.cxh.mvpart.ui.user;

import android.widget.ImageView;
import android.widget.TextView;

import com.cxh.mvpart.R;
import com.cxh.mvpart.base.BaseFragment;
import com.cxh.mvpart.di.ActivityScoped;
import com.cxh.mvpart.util.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/6/12
 */
@ActivityScoped
public class UserFragment extends BaseFragment implements UserContract.View {

    @BindView(R.id.startsTextView)
    TextView startsTextView;
    @BindView(R.id.showImage)
    ImageView showImage;

    @Inject
    UserPresenter mUserPresenter;

    @Inject
    UserFragment() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initViewsAndEvents() {
        String path = "https://avatars0.githubusercontent.com/u/13111493?v=4&s=460";
        GlideUtils.loadImage(path, showImage);
    }

    @Override
    protected void refreshState() {
        mUserPresenter.takeView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mUserPresenter.dropView();
        super.onPause();
    }

    @Override
    public void setData(Object data) {
        startsTextView.setText((String) data);
    }
}
