package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.ChinaRegionMapper;
import com.weafung.shop.model.dto.ChinaRegionDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.ChinaRegion;
import com.weafung.shop.service.ChinaRegionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weifengshih
 */
@Service
public class ChinaRegionServiceImpl implements ChinaRegionService {
    @Autowired
    private ChinaRegionMapper chinaRegionMapper;

    @Override
    public ResponseDTO<List<ChinaRegionDTO>> listByParentId(Integer parentId) {
        if (parentId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Lists.newArrayList());
        }
        List<ChinaRegion> chinaRegions = chinaRegionMapper.listByParentId(parentId);
        if (CollectionUtils.isEmpty(chinaRegions)) {
            ChinaRegion self = chinaRegionMapper.getByRegionId(parentId);
            if (self == null) {
                return ResponseDTO.build(CodeEnum.ADDRESS_NOT_FOUND, Lists.newArrayList());
            }
            chinaRegions.add(self);
        }
        return ResponseDTO.buildSuccess(chinaRegions.stream().map(this::chinaRegion2DTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseDTO<Map<String, ChinaRegionDTO>> getByNames(List<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Maps.newHashMap());
        }
        Map<String, ChinaRegionDTO> map = Maps.newHashMapWithExpectedSize(names.size());
        names.forEach(name -> {
            ChinaRegion chinaRegion = chinaRegionMapper.getByName(name);
            if (chinaRegion == null) {
                map.put(name, null);
            }
            map.put(name,chinaRegion2DTO(chinaRegion));
        });
        return ResponseDTO.buildSuccess(map);
    }

    private ChinaRegionDTO chinaRegion2DTO(ChinaRegion chinaRegion) {
        if (chinaRegion == null) {
            return null;
        }
        ChinaRegionDTO chinaRegionDTO = new ChinaRegionDTO();
        BeanUtils.copyProperties(chinaRegion, chinaRegionDTO);
        return chinaRegionDTO;
    }
}
