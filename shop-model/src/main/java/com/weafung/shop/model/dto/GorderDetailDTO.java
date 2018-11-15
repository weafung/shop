package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class GorderDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private GorderDTO gorderDTO;

    private List<SorderDTO> sorderDTOList;
}
