package com.daemon.pas.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.ui.activity.SearchPicActivityView;

public class SearchPicActivity extends ActivityPresenter<SearchPicActivityView> {


    @Override
    public Class<SearchPicActivityView> getIViewClass() {
        return SearchPicActivityView.class;
    }



    public static void openActivity(Context context, Bundle bundle){
        Intent intent=new Intent(context,SearchPicActivity.class);
        if(bundle!=null){
            intent.putExtras(bundle);
        }

        context.startActivity(intent);
    }

}
