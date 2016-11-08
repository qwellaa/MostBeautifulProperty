package com.lanou3g.mostbeautifulproperty.homepage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.designer.uiview.DesignerFragment;
import com.lanou3g.mostbeautifulproperty.discover.uiview.DiscoverFragment;
import com.lanou3g.mostbeautifulproperty.magazine.uiview.MagazineFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.MineFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton mRBtnMagazine;
    private RadioButton mRBtnDesigner;
    private RadioButton mRBtnDiscover;
    private RadioButton mRBtnMine;
    private static final int FRAGMENT_MAGAZINE = 0;
    private static final int FRAGMENT_DISCOVER = 1;
    private static final int FRAGMENT_DESIGNER = 2;
    private static final int FRAGMENT_MINE = 3;
    private FragmentManager mManager;
    private MagazineFragment mMagazineFragment;
    private DiscoverFragment mDiscoverFragment;
    private DesignerFragment mDesignerFragment;
    private MineFragment mMineFragment;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mRBtnMagazine = bindView(R.id.radio_btn_main_magazine);
        mRBtnDesigner = bindView(R.id.radio_btn_main_designer);
        mRBtnDiscover = bindView(R.id.radio_btn_main_discover);
        mRBtnMine = bindView(R.id.radio_btn_main_mine);
    }

    @Override
    protected void initData() {

        mRBtnMagazine.setOnClickListener(this);
        mRBtnDiscover.setOnClickListener(this);
        mRBtnDesigner.setOnClickListener(this);
        mRBtnMine.setOnClickListener(this);

        initStartRun();


    }


    private void initStartRun() {
        mRBtnMagazine.setChecked(true);

        // 默认显示的fragment
        mManager = getSupportFragmentManager();
        showFragment(FRAGMENT_MAGAZINE);
        /**
         * 使用Fragment时，可以通过用户交互来执行一些动作，比如增加、移除、替换等。
         * 所有这些改变构成一个集合，这个集合被叫做一个transaction。
         */
//        FragmentTransaction transaction = mManager.beginTransaction();
//        transaction.replace(R.id.replace_view, new MagazineFragment());
//        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.radio_btn_main_magazine:
              //  transaction.replace(R.id.replace_view, new MagazineFragment());
                showFragment(FRAGMENT_MAGAZINE);
                break;
            case R.id.radio_btn_main_discover:
         //       transaction.replace(R.id.replace_view, new DiscoverFragment());
                showFragment(FRAGMENT_DISCOVER);
                break;
            case R.id.radio_btn_main_designer:
         //       transaction.replace(R.id.replace_view, new DesignerFragment());
                showFragment(FRAGMENT_DESIGNER);
                break;
            case R.id.radio_btn_main_mine:
         //       transaction.replace(R.id.replace_view, new MineFragment());
                showFragment(FRAGMENT_MINE);
                break;
        }
        transaction.commit();
    }


    private void showFragment(int index) {
        FragmentTransaction transaction = mManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {

            case FRAGMENT_MAGAZINE:
                if (mMagazineFragment == null) {
                    mMagazineFragment = new MagazineFragment();
                    transaction.add(R.id.replace_view, mMagazineFragment);
                } else {
                    transaction.show(mMagazineFragment);

                }
                break;
            case FRAGMENT_DISCOVER:
                if (mDiscoverFragment == null) {
                    mDiscoverFragment = new DiscoverFragment();
                    transaction.add(R.id.replace_view, mDiscoverFragment);
                } else {
                    transaction.show(mDiscoverFragment);

                }
                break;
            case FRAGMENT_DESIGNER:
                if (mDesignerFragment == null) {
                    mDesignerFragment = new DesignerFragment();
                    transaction.add(R.id.replace_view, mDesignerFragment);
                } else {
                    transaction.show(mDesignerFragment);

                }
                break;
            case FRAGMENT_MINE:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.replace_view, mMineFragment);
                } else {
                    transaction.show(mMineFragment);

                }

                break;
        }
        transaction.commit();


    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mMagazineFragment !=null){
            transaction.hide(mMagazineFragment);
        }
        if (mDiscoverFragment !=null){
            transaction.hide(mDiscoverFragment);
        }
        if (mDesignerFragment !=null){
            transaction.hide(mDesignerFragment);
        }
        if (mMineFragment !=null){
            transaction.hide(mMineFragment);
        }


    }

    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     *
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     *
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove(listener);
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

}
