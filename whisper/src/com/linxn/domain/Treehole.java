package com.linxn.domain;

import java.io.Serializable;
import java.util.Date;

public class Treehole implements Serializable {
    private Integer tId;

    private Integer tPublisherId;

    private String tContent;

    private Integer tLikesCount;

    private Date tPublishTime;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Integer gettPublisherId() {
        return tPublisherId;
    }

    public void settPublisherId(Integer tPublisherId) {
        this.tPublisherId = tPublisherId;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent == null ? null : tContent.trim();
    }

    public Integer gettLikesCount() {
        return tLikesCount;
    }

    public void settLikesCount(Integer tLikesCount) {
        this.tLikesCount = tLikesCount;
    }

    public Date gettPublishTime() {
        return tPublishTime;
    }

    public void settPublishTime(Date tPublishTime) {
        this.tPublishTime = tPublishTime;
    }
}