package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/admin/user")
@Slf4j
public class AdminUserController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<AccountInfoDTO>> listUser() {
        try {
            ResponseDTO<List<AccountInfoDTO>> responseDTO = accountService.listAccountInfo();
            return ResponseVO.buildSuccess(responseDTO.getData());
        } catch (Exception e) {
            log.error("failed to  list user info", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
