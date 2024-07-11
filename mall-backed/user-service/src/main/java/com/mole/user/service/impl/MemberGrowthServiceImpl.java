package com.mole.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mole.common.entity.user.Member;
import com.mole.common.entity.user.MemberGrowthChangeHistory;
import com.mole.common.entity.user.MemberLevel;
import com.mole.user.mapper.MemberGrowthChangeHistoryMapper;
import com.mole.user.mapper.MemberLevelMapper;
import com.mole.user.mapper.MemberMapper;
import com.mole.user.service.MemberGrowthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberGrowthServiceImpl implements MemberGrowthService {

	private final MemberLevelMapper memberLevelMapper;
	private final MemberGrowthChangeHistoryMapper memberGrowthChangeHistoryMapper;
	private final MemberMapper memberMapper;

	//GrowthChangeHistory
	@Override
	public List<MemberGrowthChangeHistory> findAll() {
		return memberGrowthChangeHistoryMapper.selectList(null);
	}

	@Override
	public List<MemberGrowthChangeHistory> findByMemberId(Long memberId) {
		QueryWrapper<MemberGrowthChangeHistory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_id", memberId);
		return memberGrowthChangeHistoryMapper.selectList(queryWrapper);
	}

	@Override
	public List<MemberGrowthChangeHistory> findByMemberUsername(String username) {
		Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq("username", username));
		if (member != null) {
			return findByMemberId(member.getId());
		}
		return Collections.emptyList();
	}

	@Override
	public boolean create(MemberGrowthChangeHistory memberGrowthChangeHistory) {
		return memberGrowthChangeHistoryMapper.insert(memberGrowthChangeHistory) > 0;
	}

	@Override
	public boolean update(MemberGrowthChangeHistory memberGrowthChangeHistory) {
		return memberGrowthChangeHistoryMapper.updateById(memberGrowthChangeHistory) > 0;
	}

	//MemberLevel
	@Override
	public boolean delete(Long id) {
		return memberGrowthChangeHistoryMapper.deleteById(id) > 0;
	}

	@Override
	public List<MemberLevel> findAllMemberLevels() {
		try {
			return memberLevelMapper.selectList(null);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public MemberLevel findMemberLevelById(Integer id) {
		try {
			return memberLevelMapper.selectById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public MemberLevel findMemberLevelByLevelName(String levelName) {
		try {
			QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("level_name", levelName);
			return memberLevelMapper.selectOne(queryWrapper);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean createMemberLevel(MemberLevel memberLevel) {
		return memberLevelMapper.insert(memberLevel) > 0;
	}

	@Override
	public boolean updateMemberLevel(MemberLevel memberLevel) {
		return memberLevelMapper.updateById(memberLevel) > 0;
	}

	@Override
	public boolean deleteMemberLevel(Integer id) {
		return memberLevelMapper.deleteById(id) > 0;
	}
}