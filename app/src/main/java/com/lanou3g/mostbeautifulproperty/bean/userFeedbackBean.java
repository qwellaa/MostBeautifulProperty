package com.lanou3g.mostbeautifulproperty.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 */

public class UserFeedbackBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String body;
    private String time;

    public UserFeedbackBean(int id, String body, String time) {
        this.id = id;
        this.body = body;
        this.time = time;
    }

    public UserFeedbackBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
