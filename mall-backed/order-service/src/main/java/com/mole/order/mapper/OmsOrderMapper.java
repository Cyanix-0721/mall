package com.mole.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.order.OmsOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OmsOrderMapper extends BaseMapper<OmsOrder>{

    @Update({
            "<script>",
            "UPDATE oms_order",
            "SET status = 2",
            "WHERE id IN",
            "<foreach item='item' index='index' collection='orderIds' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int delivery(@Param("orderIds") List<Long> orderIds);

    @Delete("<script>" +
            "DELETE FROM oms_order WHERE id IN " +
            "<foreach item='id' collection='orderIds' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDelete(@Param("orderIds") List<Long> orderIds);

}


