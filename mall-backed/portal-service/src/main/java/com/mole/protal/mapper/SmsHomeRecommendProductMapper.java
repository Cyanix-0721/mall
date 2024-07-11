package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsHomeRecommendProduct;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页推荐商品Mapper接口
 */
@Mapper
@Repository
public interface SmsHomeRecommendProductMapper extends BaseMapper<SmsHomeRecommendProduct> {

    /**
     * 批量插入首页推荐商品
     */
    @Insert("<script>"
            + "INSERT INTO sms_home_recommend_product(product_id, product_name, recommend_status, sort)"
            + " VALUES "
            + "<foreach item='item' index='index' collection='list' separator=','>"
            + "(#{item.productId}, #{item.productName}, #{item.recommendStatus}, #{item.sort})"
            + "</foreach>"
            + "</script>")
    int batchInsert(@Param("list") List<SmsHomeRecommendProduct> homeRecommendProductList);

    /**
     * 更新推荐排序
     */
    @Update("UPDATE sms_home_recommend_product SET sort = #{sort} WHERE id = #{id}")
    int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

    /**
     * 批量删除推荐商品
     */
    @Delete("<script>"
            + "DELETE FROM sms_home_recommend_product WHERE id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 批量更新推荐状态
     */
    @Update("<script>"
            + "UPDATE sms_home_recommend_product SET recommend_status = #{recommendStatus} WHERE id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    int updateRecommendStatus(@Param("ids") List<Long> ids, @Param("recommendStatus") Integer recommendStatus);

    /**
     * 分页查询推荐商品
     */
    @Select("<script>"
            + "SELECT * FROM sms_home_recommend_product"
            + "<where>"
            + "<if test='productName != null'>"
            + "AND product_name LIKE CONCAT('%', #{productName}, '%')"
            + "</if>"
            + "<if test='recommendStatus != null'>"
            + "AND recommend_status = #{recommendStatus}"
            + "</if>"
            + "</where>"
            + "ORDER BY sort ASC"
            + "LIMIT #{offset}, #{pageSize}"
            + "</script>")
    List<SmsHomeRecommendProduct> listPageable(
            @Param("productName") String productName,
            @Param("recommendStatus") Integer recommendStatus,
            @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize);
}
