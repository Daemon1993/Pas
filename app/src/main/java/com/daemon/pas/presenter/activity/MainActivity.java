package com.daemon.pas.presenter.activity;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.R;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.fragment.FragmentMusic;
import com.daemon.pas.presenter.fragment.FragmentNews;
import com.daemon.pas.presenter.fragment.FragmentPic;
import com.daemon.pas.presenter.fragment.FragmentVideo;
import com.daemon.pas.ui.activity.MainActivityView;
import com.socks.library.KLog;

public class MainActivity extends ActivityPresenter<MainActivityView> implements View.OnClickListener, MainAFInterface {

    private ActionBarDrawerToggle toggle;

    private int current_id;
    private FragmentTransaction transaction;
    private String current_Fragment_Tag;
    private Fragment current_Fragment;
    private FragmentNews fragmentNews;
    private FragmentMusic fragmentMusic;
    private FragmentPic fragmentPic;
    private FragmentVideo fragmentVideo;

    public static final String TAG_NEWS = "Tag_news";
    public static final String TAG_MUSIC = "Tag_music";
    public static final String TAG_VIDEO = "Tag_video";
    public static final String TAG_PIC = "Tag_pic";
    private String old_title;


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragmentNews == null && fragment instanceof FragmentNews) {
            fragmentNews = (FragmentNews) fragment;
        } else if (fragmentVideo == null && fragment instanceof FragmentVideo) {
            fragmentVideo = (FragmentVideo) fragment;
        } else if (fragmentPic == null && fragment instanceof FragmentPic) {
            fragmentPic = (FragmentPic) fragment;
        } else if (fragmentMusic == null && fragment instanceof FragmentMusic) {
            fragmentMusic = (FragmentMusic) fragment;
        }

    }

    @Override
    public Class<MainActivityView> getIViewClass() {
        return MainActivityView.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();


        /**
         * 一些初始化工作 注册事件 涉及到Context Activity相关
         */
        setSupportActionBar(iView.toolbar);
        toggle = new ActionBarDrawerToggle(this, iView.drawerLayout, iView.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                hideLoadingView();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideLoadingView();
            }
        };

        toggle.syncState();
        iView.drawerLayout.setDrawerListener(toggle);


        old_title = getSupportActionBar().getTitle().toString();

        iView.setOnClickListener(this, R.id.bt_music, R.id.bt_news, R.id.bt_pic, R.id.bt_video);

        current_Fragment = new Fragment();
        if (fragmentNews == null) {
            fragmentNews = new FragmentNews();
        }

        switchFragment(current_Fragment, fragmentNews, TAG_NEWS);

        //初始化界面
        updateState(R.id.bt_news, fragmentNews, TAG_NEWS);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = iView.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {

            showDialogSearch();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogSearch() {

        final EditText editText=new EditText(this);

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("输入要搜索的图片信息")
                .setView(editText)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("搜索", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SearchPicActivity.openActivity(MainActivity.this,null);
                    }
                }).create();

        alertDialog.show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_news:
                if (fragmentNews == null) {
                    fragmentNews = new FragmentNews();
                }

                updateState(R.id.bt_music, fragmentNews, TAG_NEWS);

                iView.drawerLayout.closeDrawer(GravityCompat.START);

                break;

            case R.id.bt_music:


                if (fragmentMusic == null) {
                    fragmentMusic = new FragmentMusic();
                }
                updateState(R.id.bt_music, fragmentMusic, TAG_MUSIC);

                iView.drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.bt_pic:

                if (fragmentPic == null) {
                    fragmentPic = new FragmentPic();
                }

                updateState(R.id.bt_music, fragmentPic, TAG_PIC);
                iView.drawerLayout.closeDrawer(GravityCompat.START);

                break;

            case R.id.bt_video:

                if (fragmentVideo == null) {
                    fragmentVideo = new FragmentVideo();
                }

                updateState(R.id.bt_music, fragmentVideo, TAG_VIDEO);

                iView.drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }

    /**
     * 切换
     *
     * @param id
     * @param fragment
     * @param tag
     */
    private void updateState(int id, Fragment fragment, String tag) {
        current_id = id;
        switchFragment(current_Fragment, fragment, tag);
    }


    /*
     * 切换Fragment
     */
    public void switchFragment(Fragment from, Fragment to, String tag) {

        if (from == null || to == null)
            return;

        current_Fragment_Tag = tag;

        if (current_Fragment != to) {

            transaction = getSupportFragmentManager().beginTransaction();

            if (!to.isAdded()) { // 如果没有被添加过 隐藏当前 添加下一个ø
                transaction.hide(from).add(R.id.frame_content, to, tag).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
            current_Fragment = to;
        }

        switch (tag) {
            case TAG_MUSIC:
                setToolBarTitle(FragmentMusic.Title);

                break;
            case TAG_NEWS:
                setToolBarTitle(FragmentNews.Title);

                break;
            case TAG_PIC:
                setToolBarTitle(FragmentPic.Title);


                break;
            case TAG_VIDEO:
                setToolBarTitle(FragmentVideo.Title);

                break;
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        KLog.e("onPrepareOptionsMenu "+"调用");
        if (TAG_PIC.equals(current_Fragment_Tag)) {
            menu.findItem(R.id.action_search).setVisible(true);
        } else {
            menu.findItem(R.id.action_search).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void hiheLoading() {
        hideLoadingView();
    }


    public void setToolBarTitle(String title) {

        String newTitle = old_title + "-" + title;
        getSupportActionBar().setTitle(newTitle);

        getSupportActionBar().invalidateOptionsMenu();

    }


}
