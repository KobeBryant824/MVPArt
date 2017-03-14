package com.cxh.mvpsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.cxh.mvpsample.MApplication;
import com.cxh.mvpsample.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 简单的页面还是用mvc
 */
public class MainActivity extends AppCompatActivity {

    @BindString(R.string.app_name)
    String mAppName;

    @BindView(R.id.mvp_btn)
    Button mvpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MApplication.getContext().addPage(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mvp_btn)
    public void onClick() {
        startActivity(new Intent(this, XXXActivity.class));
    }

    boolean doubleBackToExitPressedOnce = false;

    // 双击返回键退出
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            MApplication.getContext().exitApp();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再次点击退出" + mAppName, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
