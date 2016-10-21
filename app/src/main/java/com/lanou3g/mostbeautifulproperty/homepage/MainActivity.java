package com.lanou3g.mostbeautifulproperty.homepage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.designer.DesignerFragment;
import com.lanou3g.mostbeautifulproperty.discover.DiscoverFragment;
import com.lanou3g.mostbeautifulproperty.magazine.MagazineFragment;
import com.lanou3g.mostbeautifulproperty.mine.MineFragment;

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
}
