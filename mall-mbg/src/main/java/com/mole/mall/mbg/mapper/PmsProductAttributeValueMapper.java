package com.mole.mall.mbg.mapper;

import com.mole.mall.mbg.pojo.PmsProductAttributeValue;
import com.mole.mall.mbg.pojo.PmsProductAttributeValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductAttributeValueMapper {
    long countByExample(PmsProductAttributeValueExample example);

    int deleteByExample(PmsProductAttributeValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeValue row);

    int insertSelective(PmsProductAttributeValue row);

    List<PmsProductAttributeValue> selectByExample(PmsProductAttributeValueExample example);

    PmsProductAttributeValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductAttributeValue row, @Param("example") PmsProductAttributeValueExample example);

    int updateByExample(@Param("row") PmsProductAttributeValue row, @Param("example") PmsProductAttributeValueExample example);

    int updateByPrimaryKeySelective(PmsProductAttributeValue row);

    int updateByPrimaryKey(PmsProductAttributeValue row);
}