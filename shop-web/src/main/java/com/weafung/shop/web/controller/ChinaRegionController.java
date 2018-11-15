package com.weafung.shop.web.controller;

import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ChinaRegionDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ChinaRegionVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.ChinaRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/mall/chinaRegion")
@Slf4j
public class ChinaRegionController {
    private static final Integer DEFAULT_PARENT_ID = 1;

    @Autowired
    private ChinaRegionService chinaRegionService;

    @RequestMapping(value = {"/list/", "/list"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<Map<Integer, String>> listByParentId(@RequestParam(value = "parentId", required = false) Integer parentId) {
        try {
            parentId = parentId == null ? DEFAULT_PARENT_ID : parentId;
            ResponseDTO<List<ChinaRegionDTO>> responseDTO = chinaRegionService.listByParentId(parentId);
            Map<Integer, String> map = Maps.newHashMapWithExpectedSize(responseDTO.getData().size());
            responseDTO.getData().forEach(chinaRegionDTO -> {
                map.put(chinaRegionDTO.getRegionId(), chinaRegionDTO.getName());
            });
            return ResponseVO.buildSuccess(map);
        } catch (Exception e) {
            log.warn("error occurred when call china region service list by parent id. parentId: {}", parentId, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/names/", "/names"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Map<String, ChinaRegionVO>> getByName(@RequestBody List<String> names) {
        try {
            ResponseDTO<Map<String, ChinaRegionDTO>> responseDTO = chinaRegionService.getByNames(names);
            Map<String, ChinaRegionVO> map = Maps.newHashMapWithExpectedSize(names.size());
            for (Map.Entry<String, ChinaRegionDTO> entry : responseDTO.getData().entrySet()) {
                map.put(entry.getKey(), chinaRegionDTO2VO(entry.getValue()));
            }
            return ResponseVO.buildSuccess(map);
        } catch (Exception e) {
            log.warn("error occurred when call china region service list by names. names: {}", names, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    private ChinaRegionVO chinaRegionDTO2VO(ChinaRegionDTO chinaRegionDTO) {
        if (chinaRegionDTO == null) {
            return null;
        }
        ChinaRegionVO chinaRegionVO = new ChinaRegionVO();
        BeanUtils.copyProperties(chinaRegionDTO, chinaRegionVO);
        return chinaRegionVO;
    }
}
