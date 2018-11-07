package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.GoodsVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/web/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/get")
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

}
