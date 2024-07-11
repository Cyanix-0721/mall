package com.mole.protal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.common.entity.portal.SmsHomeAdvertise;
import com.mole.protal.mapper.SmsHomeAdvertiseMapper;
import com.mole.protal.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Autowired
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;

    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return smsHomeAdvertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeAdvertiseMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setId(id);
        advertise.setStatus(status);
        return smsHomeAdvertiseMapper.updateById(advertise);
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return smsHomeAdvertiseMapper.selectById(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return smsHomeAdvertiseMapper.updateById(advertise);
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTimeStr, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SmsHomeAdvertise> queryWrapper = Wrappers.lambdaQuery();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like(SmsHomeAdvertise::getName, name);
        }
        if (type != null) {
            queryWrapper.eq(SmsHomeAdvertise::getType, type);
        }
        if (endTimeStr != null && !endTimeStr.isEmpty()) {
            try {
                Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr);
                queryWrapper.le(SmsHomeAdvertise::getEndTime, endTime);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format for endTime.", e);
            }
        }

        // 分页处理，MyBatis Plus自带分页插件
        Page<SmsHomeAdvertise> page = new Page<>(pageNum, pageSize);
        return smsHomeAdvertiseMapper.selectPage(page, queryWrapper).getRecords();
    }
}