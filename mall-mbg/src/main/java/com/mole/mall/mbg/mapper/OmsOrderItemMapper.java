package com.mole.mall.mbg.mapper;

import com.mole.mall.mbg.pojo.OmsOrderItem;
import com.mole.mall.mbg.pojo.OmsOrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderItemMapper {
	long countByExample(OmsOrderItemExample example);

	int deleteByExample(OmsOrderItemExample example);

	int deleteByPrimaryKey(Long id);

	int insert(OmsOrderItem row);

	int insertSelective(OmsOrderItem row);

	List<OmsOrderItem> selectByExample(OmsOrderItemExample example);

	OmsOrderItem selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("row") OmsOrderItem row, @Param("example") OmsOrderItemExample example);

	int updateByExample(@Param("row") OmsOrderItem row, @Param("example") OmsOrderItemExample example);

	int updateByPrimaryKeySelective(OmsOrderItem row);

	int updateByPrimaryKey(OmsOrderItem row);
}