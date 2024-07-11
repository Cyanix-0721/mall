package com.mole.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.user.Admin;
import com.mole.user.mapper.AdminMapper;
import com.mole.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminMapper adminMapper;

	@Override
	public List<Admin> findAll() {
		return adminMapper.selectList(null);
	}

	@Override
	public Page<Admin> findAllPages(int page, int size) {
		return adminMapper.selectPage(new Page<>(page, size), null);
	}

	@Override
	public Admin findById(Long id) {
		return adminMapper.selectById(id);
	}

	@Override
	public Admin findByUsername(String username) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		return adminMapper.selectOne(queryWrapper);
	}

	@Override
	public Admin findByStatus(Integer status) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status", status);
		return adminMapper.selectOne(queryWrapper);
	}

	@Override
	public boolean create(Admin admin) {
		return adminMapper.insert(admin) > 0;
	}

	@Override
	public boolean update(Admin admin) {
		return adminMapper.updateById(admin) > 0;
	}

	@Override
	public boolean delete(Long id) {
		return adminMapper.deleteById(id) > 0;
	}
}