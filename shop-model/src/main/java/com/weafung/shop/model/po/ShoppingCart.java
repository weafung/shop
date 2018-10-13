package com.weafung.shop.model.po;

import java.io.Serializable;
import java.util.Date;

public class ShoppingCart implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Boolean accountId;

    private Boolean goodsId;

    private Boolean skuId;

    private Boolean count;

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

    public Boolean getAccountId() {
        return accountId;
    }

    public void setAccountId(Boolean accountId) {
        this.accountId = accountId;
    }

    public Boolean getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Boolean goodsId) {
        this.goodsId = goodsId;
    }

    public Boolean getSkuId() {
        return skuId;
    }

    public void setSkuId(Boolean skuId) {
        this.skuId = skuId;
    }

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }
}