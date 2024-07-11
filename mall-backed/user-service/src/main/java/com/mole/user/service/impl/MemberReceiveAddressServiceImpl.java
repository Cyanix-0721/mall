package com.mole.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mole.common.entity.user.Member;
import com.mole.common.entity.user.MemberReceiveAddress;
import com.mole.user.mapper.MemberMapper;
import com.mole.user.mapper.MemberReceiveAddressMapper;
import com.mole.user.service.MemberReceiveAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberReceiveAddressServiceImpl implements MemberReceiveAddressService {

	private final MemberMapper memberMapper;
	private final MemberReceiveAddressMapper memberReceiveAddressMapper;

	@Override
	public List<MemberReceiveAddress> findReceiveAddressesByMemberId(Long memberId) {
		QueryWrapper<MemberReceiveAddress> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_id", memberId);
		return memberReceiveAddressMapper.selectList(queryWrapper);
	}

	@Override
	public List<MemberReceiveAddress> findReceiveAddressesByMemberUsername(String username) {
		QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		Member member = memberMapper.selectOne(queryWrapper);
		if (member == null) {
			return Collections.emptyList();
		}
		QueryWrapper<MemberReceiveAddress> addressQueryWrapper = new QueryWrapper<>();
		addressQueryWrapper.eq("member_id", member.getId());
		return memberReceiveAddressMapper.selectList(addressQueryWrapper);
	}

	@Override
	public boolean addReceiveAddress(MemberReceiveAddress address) {
		return memberReceiveAddressMapper.insert(address) > 0;
	}

	@Override
	public boolean updateReceiveAddress(MemberReceiveAddress address) {
		return memberReceiveAddressMapper.updateById(address) > 0;
	}

	@Override
	public boolean deleteReceiveAddress(Long id) {
		return memberReceiveAddressMapper.deleteById(id) > 0;
	}


}