package com.weafung.shop.model.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long addressId;

    private String accountId;

    private String province;

    private String city;

    private String district;

    private String detail;

    private String phone;

    private String name;

    private Boolean first;

    public boolean validAddress() {
        return StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(district)
                && StringUtils.isNotBlank(detail) && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(name);
    }
}
