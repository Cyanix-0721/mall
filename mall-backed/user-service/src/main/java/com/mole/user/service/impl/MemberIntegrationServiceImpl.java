package com.mole.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mole.common.entity.user.Member;
import com.mole.common.entity.user.MemberIntegrationChangeHistory;
import com.mole.common.entity.user.MemberIntegrationConsumeSetting;
import com.mole.user.mapper.MemberIntegrationChangeHistoryMapper;
import com.mole.user.mapper.MemberIntegrationConsumeSettingMapper;
import com.mole.user.mapper.MemberMapper;
import com.mole.user.service.MemberIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberIntegrationServiceImpl implements MemberIntegrationService {

	private final MemberMapper memberMapper;
	private final MemberIntegrationChangeHistoryMapper memberIntegrationChangeHistoryMapper;
	private final MemberIntegrationConsumeSettingMapper memberIntegrationConsumeSettingMapper;

	@Override
	public List<MemberIntegrationChangeHistory> findAllIntegrationChangeHistories() {
		return memberIntegrationChangeHistoryMapper.selectList(null);
	}

	@Override
	public MemberIntegrationChangeHistory findIntegrationChangeHistoryById(Long id) {
		return memberIntegrationChangeHistoryMapper.selectById(id);
	}

	@Override
	public List<MemberIntegrationChangeHistory> findIntegrationChangeHistoriesByMemberId(Long memberId) {
		QueryWrapper<MemberIntegrationChangeHistory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_id", memberId);
		return memberIntegrationChangeHistoryMapper.selectList(queryWrapper);
	}

	@Override
	public List<MemberIntegrationChangeHistory> findIntegrationChangeHistoriesByMemberUsername(String username) {
		QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
		memberQueryWrapper.eq("username", username);
		Member member = memberMapper.selectOne(memberQueryWrapper);

		if (member != null) {
			QueryWrapper<MemberIntegrationChangeHistory> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("member_id", member.getId());
			return memberIntegrationChangeHistoryMapper.selectList(queryWrapper);
		}

		return Collections.emptyList();
	}

	@Override
	public boolean createIntegrationChangeHistory(MemberIntegrationChangeHistory memberIntegrationChangeHistory) {
		return memberIntegrationChangeHistoryMapper.insert(memberIntegrationChangeHistory) > 0;
	}

	@Override
	public boolean updateIntegrationChangeHistory(MemberIntegrationChangeHistory memberIntegrationChangeHistory) {
		return memberIntegrationChangeHistoryMapper.updateById(memberIntegrationChangeHistory) > 0;
	}

	@Override
	public boolean deleteIntegrationChangeHistory(Long id) {
		return memberIntegrationChangeHistoryMapper.deleteById(id) > 0;
	}

	@Override
	public List<MemberIntegrationConsumeSetting> findAllIntegrationConsumeSettings() {
		return memberIntegrationConsumeSettingMapper.selectList(null);
	}

	@Override
	public MemberIntegrationConsumeSetting findIntegrationConsumeSettingById(Long id) {
		return memberIntegrationConsumeSettingMapper.selectById(id);
	}

	@Override
	public boolean createIntegrationConsumeSetting(MemberIntegrationConsumeSetting memberIntegrationConsumeSetting) {
		return memberIntegrationConsumeSettingMapper.insert(memberIntegrationConsumeSetting) > 0;
	}

	@Override
	public boolean updateIntegrationConsumeSetting(MemberIntegrationConsumeSetting memberIntegrationConsumeSetting) {
		return memberIntegrationConsumeSettingMapper.updateById(memberIntegrationConsumeSetting) > 0;
	}

	@Override
	public boolean deleteIntegrationConsumeSetting(Long id) {
		return memberIntegrationConsumeSettingMapper.deleteById(id) > 0;
	}
}