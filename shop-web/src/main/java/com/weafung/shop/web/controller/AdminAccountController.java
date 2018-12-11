package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AdminAccountDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.AdminAccountVO;
import com.weafung.shop.service.AdminAccountService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/admin/account")
@Slf4j
public class AdminAccountController {
    @Autowired
    private AdminAccountService adminAccountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<AdminAccountVO> login(@RequestParam("username") String username,
                                             @RequestParam("password") String password) {
        try {
            ResponseDTO<AdminAccountDTO> responseDTO = adminAccountService.login(username, password);
            if (responseDTO.getData() == null) {
                return ResponseDTO.build(responseDTO.getCode(), null, responseDTO.getMsg());
            }
            return ResponseDTO.buildSuccess(AdminAccountVO.fromAdminAccountDTO(responseDTO.getData()));
        } catch (Exception e) {
            log.warn("login failed. username: {}", username, e);
        }
        return ResponseDTO.build(CodeEnum.ERROR);
    }

//    @RequestMapping(value = "/check")
//    @ResponseBody
//    public ResponseDTO<AdminAccountVO> check(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return ResponseDTO.build(CodeEnum.FORBIDDEN);
//        }
//        String token = request.getHeader("Authorization");
//        if (token == null || StringUtils.isBlank(token)) {
//            return ResponseDTO.build(CodeEnum.FORBIDDEN);
//        }
//        try {
//            ResponseDTO<AdminAccountDTO> responseDTO = adminAccountService.getAdminAccount(token);
//            if (responseDTO.getData() == null) {
//                return ResponseDTO.build(CodeEnum.FORBIDDEN);
//            }
//            return ResponseDTO.buildSuccess(AdminAccountVO.fromAdminAccountDTO(responseDTO.getData()));
//        } catch (Exception e) {
//            log.warn("login failed. username: {}", e);
//        }
//        return ResponseDTO.build(CodeEnum.ERROR);
//    }
}
