package com.mole.protal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.portal.CmsSubject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsSubjectMapper extends BaseMapper<CmsSubject> {
    /**
     * 获取推荐专题
     */
    @Select("SELECT * FROM cms_subject WHERE recommend_status = 1  LIMIT #{offset}, #{limit}")
    List<CmsSubject> getRecommendSubjectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
    /**
     * 分页查询CMS专题信息
     *
     * @param page 分页对象，包含当前页码和每页数量
     * @param wrapper 查询条件构造器，用于设置查询条件
     * @return 分页数据结果 {@link IPage<CmsSubject>}
     */
    IPage<CmsSubject> selectPageCustom(IPage<CmsSubject> page, Wrapper<CmsSubject> wrapper);
}
