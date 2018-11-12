package com.weafung.shop.service;

import com.weafung.shop.model.dto.AddressDTO;
import com.weafung.shop.model.dto.ResponseDTO;

import java.util.List;

/**
 * @author weifengshih
 */
public interface AddressService {
    ResponseDTO<Boolean> updateAddress(String accountId, AddressDTO addressDTO);

    ResponseDTO<Boolean> insertAddress(String accountId, AddressDTO addressDTO);

    ResponseDTO<List<AddressDTO>> listAddresses(String accountId);

    ResponseDTO<Boolean> deleteAddress(String accountId, List<Long> addressIdList);

    ResponseDTO<AddressDTO> getAddress(String accountId, Long addressId);
}
