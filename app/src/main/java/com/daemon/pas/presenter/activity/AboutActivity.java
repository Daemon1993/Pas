package com.daemon.pas.presenter.activity;

import android.content.Intent;
import android.view.View;

import com.daemon.framework.dutils.VersionUpdateUtil;
import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.R;
import com.daemon.pas.ui.activity.AboutActivityView;

public class AboutActivity extends ActivityPresenter<AboutActivityView> implements View.OnClickListener {


    @Override
    public Class<AboutActivityView> getIViewClass() {
        return AboutActivityView.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();

        setSupportActionBar(iView.toolbar);
        iView.toolbar.setNavigationOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iView.setOnClickListener(this, R.id.tv_link, R.id.tv_home);


        String vversionName = VersionUpdateUtil.getVerName(this);
        iView.setVersionName(vversionName);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_link:
                String url = iView.getLinkUrl();
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL, url);
                startActivity(intent);
                break;
            case R.id.tv_home:
                String url1 = iView.getLinkHome();
                Intent intent1 = new Intent(this, WebViewActivity.class);
                intent1.putExtra(WebViewActivity.URL, url1);
                startActivity(intent1);
                break;

            default:
                finish();
                break;
        }
    }
}
