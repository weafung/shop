package com.weafung.shop.web.controller;

import com.weafung.shop.RequestHolder;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.CheckOutDTO;
import com.weafung.shop.model.dto.GorderDetailDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.GorderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/mall/order")
@Slf4j
public class OrderController {
    @Autowired
    private GorderService gorderService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<GorderDetailDTO>> listGorder(@RequestParam(value = "gorderId", required = false) Long gorderId,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<List<GorderDetailDTO>> responseDTO = gorderService.listGorderDetail(accountId, gorderId, status);
        return ResponseVO.buildSuccess(responseDTO.getData());
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> checkOut(@RequestBody CheckOutDTO checkOutDTO) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO;
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
