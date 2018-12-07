package com.weafung.shop.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class SkuSpecDTO {
    private Long id;
    private String label;
    private List<SkuSpecDTO> children;
}
