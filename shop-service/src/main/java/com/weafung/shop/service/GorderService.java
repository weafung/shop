package com.weafung.shop.service;


import com.weafung.shop.model.dto.AdminGorderDTO;
import com.weafung.shop.model.dto.GorderDetailDTO;
import com.weafung.shop.model.dto.OrderItemDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.query.AdminGorderQuery;

import java.util.List;
import java.util.Set;

/**
 * @author weifengshih
 */
public interface GorderService {

    ResponseDTO<GorderDetailDTO> getGorderDetail(Long gorderId);

    ResponseDTO<Boolean> checkOut(String accountId, Long addressId, Set<OrderItemDTO> orderItemDTOSet);

    ResponseDTO<List<GorderDetailDTO>> listGorderDetail(String accountId, Long gorderId, Integer status);

    ResponseDTO<Boolean> confirmPay(String accountId, Long gorderId);

    ResponseDTO<Boolean> confirmReceived(String accountId, Long gorderId);

    ResponseDTO<List<AdminGorderDTO>> listGorderByPage(AdminGorderQuery query);

    ResponseDTO<Boolean> packageGorder(Long gorderId, String packageCode);

    ResponseDTO<Boolean> deleteGorder(Long gorderId);

}
