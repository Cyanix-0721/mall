package com.mole.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.user.Member;
import com.mole.user.mapper.MemberMapper;
import com.mole.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	@Override
	public List<Member> findAll() {
		return memberMapper.selectList(null);
	}

	@Override
	public Page<Member> findAllPages(int page, int size) {
		return memberMapper.selectPage(new Page<>(page, size), null);
	}

	@Override
	public Member findById(Long id) {
		return memberMapper.selectById(id);
	}

	@Override
	public Member findByUsername(String username) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		return memberMapper.selectOne(queryWrapper);
	}

	@Override
	public List<Member> findByNicknameFuzzy(String nickname) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("nickname", nickname);
		return memberMapper.selectList(queryWrapper);
	}

	@Override
	public Member findByPhone(String phone) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);
		return memberMapper.selectOne(queryWrapper);
	}

	@Override
	public List<Member> findByStatus(Integer status) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status", status);
		return memberMapper.selectList(queryWrapper);
	}

	@Override
	public List<Member> findByMemberLevelId(Long memberLevelId) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_level_id", memberLevelId);
		return memberMapper.selectList(queryWrapper);
	}

	@Override
	public List<Member> findByGender(Integer gender) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("gender", gender);
		return memberMapper.selectList(queryWrapper);
	}

	@Override
	public List<Member> findByCity(String city) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("city", city);
		return memberMapper.selectList(queryWrapper);
	}

	@Override
	public boolean create(Member member) {
		return memberMapper.insert(member) > 0;
	}

	@Override
	public boolean update(Member member) {
		UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", member.getId()); // 设置更新条件
		return memberMapper.update(member, updateWrapper) > 0; // 进行更新
	}

	@Override
	public boolean delete(Long id) {
		return memberMapper.deleteById(id) > 0;
	}
}