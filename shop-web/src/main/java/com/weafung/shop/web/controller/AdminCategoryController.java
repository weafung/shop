package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.CategoryDTO;
import com.weafung.shop.model.dto.CategoryDetailDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.CategoryVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/admin/category")
@Slf4j
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<CategoryVO> listCategories() {
        try {
            ResponseDTO<CategoryDetailDTO> responseDTO = categoryService.listAllCategories(0L);
            if (responseDTO.getData() == null) {
                return ResponseVO.build(responseDTO.getCode(), null, responseDTO.getMsg());
            }
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(responseDTO.getData(), categoryVO);
            return ResponseVO.buildSuccess(categoryVO);
        } catch (Exception e) {
            log.error("list categories failed. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);

    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> insert(@RequestBody CategoryDTO categoryDTO) {
        try {
            ResponseDTO<Boolean> responseDTO = categoryService.insertCategory(categoryDTO.getParentId(), categoryDTO.getTitle(),
                    categoryDTO.getImage(), categoryDTO.getRank());
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("insert category failed. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> delete(@RequestParam("categoryId") Long categoryId) {
        try {
            ResponseDTO<Boolean> responseDTO = categoryService.deleteCategory(categoryId);
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("delete category failed. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> update(@RequestBody CategoryDTO categoryDTO) {
        try {
            ResponseDTO<Boolean> responseDTO = categoryService.updateCategory(categoryDTO.getCategoryId(), categoryDTO.getTitle(),
                    categoryDTO.getImage(), categoryDTO.getRank());
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("update category failed. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
