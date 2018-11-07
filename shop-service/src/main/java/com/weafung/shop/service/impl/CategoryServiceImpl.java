package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.CategoryMapper;
import com.weafung.shop.model.dto.CategoryDTO;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.Category;
import com.weafung.shop.model.po.CategoryExample;
import com.weafung.shop.model.po.Goods;
import com.weafung.shop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weifeng
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    @Cacheable(value = "goodsCategory", key = "#categoryId")
    public ResponseDTO<CategoryDTO> listCategories(Long categoryId) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIsDeletedEqualTo(false).andCategoryIdEqualTo(categoryId);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        if (CollectionUtils.isEmpty(categoryList)) {
            return ResponseDTO.build(CodeEnum.CATEGORY_NOT_FOUND);
        }
        Category category = categoryList.get(0);
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        categoryDTO.setChildren(getChildrenCategory(categoryId));
        return ResponseDTO.buildSuccess(categoryDTO);
    }

    private List<CategoryDTO> getChildrenCategory(Long categoryId) {
        List<CategoryDTO> childrenCategoryDTOList = Lists.newArrayList();

        CategoryExample childrenCategoryExample = new CategoryExample();
        childrenCategoryExample.createCriteria().andIsDeletedEqualTo(false).andParentIdEqualTo(categoryId);
        List<Category> childrenCategoryList = categoryMapper.selectByExample(childrenCategoryExample);
        if (CollectionUtils.isEmpty(childrenCategoryList)) {
            return childrenCategoryDTOList;
        }

        for (Category category : childrenCategoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            List<CategoryDTO> childrenCategoryDTOs = getChildrenCategory(category.getCategoryId());
            if (CollectionUtils.isNotEmpty(childrenCategoryDTOs)) {
                categoryDTO.setChildren(childrenCategoryDTOs);
            } else {
                categoryDTO.setChildren(Lists.newArrayList());
            }
            childrenCategoryDTOList.add(categoryDTO);
        }
        return childrenCategoryDTOList;
    }

    @Override
    public ResponseDTO<GoodsDTO> listGoods(Long categoryId) {
        return null;
    }
}
