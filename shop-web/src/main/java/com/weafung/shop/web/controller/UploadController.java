package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.common.util.EncryptUtil;
import com.weafung.shop.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

/**
 * @author weifengshih
 */
@RequestMapping("/api/upload")
@Controller
@Slf4j
public class UploadController {
    @RequestMapping("/image")
    @ResponseBody
    public ResponseVO<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY, "");
        }
        try {
            // 写文件到服务器
            String fileExt = file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf("."));
            File serverFile = new File("d:/tmp/uploads/" + EncryptUtil.md5(file.getOriginalFilename() + System.currentTimeMillis()) + fileExt);
            file.transferTo(serverFile);
            log.info(file.getOriginalFilename());
            return ResponseVO.buildSuccess(serverFile.getAbsolutePath());
        } catch (Exception e) {
            log.warn("upload image failed", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
