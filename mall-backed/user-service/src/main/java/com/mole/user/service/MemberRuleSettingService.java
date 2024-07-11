package com.mole.user.service;

import com.mole.common.entity.user.MemberRuleSetting;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Member Rule Setting management")
public interface MemberRuleSettingService {

	@Operation(summary = "Find all member rule settings")
	List<MemberRuleSetting> findAllRuleSettings();

	@Operation(summary = "Find member rule setting by id")
	MemberRuleSetting findRuleSettingById(Long id);

	@Operation(summary = "Create a new member rule setting")
	boolean createRuleSetting(MemberRuleSetting memberRuleSetting);

	@Operation(summary = "Update a member rule setting")
	boolean updateRuleSetting(MemberRuleSetting memberRuleSetting);

	@Operation(summary = "Delete a member rule setting by id")
	boolean deleteRuleSetting(Long id);
}