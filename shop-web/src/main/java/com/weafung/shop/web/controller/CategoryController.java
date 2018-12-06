package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.CategoryDetailDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsDTO;
import com.weafung.shop.model.vo.CategoryVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.CategoryService;
import com.weafung.shop.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/mall/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<CategoryVO> listCategories(@RequestParam Long categoryId) {
        ResponseDTO<CategoryDetailDTO> responseDTO = categoryService.listAllCategories(categoryId);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, null);
        }
        if (responseDTO.getData() == null) {
            return ResponseVO.build(responseDTO.getCode(), null, responseDTO.getMsg());
        }
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(responseDTO.getData(), categoryVO);
        return ResponseVO.buildSuccess(categoryVO);
    }

    @RequestMapping(value = {"/goods/", "/goods"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SimpleGoodsDTO>> listGoods(@RequestParam(required = false) Long firstCategoryId,
                                                      @RequestParam(required = false) Long secondCategoryId,
                                                      @RequestParam(required = false) Long thirdCategoryId) {
        ResponseDTO<List<SimpleGoodsDTO>> responseDTO = goodsService.listGoodsByCategoryId(firstCategoryId, secondCategoryId, thirdCategoryId);
        return ResponseVO.buildSuccess(responseDTO.getData());
    }
}
