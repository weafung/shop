package com.weafung.shop.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class GorderDetailDTO {
    private GorderDTO gorderDTO;

    private List<SorderDTO> sorderDTOList;
}
