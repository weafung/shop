package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AdminGoodsDTO;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/admin/goods")
@Slf4j
public class AdminGoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SkuService skuService;

    @RequestMapping("/update")
    @ResponseBody
    public ResponseVO<Boolean> update(@RequestBody GoodsDTO goodsDTO) {
        if (goodsDTO == null) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Boolean> responseDTO = goodsService.updateGoods(goodsDTO);
        if (responseDTO != null && responseDTO.getData() != null) {
            return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
        }
        log.warn("execute goodsService#updateGoods failed. goodsDTO:{}, responseDTO:{}", goodsDTO, responseDTO);
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<AdminGoodsDTO>> list() {
        try {
            ResponseDTO<List<AdminGoodsDTO>> responseDTO = goodsService.listAdminGoods();
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("list goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }


    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> delete(@RequestParam("goodsId") Long goodsId) {
        try {
            ResponseDTO<Boolean> responseDTO = goodsService.deleteGoods(goodsId);
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("delete goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/sku/", "/sku"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SkuDTO>> sku(@RequestParam("goodsId") Long goodsId) {
        try {
            ResponseDTO<List<SkuDTO>> responseDTO = skuService.listSku(goodsId);
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("list goods sku failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/sku/", "/sku"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> deleteSkuOfGoods(@RequestParam("skuId") Long skuId) {
        try {
            ResponseDTO<Boolean> responseDTO = skuService.deleteSkuOfGoods(skuId);
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("delete goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
