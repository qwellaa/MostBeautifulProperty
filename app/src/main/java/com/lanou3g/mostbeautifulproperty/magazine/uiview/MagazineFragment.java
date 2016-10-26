package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.StackView;
import android.widget.Toast;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.MagazineBean;
import com.lanou3g.mostbeautifulproperty.magazine.presenter.MagazinePresenter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.LVGhost;

import java.util.ArrayList;

/**
 *
 */

public class MagazineFragment extends BaseFragment implements IMagazineView<MagazineBean>{

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
    public void onResponse(MagazineBean magazineBean) {
        ArrayList<MagazineBean.DataBean.ArticlesBean> beanArrayList = (ArrayList<MagazineBean.DataBean.ArticlesBean>) magazineBean.getData().getArticles();
        mStackView.setAdapter(new CurrentAdapter<MagazineBean.DataBean.ArticlesBean>(context, beanArrayList, R.layout.item_magazine) {
            @Override
            public void convert(BaseViewHolder helper, MagazineBean.DataBean.ArticlesBean item) {
                helper.setIamgeGlide(R.id.iv_magazine_user_head, item.getAuthor().getAvatar_url());
                helper.setIamgeGlide(R.id.iv_magazine_body, item.getImage_url());
                helper.setText(R.id.tv_magazine_user_name, item.getAuthor().getUsername());
                helper.setText(R.id.tv_magazine_title, item.getTitle());
                helper.setText(R.id.tv_magazine_subtitle, item.getSub_title());
            }
        });
    }

    @Override
    public void onError() {
        Toast.makeText(context, "数据异常,请求失败", Toast.LENGTH_SHORT).show();
    }
}
