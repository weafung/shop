package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.CategoryMapper;
import com.weafung.shop.model.dto.CategoryDetailDTO;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.Category;
import com.weafung.shop.service.CategoryService;
import com.weafung.shop.service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weifeng
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
//    @Cacheable(value = "goodsCategory", key = "#categoryId")
    public ResponseDTO<CategoryDetailDTO> listCategories(Long categoryId) {
        List<Category> categoryList = categoryMapper.listByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            return ResponseDTO.build(CodeEnum.CATEGORY_NOT_FOUND);
        }
        Category category = categoryList.get(0);
        CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
        BeanUtils.copyProperties(category, categoryDetailDTO);
        categoryDetailDTO.setChildren(getChildrenCategory(categoryId));
        return ResponseDTO.buildSuccess(categoryDetailDTO);
    }

    private List<CategoryDetailDTO> getChildrenCategory(Long categoryId) {
        List<CategoryDetailDTO> childrenCategoryDetailDTOList = Lists.newArrayList();
        List<Category> childrenCategoryList = categoryMapper.listByParentId(categoryId);
        if (CollectionUtils.isEmpty(childrenCategoryList)) {
            return childrenCategoryDetailDTOList;
        }

        for (Category category : childrenCategoryList) {
            CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
            BeanUtils.copyProperties(category, categoryDetailDTO);
            List<CategoryDetailDTO> childrenCategoryDetailDTOS = getChildrenCategory(category.getCategoryId());
            if (CollectionUtils.isNotEmpty(childrenCategoryDetailDTOS)) {
                categoryDetailDTO.setChildren(childrenCategoryDetailDTOS);
            } else {
                categoryDetailDTO.setChildren(Lists.newArrayList());
            }
            childrenCategoryDetailDTOList.add(categoryDetailDTO);
        }
        return childrenCategoryDetailDTOList;
    }

    @Override
    public ResponseDTO<GoodsDTO> listGoods(Long categoryId) {
        return null;
    }

    @Override
    public ResponseDTO<Boolean> insertCategory(Long parentId, String title, String image, Integer rank) {
        if (parentId == null || StringUtils.isBlank(title)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Long categoryId = snowFlakeService.nextId(CategoryService.class);
        title = StringUtils.trimToEmpty(title);
        image = StringUtils.trimToEmpty(image);
        rank = rank == null ? 0 : rank;
        boolean insertSuccess = categoryMapper.insert(categoryId, parentId, title, image, rank) > 0;
        if (!insertSuccess) {
            log.warn("insert to category table failed. categoryId:{}, parentId:{}, title:{}, image:{},rank:{}",
                    categoryId, parentId, title, image, rank);
            return ResponseDTO.build(CodeEnum.ORDER_INSERT_FAIL, Boolean.FALSE);
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }
}
