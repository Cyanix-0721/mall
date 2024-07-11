package com.mole.order.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.order.OmsOrderReturnApply;
import com.mole.order.mapper.OmsOrderReturnApplyMapper;
import com.mole.order.service.OmsOrderReturnApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    private final OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;

    @Override
    public IPage<OmsOrderReturnApply> returnApplyList(Integer pageSize, Integer pageNum) {
        Page<OmsOrderReturnApply> page = new Page<>(pageNum, pageSize);
        return omsOrderReturnApplyMapper.selectPage(page, null);
    }

    @Override
    public boolean batchDeleteApplys(List<Long> ids) {
        return omsOrderReturnApplyMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public boolean updateApplyStatus(Long id, Integer status) {
        OmsOrderReturnApply omsOrderReturnApply = new OmsOrderReturnApply();
        omsOrderReturnApply.setId(id);
        omsOrderReturnApply.setStatus(status);
        return omsOrderReturnApplyMapper.updateById(omsOrderReturnApply) > 0;


    }

    @Override
    public boolean submitReturnApply(OmsOrderReturnApply omsOrderReturnApply) {
        try {
            omsOrderReturnApplyMapper.insert(omsOrderReturnApply);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
