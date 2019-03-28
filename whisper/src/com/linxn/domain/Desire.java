package com.linxn.domain;

import java.io.Serializable;
import java.util.Date;

public class Desire implements Serializable {
    private Integer dId;

    private Integer dPublisherId;

    private String dContent;

    private Integer dLikesCount;

    private Date dTime;

    //1 实现了 0 未实现
    private Byte dRealize;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getdPublisherId() {
        return dPublisherId;
    }

    public void setdPublisherId(Integer dPublisherId) {
        this.dPublisherId = dPublisherId;
    }

    public String getdContent() {
        return dContent;
    }

    public void setdContent(String dContent) {
        this.dContent = dContent == null ? null : dContent.trim();
    }

    public Integer getdLikesCount() {
        return dLikesCount;
    }

    public void setdLikesCount(Integer dLikesCount) {
        this.dLikesCount = dLikesCount;
    }

    public Date getdTime() {
        return dTime;
    }

    public void setdTime(Date dTime) {
        this.dTime = dTime;
    }

    public Byte getdRealize() {
        return dRealize;
    }

    public void setdRealize(Byte dRealize) {
        this.dRealize = dRealize;
    }
}