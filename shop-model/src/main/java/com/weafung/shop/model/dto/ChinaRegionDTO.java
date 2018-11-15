package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class ChinaRegionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer regionId;

    private Integer parentId;

    private String name;

    private Integer level;

}
