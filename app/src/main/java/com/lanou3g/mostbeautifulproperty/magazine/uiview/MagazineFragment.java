package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.StackView;
import android.widget.Toast;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.MagazineLiteBean;
import com.lanou3g.mostbeautifulproperty.magazine.presenter.MagazinePresenter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.LVGhost;

import java.util.List;

/**
 *
 */

public class MagazineFragment extends BaseFragment implements IMagazineView<MagazineLiteBean>{

    private StackView mStackView;
    private MagazinePresenter mPresenter;
    private AlertDialog mDialog;
    private int PAGA = 1;
    private int PAGASIZE = 20;

    @Override
    protected int setLayout() {
        return R.layout.fragment_magazine;
    }


    @Override
    protected void initView() {
        mStackView = bindView(R.id.magazine_stackview);
        mDialog = createDialog();
    }



    private AlertDialog createDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        LVGhost mLvGhost =  (LVGhost) view.findViewById(R.id.dialog_lvghost);
        mLvGhost.startAnim();
        dialog.setView(view);
        return dialog;
    }

    @Override
    protected void initData() {

        mPresenter = new MagazinePresenter(this);

        mPresenter.startRequest(URLValues.getMagazineUrl(PAGA, PAGASIZE));

        mStackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MagazineLiteBean bean = (MagazineLiteBean) parent.getItemAtPosition(position);
                int derailsId  = bean.getDetailsID();

                Intent intent = new Intent(context, MagaDetailsActivity.class);
                intent.putExtra(MagaDetailsActivity.KEY_ID, derailsId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();

    }

    @Override
    public void onResponse(List<MagazineLiteBean> list) {

        mStackView.setAdapter(new CurrentAdapter<MagazineLiteBean>(context, list, R.layout.item_magazine) {
            @Override
            public void convert(BaseViewHolder helper, MagazineLiteBean item) {
                helper.setIamgeGlide(R.id.iv_magazine_user_head, item.getUserHead());
                helper.setIamgeGlide(R.id.iv_magazine_body, item.getImageUrl());
                helper.setText(R.id.tv_magazine_user_name, item.getUserName());
                helper.setText(R.id.tv_magazine_title, item.getTitle());
                helper.setText(R.id.tv_magazine_subtitle, item.getSubTitle());
            }
        });
    }

    @Override
    public void onError() {
        Toast.makeText(context, "数据异常,请求失败", Toast.LENGTH_SHORT).show();
    }
}
