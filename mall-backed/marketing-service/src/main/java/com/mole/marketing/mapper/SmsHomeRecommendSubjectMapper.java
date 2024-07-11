package com.mole.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.marketing.entity.SmsHomeRecommendSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface SmsHomeRecommendSubjectMapper extends BaseMapper<SmsHomeRecommendSubject> {

    int insertBatch(@Param("list") List<SmsHomeRecommendSubject> recommendSubjectList);
}