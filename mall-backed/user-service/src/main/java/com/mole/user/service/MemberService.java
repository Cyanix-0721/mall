package com.mole.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.user.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Member management")
public interface MemberService {

	@Operation(summary = "Find all members")
	List<Member> findAll();

	@Operation(summary = "Find all members with paging")
	Page<Member> findAllPages(int page, int size);

	@Operation(summary = "Find member by id")
	Member findById(Long id);

	@Operation(summary = "Find member by username")
	Member findByUsername(String username);

	@Operation(summary = "Find members by nickname with fuzzy search")
	List<Member> findByNicknameFuzzy(String nickname);

	@Operation(summary = "Find member by phone")
	Member findByPhone(String phone);

	@Operation(summary = "Find member by status")
	List<Member> findByStatus(Integer status);

	@Operation(summary = "Find member by member level id")
	List<Member> findByMemberLevelId(Long memberLevelId);

	@Operation(summary = "Find member by gender")
	List<Member> findByGender(Integer gender);

	@Operation(summary = "Find member by city")
	List<Member> findByCity(String city);

	@Operation(summary = "Create member")
	boolean create(Member member);

	@Operation(summary = "Update member")
	boolean update(Member member);

	@Operation(summary = "Delete member")
	boolean delete(Long id);
}