package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.portal.SmsHomeRecommendSubject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页推荐专题Mapper
 */
@Mapper
@Repository
public interface SmsHomeRecommendSubjectMapper  extends BaseMapper<SmsHomeRecommendSubject> {

    /**
     * 插入首页推荐专题
     *
     * @param recommendSubject 待插入的推荐专题对象
     * @return 受影响的行数
     */
    @Insert("INSERT INTO sms_home_recommend_subject (subject_id, subject_name, recommend_status, sort) VALUES (#{subjectId}, #{subjectName}, #{recommendStatus}, #{sort})")
    int insert(SmsHomeRecommendSubject recommendSubject);

    /**
     * 批量插入首页推荐专题
     *
     * @param recommendSubjectList 推荐专题对象列表
     * @return 受影响的行数
     */
    @Insert("<script>"
            + "INSERT INTO sms_home_recommend_subject (subject_id, subject_name, recommend_status, sort)"
            + " VALUES "
            + "<foreach item='item' index='index' collection='list' separator=','>"
            + "(#{item.subjectId}, #{item.subjectName}, #{item.recommendStatus}, #{item.sort})"
            + "</foreach>"
            + "</script>")
    int batchInsert(@Param("list") List<SmsHomeRecommendSubject> recommendSubjectList);

    /**
     * 更新推荐专题排序
     *
     * @param id    主键ID
     * @param sort  排序值
     * @return 受影响的行数
     */
    @Update("UPDATE sms_home_recommend_subject SET sort = #{sort} WHERE id = #{id}")
    int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

    /**
     * 批量删除推荐专题
     *
     * @param ids 主键ID列表
     * @return 受影响的行数
     */
    @Delete("<script>"
            + "DELETE FROM sms_home_recommend_subject WHERE id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>"
            + "</script>")
    int batchDelete(@Param("ids") List<Long> ids);

    /**
     * 批量更新推荐状态
     *
     * @param ids          主键ID列表
     * @param recommendStatus 推荐状态
     * @return 受影响的行数
     */
    @Update("<script>"
            + "UPDATE sms_home_recommend_subject SET recommend_status = #{recommendStatus}"
            + " WHERE id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>"
            + "</script>")
    int batchUpdateRecommendStatus(@Param("ids") List<Long> ids, @Param("recommendStatus") Integer recommendStatus);

    @Select({
            "<script>",
            "SELECT * FROM sms_home_recommend_subject",
            "<where>",
            "   <if test='subjectName != null'>",
            "       subject_name like #{subjectName}",
            "   </if>",
            "   <if test='recommendStatus != null'>",
            "       AND recommend_status = #{recommendStatus}",
            "   </if>",
            "</where>",
            "ORDER BY ${orderBy}",
            "</script>"
    })
    List<SmsHomeRecommendSubject> listByCondition(
            @Param("subjectName") String subjectName,
            @Param("recommendStatus") Integer recommendStatus,
            @Param("orderBy") String orderBy
    );
}