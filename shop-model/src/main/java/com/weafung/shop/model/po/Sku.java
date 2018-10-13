package com.weafung.shop.model.po;

import java.io.Serializable;
import java.util.Date;

public class Sku implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Long skuId;

    private Long attributeNameId;

    private Long attributeValueId;

    private Long storeCount;

    private Long saleCount;

    private Long salePrice;

    private Long marketPrice;

    private Byte bonusRatio;

    private Boolean hidden;

    private Boolean onsale;

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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAttributeNameId() {
        return attributeNameId;
    }

    public void setAttributeNameId(Long attributeNameId) {
        this.attributeNameId = attributeNameId;
    }

    public Long getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(Long attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public Long getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(Long storeCount) {
        this.storeCount = storeCount;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Byte getBonusRatio() {
        return bonusRatio;
    }

    public void setBonusRatio(Byte bonusRatio) {
        this.bonusRatio = bonusRatio;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getOnsale() {
        return onsale;
    }

    public void setOnsale(Boolean onsale) {
        this.onsale = onsale;
    }
}