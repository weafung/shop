package com.weafung.shop.service.impl;

import com.weafung.shop.dao.GoodsImageMapper;
import com.weafung.shop.model.dto.GoodsImageDTO;
import com.weafung.shop.service.GoodsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifengshih
 */
@Service
public class GoodsImageServiceImpl implements GoodsImageService {
    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public List<GoodsImageDTO> listByGoodsId(Long goodsId) {
        return goodsImageMapper.listByGoodsId(goodsId).stream()
                .map(GoodsImageDTO::fromGoodsImage).collect(Collectors.toList());
    }

    @Override
    public List<GoodsImageDTO> listBySkuId(Long skuId) {
        return goodsImageMapper.listBySkuId(skuId).stream()
                .map(GoodsImageDTO::fromGoodsImage).collect(Collectors.toList());
    }
}
