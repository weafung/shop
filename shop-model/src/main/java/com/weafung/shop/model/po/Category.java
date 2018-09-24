package com.weafung.shop.model.po;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.gmt_create
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.gmt_modified
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.is_deleted
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private Boolean isDeleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.title
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.image
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private String image;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.parent_id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table category
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.id
     *
     * @return the value of category.id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.id
     *
     * @param id the value for category.id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.gmt_create
     *
     * @return the value of category.gmt_create
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.gmt_create
     *
     * @param gmtCreate the value for category.gmt_create
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.gmt_modified
     *
     * @return the value of category.gmt_modified
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.gmt_modified
     *
     * @param gmtModified the value for category.gmt_modified
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.is_deleted
     *
     * @return the value of category.is_deleted
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.is_deleted
     *
     * @param isDeleted the value for category.is_deleted
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.title
     *
     * @return the value of category.title
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.title
     *
     * @param title the value for category.title
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.image
     *
     * @return the value of category.image
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.image
     *
     * @param image the value for category.image
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.parent_id
     *
     * @return the value of category.parent_id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.parent_id
     *
     * @param parentId the value for category.parent_id
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}