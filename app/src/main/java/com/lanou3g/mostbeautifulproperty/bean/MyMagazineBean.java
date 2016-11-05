package com.lanou3g.mostbeautifulproperty.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 */

public class MyMagazineBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private int detailsId;
    private String title;
    private String subTitle;
    private String imageUrl;

    public MyMagazineBean() {
    }

    public MyMagazineBean(int id, int detailsId, String title, String subTitle, String imageUrl) {
        this.id = id;
        this.detailsId = detailsId;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
