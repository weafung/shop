package com.weafung.shop.model.po;

import java.io.Serializable;
import java.util.Date;

public class OperationLog implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private String accountId;

    private Byte type;

    private String oldData;

    private String newData;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData == null ? null : oldData.trim();
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData == null ? null : newData.trim();
    }
}