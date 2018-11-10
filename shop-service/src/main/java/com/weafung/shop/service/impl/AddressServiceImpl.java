package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.AddressMapper;
import com.weafung.shop.model.dto.AddressDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.Address;
import com.weafung.shop.service.AddressService;
import com.weafung.shop.service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifengshih
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
    public ResponseDTO<Boolean> updateAddress(String accountId, AddressDTO addressDTO) {
        if (StringUtils.isBlank(accountId) || addressDTO == null || !addressDTO.validAddress()) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        boolean updateSuccess = addressMapper.updateByAddressIdSelective(address, addressDTO.getAddressId()) > 0;
        if (updateSuccess) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.SAVE_FAIL, Boolean.FALSE);
    }

    @Override
    @Transactional
    public ResponseDTO<Boolean> insertAddress(String accountId, AddressDTO addressDTO) {
        if (StringUtils.isBlank(accountId) || addressDTO == null || !addressDTO.validAddress()) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        addressDTO.setAccountId(accountId);
        addressDTO.setAddressId(snowFlakeService.nextAddressId());
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        boolean insertSuccess = addressMapper.insertSelective(address) > 0;
        if (insertSuccess) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.SAVE_FAIL, Boolean.FALSE);
    }

    @Override
    public ResponseDTO<List<AddressDTO>> listAddresses(String accountId) {
        if (StringUtils.isBlank(accountId)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Lists.newArrayList());
        }
        List<Address> addressList = addressMapper.listByAccountId(accountId);
        List<AddressDTO> addressDTOList = addressList.stream().map(this::address2AddressDTO).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(addressDTOList);
    }

    @Override
    public ResponseDTO<Boolean> deleteAddress(String accountId, List<Long> addressIdList) {
        if (CollectionUtils.isEmpty(addressIdList)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        addressIdList.forEach(addressId -> {
            boolean deleteSuccess = addressMapper.deleteByAccountIdAndAddressId(accountId, addressId) > 0;
            if (!deleteSuccess) {
                log.warn("delete address failed. addressId:{}", addressId);
            }
        });
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }

    @Override
    public AddressDTO getAddress(Long addressId) {
        Address address = addressMapper.getAddressByAddressId(addressId);
        return address2AddressDTO(address);
    }

    private AddressDTO address2AddressDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }
}
