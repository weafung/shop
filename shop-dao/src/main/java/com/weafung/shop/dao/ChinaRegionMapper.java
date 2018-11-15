package com.weafung.shop.dao;

import com.weafung.shop.model.po.ChinaRegion;

import java.util.List;

/**
 * @author weifengshih
 */
public interface ChinaRegionMapper {
    ChinaRegion getByRegionId(Integer regionId);

    ChinaRegion getByName(String name);

    List<ChinaRegion> listByParentId(Integer parentId);
}
