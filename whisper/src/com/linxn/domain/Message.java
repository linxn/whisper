package com.linxn.domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer mId;

    private Integer mType;

    private Integer mFromId;

    private Integer mToId;

    private String mContent;

    private Date mTime;

    private Byte mIfRead;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Integer getmFromId() {
        return mFromId;
    }

    public void setmFromId(Integer mFromId) {
        this.mFromId = mFromId;
    }

    public Integer getmToId() {
        return mToId;
    }

    public void setmToId(Integer mToId) {
        this.mToId = mToId;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Byte getmIfRead() {
        return mIfRead;
    }

    public void setmIfRead(Byte mIfRead) {
        this.mIfRead = mIfRead;
    }
}