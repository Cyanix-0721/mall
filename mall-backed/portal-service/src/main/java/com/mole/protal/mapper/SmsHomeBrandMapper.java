package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsHomeBrand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页品牌Mapper
 */
@Mapper
@Repository
public interface SmsHomeBrandMapper extends BaseMapper<SmsHomeBrand> {

    /**
     * 批量插入首页品牌推荐
     */
    @Insert("<script>" +
            "INSERT INTO sms_home_brand (brand_id, brand_name, recommend_status, sort)" +
            " VALUES " +
            "<foreach item='item' index='index' collection='list' separator=','>" +
            "(#{item.brandId}, #{item.brandName}, #{item.recommendStatus}, #{item.sort})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<SmsHomeBrand> homeBrandList);

    /**
     * 更新品牌推荐排序
     */
    @Update("UPDATE sms_home_brand SET sort = #{sort} WHERE id = #{id}")
    int updateSortById(@Param("id") Long id, @Param("sort") Integer sort);

    /**
     * 批量删除品牌推荐
     */
    @Delete("<script>" +
            "DELETE FROM sms_home_brand WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 批量更新推荐状态
     */
    @Update("<script>" +
            "UPDATE sms_home_brand SET recommend_status = #{recommendStatus} WHERE id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int updateRecommendStatusByIds(@Param("ids") List<Long> ids, @Param("recommendStatus") Integer recommendStatus);

    /**
     * 分页查询品牌推荐
     */
    @Select("<script>" +
            "SELECT * FROM sms_home_brand" +
            "<where>" +
            "<if test='brandName != null'>AND brand_name LIKE CONCAT('%', #{brandName}, '%')</if>" +
            "<if test='recommendStatus != null'>AND recommend_status = #{recommendStatus}</if>" +
            "</where>" +
            "ORDER BY sort ASC" +
            "<if test='offset != null'>LIMIT #{pageSize} OFFSET #{offset}</if>" +
            "</script>")
    List<SmsHomeBrand> selectPage(
            @Param("brandName") String brandName,
            @Param("recommendStatus") Integer recommendStatus,
            @Param("pageSize") Integer pageSize,
            @Param("offset") Integer offset);
}


