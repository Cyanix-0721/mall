package com.mole.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.order.OmsOrderReturnReason;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OmsOrderReturnReasonMapper extends BaseMapper<OmsOrderReturnReason>{

        @Update({
                "<script>",
                "UPDATE oms_order_return_reason",
                "SET status = #{status}",
                "WHERE id IN",
                "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>",
                "#{item}",
                "</foreach>",
                "</script>"
        })
        int updateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);

}
