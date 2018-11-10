package com.weafung.shop.web.controller;

import com.weafung.shop.RequestHolder;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.CheckOutDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.GorderService;
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
@RequestMapping("/api/mall/order")
@Slf4j
public class OrderController {
    @Autowired
    private GorderService gorderService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> checkOut(@RequestBody CheckOutDTO checkOutDTO) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = gorderService.checkOut(accountId, checkOutDTO.getAddressId(), checkOutDTO.getOrderItems());
            if (responseDTO == null) {
                return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
            }
            if (!responseDTO.getData()) {
                return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
            }
        } catch (Exception e) {
            log.warn("checkout failed. exception: {}", e);
            return ResponseVO.build(CodeEnum.ORDER_INSERT_FAIL.getCode(), Boolean.FALSE, e.getMessage());
        }
        return ResponseVO.buildSuccess(Boolean.TRUE);
    }
}
