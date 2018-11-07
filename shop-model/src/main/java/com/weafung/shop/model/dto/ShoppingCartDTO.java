package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class ShoppingCartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountId;

    private List<ShoppingCartDetailDTO> shoppingCartDetailList;
}
