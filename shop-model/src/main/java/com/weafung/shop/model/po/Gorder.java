package com.weafung.shop.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Gorder implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Long gorderId;

    private String accountId;

    private Long addressId;

    private String province;

    private String city;

    private String district;

    private String detail;

    private String phone;

    private String name;

    private String packageCode;

    private Byte status;

    private Long orderTime;

    private Long payTime;

    private Long confirmTime;

    private Long packageTime;

    private static final long serialVersionUID = 1L;
}