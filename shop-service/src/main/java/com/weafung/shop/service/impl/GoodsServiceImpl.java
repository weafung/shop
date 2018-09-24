package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeConstant;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.GoodsImageMapper;
import com.weafung.shop.dao.GoodsMapper;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsExample;
import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.model.po.GoodsImageExample;
import com.weafung.shop.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author weifeng
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId) {
        if (goodsId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        // 获取商品信息
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsId).andIsDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (CollectionUtils.isEmpty(goodsList)) {
            return ResponseDTO.build(CodeConstant.NOT_FOUND, null, "商品不存在");
        }

        GoodsDTO goodsDTO = new GoodsDTO();
        Goods goods = goodsList.get(0);
        BeanUtils.copyProperties(goods, goodsDTO);
        // 获取商品图片信息
        GoodsImageExample goodsImageExample = new GoodsImageExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goodsId).andIsDeletedEqualTo(false);
        List<GoodsImage> goodsImageList = goodsImageMapper.selectByExample(goodsImageExample);
        goodsDTO.setGoodsImageList(goodsImageList);

        return ResponseDTO.buildSuccess(goodsDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> saveGoods(GoodsDTO goodsDTO) {
        if (goodsDTO == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> updateGoods(GoodsDTO goodsDTO) {
        if (goodsDTO == null || goodsDTO.getGoodsId() == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goodsDTO.getGoodsId()).andIsDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (CollectionUtils.isEmpty(goodsList)) {
            return ResponseDTO.build(CodeConstant.NOT_FOUND, null, "商品不存在");
        }
        Goods goods = goodsList.get(0);
        BeanUtils.copyProperties(goodsDTO, goods);
        goodsMapper.updateByExampleSelective(goods, goodsExample);
        // 删除原来的照片
        GoodsImageExample goodsImageExample = new GoodsImageExample();
        goodsImageExample.createCriteria().andGoodsIdEqualTo(goodsDTO.getGoodsId()).andIsDeletedEqualTo(false);
        GoodsImage oldGoodsImage = new GoodsImage();
        oldGoodsImage.setIsDeleted(true);
        goodsImageMapper.updateByExampleSelective(oldGoodsImage, goodsImageExample);
        // 添加新照片
        if (CollectionUtils.isNotEmpty(goodsDTO.getGoodsImageList())) {
            goodsDTO.getGoodsImageList()
                    .forEach(newGoodsImage -> goodsImageMapper.insertSelective(newGoodsImage));
        }
        return ResponseDTO.build(Boolean.TRUE);
    }
}
