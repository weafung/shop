package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AdminGoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuDTO;
import com.weafung.shop.model.query.AdminGoodsQuery;
import com.weafung.shop.model.query.AdminUpdateGoodsQuery;
import com.weafung.shop.model.query.AdminUpdateImagesOfGoodsQuery;
import com.weafung.shop.model.query.AdminUpdateSkuOfGoodsQuery;
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

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<AdminGoodsDTO>> listGoods(AdminGoodsQuery query) {
        try {
            ResponseDTO<List<AdminGoodsDTO>> responseDTO = goodsService.listGoodsForAdministrator(query);
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("list goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""},method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> saveGoods(@RequestBody AdminUpdateGoodsQuery query) {
        if (query == null) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Boolean> responseDTO = goodsService.saveGoods(query);
        if (responseDTO != null && responseDTO.getData() != null) {
            return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
        }
        log.warn("execute goodsService#saveGoods failed. query:{}, responseDTO:{}", query, responseDTO);
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""},method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateGoods(@RequestBody AdminUpdateGoodsQuery query) {
        if (query == null) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Boolean> responseDTO = goodsService.updateGoods(query);
        if (responseDTO != null && responseDTO.getData() != null) {
            return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
        }
        log.warn("execute goodsService#updateGoods failed. query:{}, responseDTO:{}", query, responseDTO);
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> deleteGoods(@RequestParam("goodsId") Long goodsId) {
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

    @RequestMapping(value = {"/images/", "/images"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<String>> listImageOfGoods(@RequestParam("goodsId") Long goodsId) {
        try {
            ResponseDTO<List<String>> responseDTO = goodsService.listImageOfGoods(goodsId);
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("list goods image failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/images/", "/images"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateImagesOfGoods(@RequestBody AdminUpdateImagesOfGoodsQuery query) {
        if (query == null || query.getGoodsId() == null || query.getImageUrls() == null) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        try {
            ResponseDTO<Boolean> responseDTO = goodsService.updateImagesOfGoods(query.getGoodsId(), query.getImageUrls());
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("update goods image failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/sku/", "/sku"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SkuDTO>> listSkuOfGoods(@RequestParam("goodsId") Long goodsId) {
        try {
            ResponseDTO<List<SkuDTO>> responseDTO = skuService.listSkuOfGoods(goodsId);
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

    @RequestMapping(value = {"/sku/", "/sku"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateSkuOfGoods(@RequestBody AdminUpdateSkuOfGoodsQuery query) {
        try {
            ResponseDTO<Boolean> responseDTO = skuService.updateSkuOfGoods(query);
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("delete sku of goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/sku/", "/sku"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> saveSkuOfGoods(@RequestBody AdminUpdateSkuOfGoodsQuery query) {
        try {
            ResponseDTO<Boolean> responseDTO = skuService.saveSkuOfGoods(query);
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("save sku of goods failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
