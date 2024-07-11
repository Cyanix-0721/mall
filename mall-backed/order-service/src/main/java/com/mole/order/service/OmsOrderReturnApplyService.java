package com.mole.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mole.common.entity.order.OmsOrderReturnApply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@FeignClient("order-service")
public interface OmsOrderReturnApplyService {

    /**
     * 分页查询页码
     * @param pageNum  当前页码
     * @param pageSize 每条页数
     * @return 分页结果
     */
    IPage<OmsOrderReturnApply> returnApplyList(Integer pageSize, Integer pageNum);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean batchDeleteApplys(List<Long> ids);

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    boolean updateApplyStatus(Long id, Integer status);

    boolean submitReturnApply(OmsOrderReturnApply omsOrderReturnApply);

}
