package com.mole.user.service;

import com.mole.common.entity.user.MemberReceiveAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Member receiveAddress management")
public interface MemberReceiveAddressService {

	@Operation(summary = "Find all receive addresses of a member")
	List<MemberReceiveAddress> findReceiveAddressesByMemberId(Long memberId);

	@Operation(summary = "Find all receive addresses of a member")
	List<MemberReceiveAddress> findReceiveAddressesByMemberUsername(String username);

	@Operation(summary = "Add a new receive address for a member")
	boolean addReceiveAddress(MemberReceiveAddress address);

	@Operation(summary = "Update a receive address for a member")
	boolean updateReceiveAddress(MemberReceiveAddress address);

	@Operation(summary = "Delete a receive address for a member")
	boolean deleteReceiveAddress(Long id);
}