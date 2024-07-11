package com.mole.user.service;

import com.mole.common.entity.user.MemberIntegrationChangeHistory;
import com.mole.common.entity.user.MemberIntegrationConsumeSetting;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Member Integration management")
public interface MemberIntegrationService {

	// MemberIntegrationChangeHistory
	@Operation(summary = "Find all member integration change histories")
	List<MemberIntegrationChangeHistory> findAllIntegrationChangeHistories();

	@Operation(summary = "Find member integration change history by id")
	MemberIntegrationChangeHistory findIntegrationChangeHistoryById(Long id);

	@Operation(summary = "Find member integration change histories by member id")
	List<MemberIntegrationChangeHistory> findIntegrationChangeHistoriesByMemberId(Long memberId);

	@Operation(summary = "Find member integration change histories by member username")
	List<MemberIntegrationChangeHistory> findIntegrationChangeHistoriesByMemberUsername(String username);

	@Operation(summary = "Create a new member integration change history")
	boolean createIntegrationChangeHistory(MemberIntegrationChangeHistory memberIntegrationChangeHistory);

	@Operation(summary = "Update a member integration change history")
	boolean updateIntegrationChangeHistory(MemberIntegrationChangeHistory memberIntegrationChangeHistory);

	@Operation(summary = "Delete a member integration change history by id")
	boolean deleteIntegrationChangeHistory(Long id);

	// MemberIntegrationConsumeSetting
	@Operation(summary = "Find all member integration consume settings")
	List<MemberIntegrationConsumeSetting> findAllIntegrationConsumeSettings();

	@Operation(summary = "Find member integration consume setting by id")
	MemberIntegrationConsumeSetting findIntegrationConsumeSettingById(Long id);

	@Operation(summary = "Create a new member integration consume setting")
	boolean createIntegrationConsumeSetting(MemberIntegrationConsumeSetting memberIntegrationConsumeSetting);

	@Operation(summary = "Update a member integration consume setting")
	boolean updateIntegrationConsumeSetting(MemberIntegrationConsumeSetting memberIntegrationConsumeSetting);

	@Operation(summary = "Delete a member integration consume setting by id")
	boolean deleteIntegrationConsumeSetting(Long id);
}