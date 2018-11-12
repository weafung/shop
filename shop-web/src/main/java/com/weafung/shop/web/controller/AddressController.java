package com.weafung.shop.web.controller;

import com.google.common.collect.Lists;
import com.weafung.shop.RequestHolder;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.AddressDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.AddressVO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/mall/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = {"", "/"})
    @ResponseBody
    public ResponseVO<AddressVO> getAddress(@RequestParam(value = "addressId", required = false) Long addressId) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        try {
            ResponseDTO<AddressDTO> responseDTO = addressService.getAddress(accountId, addressId);
            if (responseDTO.getData() == null) {
                return ResponseVO.build(responseDTO.getCode(), null, responseDTO.getMsg());
            }
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(responseDTO.getData(), addressVO);
            return ResponseVO.buildSuccess(addressVO);
        } catch (Exception e) {
            log.warn("get address failed.", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> addAddress(AddressDTO addressDTO) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO = addressService.insertAddress(accountId, addressDTO);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
        }
        if (!responseDTO.getData()) {
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        }
        return ResponseVO.buildSuccess(Boolean.TRUE);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateAddress(AddressDTO addressDTO) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO = addressService.updateAddress(accountId, addressDTO);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
        }
        if (!responseDTO.getData()) {
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        }
        return ResponseVO.buildSuccess(Boolean.TRUE);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> deleteAddress(@RequestBody List<Long> addressIdList) {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<Boolean> responseDTO = addressService.deleteAddress(accountId, addressIdList);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, Boolean.FALSE);
        }
        if (!responseDTO.getData()) {
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        }
        return ResponseVO.buildSuccess(Boolean.TRUE);
    }

    @RequestMapping(value = {"/list/", "/list"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<AddressVO>> listAddress() {
        String accountId = RequestHolder.getCurrentUser().getAccountId();
        ResponseDTO<List<AddressDTO>> responseDTO = addressService.listAddresses(accountId);
        if (responseDTO == null) {
            return ResponseVO.build(CodeEnum.ERROR, Lists.newArrayList());
        }
        if (!CodeEnum.SUCCESS.getCode().equals(responseDTO.getCode()) || responseDTO.getData() == null) {
            return ResponseVO.build(responseDTO.getCode(), Lists.newArrayList(), responseDTO.getMsg());
        }
        List<AddressVO> addressVOList = responseDTO.getData().stream().map(addressDTO -> {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressDTO, addressVO);
            return addressVO;
        }).collect(Collectors.toList());
        return ResponseVO.buildSuccess(addressVOList);
    }
}
