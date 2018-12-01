package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class AdminGorderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long gorderId;
    private String name;
    private String phone;
    private String address;
    private Integer status;
    private String packageCode;
    private Long orderTime;
    private Long payTime;
    private Long confirmTime;
    private Long packageTime;
}
