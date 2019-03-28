package com.linxn.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer uId;

    private String uUsername;

    private String uUserpwd;

    private Date uReDate;

    private Byte uOnline;

    private String uHeadPhoto;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername == null ? null : uUsername.trim();
    }

    public String getuUserpwd() {
        return uUserpwd;
    }

    public void setuUserpwd(String uUserpwd) {
        this.uUserpwd = uUserpwd == null ? null : uUserpwd.trim();
    }

    public Date getuReDate() {
        return uReDate;
    }

    public void setuReDate(Date uReDate) {
        this.uReDate = uReDate;
    }

    public Byte getuOnline() {
        return uOnline;
    }

    public void setuOnline(Byte uOnline) {
        this.uOnline = uOnline;
    }

    public String getuHeadPhoto() {
        return uHeadPhoto;
    }

    public void setuHeadPhoto(String uHeadPhoto) {
        this.uHeadPhoto = uHeadPhoto == null ? null : uHeadPhoto.trim();
    }
}