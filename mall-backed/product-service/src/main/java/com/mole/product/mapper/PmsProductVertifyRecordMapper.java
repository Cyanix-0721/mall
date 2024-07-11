package com.mole.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mole.common.entity.product.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品审核记录Mapper接口
 */
@Repository
public interface PmsProductVertifyRecordMapper extends BaseMapper<PmsProductVertifyRecord> {

    // 批量插入审核记录的方法
    @Insert("<script>"
            + "INSERT INTO pms_product_vertify_record(product_id, create_time, detail, status, vertify_man)"
            + " VALUES "
            + "<foreach collection='list' item='record' separator=','>"
            + "(#{record.productId}, #{record.createTime}, #{record.detail}, #{record.status}, #{record.vertifyMan})"
            + "</foreach>"
            + "</script>")
    int insertBatch(@Param("list") List<PmsProductVertifyRecord> records);

}
