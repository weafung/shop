package com.weafung.shop.service;

import com.weafung.shop.model.dto.ChinaRegionDTO;
import com.weafung.shop.model.dto.ResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * @author weifengshih
 */
public interface ChinaRegionService {
    ResponseDTO<List<ChinaRegionDTO>> listByParentId(Integer parentId);

    ResponseDTO<Map<String, ChinaRegionDTO>> getByNames(List<String> name);
}
