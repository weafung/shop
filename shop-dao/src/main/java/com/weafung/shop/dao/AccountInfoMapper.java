package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountInfo;
import com.weafung.shop.model.po.AccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    long countByExample(AccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    int deleteByExample(AccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    int insert(AccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    int insertSelective(AccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    List<AccountInfo> selectByExample(AccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated Mon Sep 24 23:22:32 CST 2018
     */
    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);
}