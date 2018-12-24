package com.weafung.shop.web.controller;

import com.weafung.shop.RequestHolder;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.vo.AccountInfoVO;
import com.weafung.shop.model.vo.ResponseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/mall/user")
public class UserController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<AccountInfoVO> getUserInfo() {
        AccountInfoDTO accountInfoDTO = RequestHolder.getCurrentUser();
        return ResponseVO.buildSuccess(AccountInfoVO.fromDTO(accountInfoDTO));
    }
}
