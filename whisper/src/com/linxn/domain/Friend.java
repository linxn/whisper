package com.linxn.domain;

import java.io.Serializable;

public class Friend implements Serializable {
    private Integer fId;

    private Integer fType;

    private Integer fFromId;

    private Integer fToId;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfFromId() {
        return fFromId;
    }

    public void setfFromId(Integer fFromId) {
        this.fFromId = fFromId;
    }

    public Integer getfToId() {
        return fToId;
    }

    public void setfToId(Integer fToId) {
        this.fToId = fToId;
    }

    //默认构造函数
    public Friend(){};

    public Friend(int f_from_id, int f_to_id){
        if(f_from_id > f_to_id){
            this.fFromId = f_to_id;
            this.fToId = f_from_id;
        }else {
            this.fFromId = f_from_id;
            this.fToId = f_to_id;
        }
    }
}