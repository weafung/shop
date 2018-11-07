package com.weafung.shop.web.controller;

import com.weafung.shop.RequestHolder;
import com.weafung.shop.model.dto.ShoppingCartDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.model.vo.ShoppingCartVO;
import com.weafung.shop.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/get")
    @ResponseBody
    public ResponseVO<ShoppingCartVO> get() {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCart(accountId).getData();
        ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCartVO);
        return ResponseVO.buildSuccess(shoppingCartVO);
    }
}
