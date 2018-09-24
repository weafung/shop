package com.weafung.shop.dao;

import com.weafung.shop.model.po.WebsiteConfig;
import com.weafung.shop.model.po.WebsiteConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebsiteConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    long countByExample(WebsiteConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    int deleteByExample(WebsiteConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    int insert(WebsiteConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    int insertSelective(WebsiteConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    List<WebsiteConfig> selectByExample(WebsiteConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    int updateByExampleSelective(@Param("record") WebsiteConfig record, @Param("example") WebsiteConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table website_config
     *
     * @mbg.generated Mon Sep 24 16:02:48 CST 2018
     */
    int updateByExample(@Param("record") WebsiteConfig record, @Param("example") WebsiteConfigExample example);
}