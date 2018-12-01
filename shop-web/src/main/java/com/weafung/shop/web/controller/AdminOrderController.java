package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AdminGorderDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.query.AdminGorderQuery;
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
@RequestMapping("/api/admin/order")
@Slf4j
public class AdminOrderController {
    @Autowired
    private GorderService gorderService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<AdminGorderDTO>> listOrders(AdminGorderQuery query) {
        try {
            ResponseDTO<List<AdminGorderDTO>> responseDTO = gorderService.listGorderByPage(query);
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("list gorder failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> delete(@RequestParam("gorderId") Long gorderId) {
        try {
            ResponseDTO<Boolean> responseDTO = gorderService.deleteGorder(gorderId);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.error("delete gorder failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }


    @RequestMapping(value = {"/package/", "/package"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> packageGorder(@RequestParam("gorderId") Long gorderId, @RequestParam("packageCode") String packageCode) {
        try {
            ResponseDTO<Boolean> responseDTO = gorderService.packageGorder(gorderId, packageCode);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.error("package gorder failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
