package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsHomeNewProduct;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页新品推荐Mapper接口
 */
@Mapper
@Repository
public interface SmsHomeNewProductMapper extends BaseMapper<SmsHomeNewProduct> {

    /**
     * 批量插入首页新品推荐
     */
    @Insert({
            "<script>",
            "INSERT INTO sms_home_new_product(product_id, product_name, recommend_status, sort)",
            "VALUES",
            "<foreach item='item' index='index' collection='list' separator=','>",
            "(#{item.productId}, #{item.productName}, #{item.recommendStatus}, #{item.sort})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int batchInsert(@Param("list") List<SmsHomeNewProduct> homeNewProductList);

    /**
     * 更新推荐排序
     */
    int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

    /**
     * 批量删除推荐
     */
    @Delete("<script>DELETE FROM sms_home_new_product WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 批量更新推荐状态
     */
    @Update("<script>UPDATE sms_home_new_product SET recommend_status = #{recommendStatus} WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int updateRecommendStatus(@Param("ids") List<Long> ids, @Param("recommendStatus") Integer recommendStatus);

    /**
     * 分页查询推荐
     */

    List<SmsHomeNewProduct> selectByCondition(@Param("productName") String productName,
                                              @Param("recommendStatus") Integer recommendStatus,
                                              @Param("pageSize") Integer pageSize,
                                              @Param("offset") Integer offset );

}


