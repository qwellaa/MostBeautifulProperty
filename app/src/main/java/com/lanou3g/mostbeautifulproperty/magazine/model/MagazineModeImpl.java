package com.lanou3g.mostbeautifulproperty.magazine.model;

import com.lanou3g.mostbeautifulproperty.bean.MagazineBean;

/**
 *
 */

public class MagazineModeImpl implements IMagazineModel<MagazineBean>{



    @Override
    public void startRequest(String urlStr, OnFinishedMagazineListener<MagazineBean> listener) {
//        StringRequest request
    }

    @Override
    public void insertInfoDB(MagazineBean magazineBean) {

    }

    @Override
    public void queryGankAll(OnFinishedMagazineListener<MagazineBean> listener) {

    }
}
