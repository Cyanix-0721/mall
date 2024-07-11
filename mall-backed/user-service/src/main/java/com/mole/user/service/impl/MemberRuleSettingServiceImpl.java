package com.mole.user.service.impl;

import com.mole.common.entity.user.MemberRuleSetting;
import com.mole.user.mapper.MemberRuleSettingMapper;
import com.mole.user.service.MemberRuleSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRuleSettingServiceImpl implements MemberRuleSettingService {

	private final MemberRuleSettingMapper memberRuleSettingMapper;

	@Override
	public List<MemberRuleSetting> findAllRuleSettings() {
		return memberRuleSettingMapper.selectList(null);
	}

	@Override
	public MemberRuleSetting findRuleSettingById(Long id) {
		return memberRuleSettingMapper.selectById(id);
	}

	@Override
	public boolean createRuleSetting(MemberRuleSetting memberRuleSetting) {
		return memberRuleSettingMapper.insert(memberRuleSetting) > 0;
	}

	@Override
	public boolean updateRuleSetting(MemberRuleSetting memberRuleSetting) {
		return memberRuleSettingMapper.updateById(memberRuleSetting) > 0;
	}

	@Override
	public boolean deleteRuleSetting(Long id) {
		return memberRuleSettingMapper.deleteById(id) > 0;
	}
}