package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class GorderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long gorderId;

    private String accountId;

    private Long addressId;

    private Byte status;

    private Long orderTime;

    private Long payTime;

    private Long confirmTime;
}
