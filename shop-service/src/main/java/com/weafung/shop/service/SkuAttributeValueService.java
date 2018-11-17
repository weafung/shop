package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;

/**
 * @author weifeng
 */
public interface SkuAttributeValueService {
    /**
     * 根据属性值ID获取属性值
     * @param attributeValueId
     * @return
     */
    ResponseDTO<SkuAttributeValueDTO> getByAttributeValueId(Long attributeValueId);

    /**
     * 根据属性ID获取属性值
     * @param attributeNameId
     * @return
     */
    ResponseDTO<SkuAttributeValueDTO> getByAttributeNameId(Long attributeNameId);

    ResponseDTO<Boolean> addSkuAttributeValue(Long attributeNameId, String value);

}
