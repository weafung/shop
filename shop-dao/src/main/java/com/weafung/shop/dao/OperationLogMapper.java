package com.weafung.shop.dao;

import com.weafung.shop.model.po.OperationLog;
import com.weafung.shop.model.po.OperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationLogMapper {
    long countByExample(OperationLogExample example);

    int deleteByExample(OperationLogExample example);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    List<OperationLog> selectByExample(OperationLogExample example);

    int updateByExampleSelective(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    int updateByExample(@Param("record") OperationLog record, @Param("example") OperationLogExample example);
}