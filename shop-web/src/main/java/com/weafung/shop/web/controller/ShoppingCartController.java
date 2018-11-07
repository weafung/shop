package com.weafung.shop.web.controller;

import com.weafung.shop.RequestHolder;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.ShoppingCartDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.model.vo.ShoppingCartVO;
import com.weafung.shop.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifengshih
 */

@Controller
@RequestMapping("/api/web/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<ShoppingCartVO> get() {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCart(accountId).getData();
        ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCartVO);
        return ResponseVO.buildSuccess(shoppingCartVO);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> add(@RequestParam("goodsId")Long goodsId, @RequestParam("skuId")Long skuId,
                                   @RequestParam("count")Integer count) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO = shoppingCartService.addGoods(accountId, goodsId, skuId, count);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
        }
        if (!responseDTO.getData()) {
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        }
        return ResponseVO.buildSuccess(Boolean.TRUE);
    }
}
