package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsSkuDTO;
import com.weafung.shop.model.vo.GoodsVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/mall/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<GoodsVO> get(@RequestParam("goodsId") Long goodsId) {
        if (goodsId == null) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<GoodsDTO> responseDTO = goodsService.getGoodsByGoodsId(goodsId);
        if (responseDTO == null) {
            log.warn("execute goodsService#getGoodsByGoodsId failed. goodsId:{}, responseDTO:{}", goodsId, responseDTO);
            return ResponseVO.build(CodeEnum.ERROR);
        }
        if (responseDTO.getData() == null) {
            return ResponseVO.build(responseDTO.getCode(), null, responseDTO.getMsg());
        }
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(responseDTO.getData(), goodsVO);
        return ResponseVO.build(responseDTO.getCode(), goodsVO, responseDTO.getMsg());
    }

    @RequestMapping(value = {"/simple/", "/simple"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Map<Long, SimpleGoodsSkuDTO>> list(@RequestBody List<Long> skuIdList) {
        try {
            ResponseDTO<Map<Long, SimpleGoodsSkuDTO>> responseDTO = goodsService.listGoodsSku(skuIdList);
            if (!CodeEnum.SUCCESS.getCode().equals(responseDTO.getCode()) || responseDTO.getData() == null) {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.warn("fail list simpleGoodsSkuDTO. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
