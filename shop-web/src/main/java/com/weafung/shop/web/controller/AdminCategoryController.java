package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.CategoryDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/admin/category")
@Slf4j
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> insert(@RequestBody CategoryDTO categoryDTO) {
        try {
            ResponseDTO<Boolean> responseDTO = categoryService.insertCategory(categoryDTO.getParentId(), categoryDTO.getTitle(),
                    categoryDTO.getImage(), categoryDTO.getRank());
            if (responseDTO == null) {
                return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
            }
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
            return ResponseVO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.error("insert category failed. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
