package com.weafung.shop.model.query;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class AdminGorderQuery {
    private Long gorderId;
    private String phone;
    private String name;
    private Long orderTimeStart;
    private Long orderTimeEnd;
    private Long payTimeStart;
    private Long payTimeEnd;
    private Long packageTimeStart;
    private Long packageTimeEnd;
    private Long confirmTimeStart;
    private Long confirmTimeEnd;
    private Long startGorderId;
    private Integer pageSize;
    private Integer status;
}
