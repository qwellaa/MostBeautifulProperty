package com.lanou3g.mostbeautifulproperty.homepage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

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

        // 默认显示的碎片
        FragmentManager manager = getSupportFragmentManager();
        /**
         * 使用Fragment时，可以通过用户交互来执行一些动作，比如增加、移除、替换等。
         * 所有这些改变构成一个集合，这个集合被叫做一个transaction。
         */
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_view, new MagazineFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.radio_btn_main_magazine:
                transaction.replace(R.id.replace_view, new MagazineFragment());
                break;
            case R.id.radio_btn_main_discover:
                transaction.replace(R.id.replace_view, new DiscoverFragment());
                break;
            case R.id.radio_btn_main_designer:
                transaction.replace(R.id.replace_view, new DesignerFragment());
                break;
            case R.id.radio_btn_main_mine:
                transaction.replace(R.id.replace_view, new MineFragment());
                break;
        }
        transaction.commit();
    }
    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
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
