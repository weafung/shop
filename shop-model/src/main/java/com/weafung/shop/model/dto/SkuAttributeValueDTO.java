package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifeng
 */
@Data
public class SkuAttributeValueDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String attributeValueId;

    private String attributeValue;
}
