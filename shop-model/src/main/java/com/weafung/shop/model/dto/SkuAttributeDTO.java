package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifeng
 */
@Data
public class SkuAttributeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long attributeNameId;

    private String attributeName;

    private Long attributeValueId;

    private String attributeValue;
}
