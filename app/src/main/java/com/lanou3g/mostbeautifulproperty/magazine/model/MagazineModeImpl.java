package com.lanou3g.mostbeautifulproperty.magazine.model;

import com.lanou3g.mostbeautifulproperty.bean.MagazineBean;
import com.lanou3g.mostbeautifulproperty.bean.MagazineLiteBean;
import com.lanou3g.mostbeautifulproperty.dbtool.DBTools;
import com.lanou3g.mostbeautifulproperty.okhttp.HttpManager;
import com.lanou3g.mostbeautifulproperty.okhttp.OnCompletedListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MagazineModeImpl implements IMagazineModel<MagazineLiteBean>{

    @Override
    public void startRequest(String urlStr, final OnFinishedMagazineListener<MagazineLiteBean> listener) {
        HttpManager.getInstance().getRequest(urlStr, MagazineBean.class, new OnCompletedListener<MagazineBean>() {
            @Override
            public void onCompleted(MagazineBean result) {
                if (result.getResult() == 1) {
                    List<MagazineLiteBean> beanList = new ArrayList<MagazineLiteBean>();
                    for (int i = 0; i < result.getData().getArticles().size(); i++) {
                        MagazineLiteBean bean = new MagazineLiteBean();
                        bean.setUserName(result.getData().getArticles().get(i).getAuthor().getUsername());
                        bean.setUserHead(result.getData().getArticles().get(i).getAuthor().getAvatar_url());
                        bean.setTitle(result.getData().getArticles().get(i).getTitle());
                        bean.setSubTitle(result.getData().getArticles().get(i).getSub_title());
                        bean.setImageUrl(result.getData().getArticles().get(i).getImage_url());
                        beanList.add(bean);
                    }
                    listener.onFinished(beanList);
                } else {
                    listener.onError();
                    return;
                }
            }

            @Override
            public void onFailed() {
                listener.onError();
            }
        });
    }

    @Override
    public void insertInfoDB(List<MagazineLiteBean> list) {
        DBTools.getInstance().insertDB(list);
    }

    @Override
    public void queryGankAll(final OnFinishedMagazineListener<MagazineLiteBean> listener) {
        DBTools.getInstance().getQueryAll(MagazineLiteBean.class, new DBTools.QueryListener<MagazineLiteBean>() {
            @Override
            public void onQuery(List<MagazineLiteBean> beanArrayList) {
                if (beanArrayList.isEmpty()) {
                    listener.onError();
                } else {
                    listener.onFinished(beanArrayList);
                }
            }
        });
    }

    @Override
    public void deleteInfoDB(Class<MagazineLiteBean> magazineLiteBeanClass) {
        DBTools.getInstance().deleteAll(magazineLiteBeanClass);
    }
}
