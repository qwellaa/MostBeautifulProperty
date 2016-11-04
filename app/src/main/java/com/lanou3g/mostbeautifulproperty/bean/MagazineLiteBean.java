package com.lanou3g.mostbeautifulproperty.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 */

public class MagazineLiteBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String userHead;
    private String userName;
    private String title;
    private String subTitle;
    private String imageUrl;
    private int detailsID;

    public MagazineLiteBean() {
    }

    public MagazineLiteBean(int id, String userHead, String userName, String title, String subTitle, String imageUrl, int detailsID) {
        this.id = id;
        this.userHead = userHead;
        this.userName = userName;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.detailsID = detailsID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getDetailsID() {
        return detailsID;
    }

    public void setDetailsID(int detailsID) {
        this.detailsID = detailsID;
    }
}
