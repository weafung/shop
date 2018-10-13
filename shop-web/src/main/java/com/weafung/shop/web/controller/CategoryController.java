package com.weafung.shop.web.controller;

import com.weafung.shop.model.dto.CategoryDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.CategoryVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponseVO<CategoryVO> listCategories(@RequestParam Long categoryId) {
        ResponseDTO<CategoryDTO> responseDTO = categoryService.listCategories(categoryId);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(responseDTO.getData(), categoryVO);
        return ResponseVO.buildSuccess(categoryVO);
    }
}
