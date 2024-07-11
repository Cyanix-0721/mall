package com.mole.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mole.marketing.entity.SmsHomeAdvertise;
import com.mole.marketing.mapper.SmsHomeAdvertiseMapper;
import com.mole.marketing.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

	@Autowired
	private SmsHomeAdvertiseMapper advertiseMapper;

	@Override
	public int create(SmsHomeAdvertise advertise) {
		return baseMapper.insert(advertise);
	}

	@Override
	public boolean deteleById(Long id) {
		return advertiseMapper.deleteById(id) > 0;
	}

	@Override
	public int updateStatus(Long id, Integer status) {
		SmsHomeAdvertise advertise = new SmsHomeAdvertise();
		advertise.setId(id);
		advertise.setStatus(status);
		return baseMapper.updateById(advertise);
	}

	@Override
	public SmsHomeAdvertise gotById(Long id) {
		return advertiseMapper.selectById(id);
	}


	@Override
	public boolean updateById(SmsHomeAdvertise advertise) {
		return advertiseMapper.updateById(advertise) > 0;
	}

	@Override
	public IPage<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
		Page<SmsHomeAdvertise> page = new Page<>(pageNum, pageSize);
		LambdaQueryWrapper<SmsHomeAdvertise> queryWrapper = new LambdaQueryWrapper<>();
		if (name != null) {
			queryWrapper.like(SmsHomeAdvertise::getName, name);
		}
		if (type != null) {
			queryWrapper.eq(SmsHomeAdvertise::getType, type);
		}
		if (endTime != null) {
			queryWrapper.le(SmsHomeAdvertise::getEndTime, endTime);
		}
		queryWrapper.orderByDesc(SmsHomeAdvertise::getSort);
		return baseMapper.selectPage(page, queryWrapper);
	}
}
