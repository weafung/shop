package com.weafung.shop.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weifengshih
 */
@Data
public class ChinaRegion implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Integer regionId;

    private Integer parentId;

    private String name;

    private Integer level;

    private static final long serialVersionUID = 1L;
}
