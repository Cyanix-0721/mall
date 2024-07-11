package com.mole.user.service;

import com.mole.common.entity.user.MemberGrowthChangeHistory;
import com.mole.common.entity.user.MemberLevel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Member Growth management")
public interface MemberGrowthService {

	//GrowthChangeHistory
	@Operation(summary = "Find all member growth change histories")
	List<MemberGrowthChangeHistory> findAll();

	@Operation(summary = "Find member growth change histories by member id")
	List<MemberGrowthChangeHistory> findByMemberId(Long memberId);

	@Operation(summary = "Find member growth change history by member username")
	List<MemberGrowthChangeHistory> findByMemberUsername(String username);

	@Operation(summary = "Create a new member growth change history")
	boolean create(MemberGrowthChangeHistory memberGrowthChangeHistory);

	@Operation(summary = "Update a member growth change history")
	boolean update(MemberGrowthChangeHistory memberGrowthChangeHistory);

	@Operation(summary = "Delete a member growth change history by id")
	boolean delete(Long id);

	//MemberLevel
	@Operation(summary = "Find all member levels")
	List<MemberLevel> findAllMemberLevels();

	@Operation(summary = "Find member level by id")
	MemberLevel findMemberLevelById(Integer id);

	@Operation(summary = "Find member level by levelName")
	MemberLevel findMemberLevelByLevelName(String levelName);

	@Operation(summary = "Create a new member level")
	boolean createMemberLevel(MemberLevel memberLevel);

	@Operation(summary = "Update a member level")
	boolean updateMemberLevel(MemberLevel memberLevel);

	@Operation(summary = "Delete a member level by id")
	boolean deleteMemberLevel(Integer id);

	//TODO 监听用户成长值实现异步更新会员等级/等级相关修改发送全局通知到用户并确认是否更新会员等级
}