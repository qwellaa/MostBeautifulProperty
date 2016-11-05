package com.lanou3g.mostbeautifulproperty.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 */

public class FocusDesignerBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private int designerId;
    private int followNum;
    private String iconHeadUrl;
    private String name;
    private String label;
    private String concept;
    private String imageUrl;

    public FocusDesignerBean() {
    }

    public FocusDesignerBean(int id, int designerId, int followNum, String iconHeadUrl, String name, String label, String concept, String imageUrl) {
        this.id = id;
        this.designerId = designerId;
        this.followNum = followNum;
        this.iconHeadUrl = iconHeadUrl;
        this.name = name;
        this.label = label;
        this.concept = concept;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesignerId() {
        return designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getIconHeadUrl() {
        return iconHeadUrl;
    }

    public void setIconHeadUrl(String iconHeadUrl) {
        this.iconHeadUrl = iconHeadUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
