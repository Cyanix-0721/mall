package com.mole.common.client.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.Response;
import com.mole.common.entity.marketing.SmsCouponHistory;
import com.mole.common.entity.user.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {

	// MemberController
	@GetMapping("/members")
	ResponseEntity<Response<List<Member>>> findAll();

	@GetMapping("/members/{id}")
	ResponseEntity<Response<Member>> findById(@PathVariable Long id);

	@GetMapping("/members/username/{username}")
	ResponseEntity<Response<Member>> findByUsername(@PathVariable String username);

	@GetMapping("/members/nickname/{nickname}")
	ResponseEntity<Response<List<Member>>> findByNicknameFuzzy(@PathVariable String nickname);

	@GetMapping("/members/phone/{phone}")
	ResponseEntity<Response<Member>> findByPhone(@PathVariable String phone);

	@GetMapping("/members/status/{status}")
	ResponseEntity<Response<List<Member>>> findByStatus(@PathVariable Integer status);

	@GetMapping("/members/memberLevelId/{memberLevelId}")
	ResponseEntity<Response<List<Member>>> findByMemberLevelId(@PathVariable Long memberLevelId);

	@GetMapping("/members/gender/{gender}")
	ResponseEntity<Response<List<Member>>> findByGender(@PathVariable Integer gender);

	@GetMapping("/members/city/{city}")
	ResponseEntity<Response<List<Member>>> findByCity(@PathVariable String city);

	@PostMapping("/members")
	ResponseEntity<Response<Member>> create(@RequestBody Member member);

	@PutMapping("/members")
	ResponseEntity<Response<Member>> update(@RequestBody Member member);

	@DeleteMapping("/members/{id}")
	ResponseEntity<Response<Void>> delete(@PathVariable Long id);

	// MemberReceiveAddressController
	@GetMapping("/members/member-receive-addresses/{memberId}")
	ResponseEntity<Response<List<MemberReceiveAddress>>> findReceiveAddressesByMemberId(@PathVariable Long memberId);

	@GetMapping("/members/member-receive-addresses/username/{username}")
	ResponseEntity<Response<List<MemberReceiveAddress>>> findReceiveAddressesByMemberUsername(@PathVariable String username);

	@PostMapping("/members/member-receive-addresses")
	ResponseEntity<Response<MemberReceiveAddress>> addReceiveAddress(@RequestBody MemberReceiveAddress address);

	@PutMapping("/members/member-receive-addresses")
	ResponseEntity<Response<MemberReceiveAddress>> updateReceiveAddress(@RequestBody MemberReceiveAddress address);

	@DeleteMapping("/members/member-receive-addresses/{id}")
	ResponseEntity<Response<Void>> deleteReceiveAddress(@PathVariable Long id);

	// MemberRuleSettingController
	@GetMapping("/members/ruleSetting")
	ResponseEntity<List<MemberRuleSetting>> findAllRuleSettings();

	@GetMapping("/members/ruleSetting/{id}")
	ResponseEntity<MemberRuleSetting> findRuleSettingById(@PathVariable Long id);

	@PostMapping("/members/ruleSetting/")
	ResponseEntity<Response<MemberRuleSetting>> createRuleSetting(@RequestBody MemberRuleSetting memberRuleSetting);

	@PutMapping("/members/ruleSetting/{id}")
	ResponseEntity<Response<MemberRuleSetting>> updateRuleSetting(@PathVariable Long id, @RequestBody MemberRuleSetting memberRuleSetting);

	@DeleteMapping("/members/ruleSetting/{id}")
	ResponseEntity<Response<Void>> deleteRuleSetting(@PathVariable Long id);

	// MemberGrowthController
	@GetMapping("/members/memberGrowth/history")
	ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findAllMemberGrowthChangeHistories();

	@GetMapping("/members/memberGrowth/history/{memberId}")
	ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findByMemberId(@PathVariable Long memberId);

	@GetMapping("/members/memberGrowth/history/findByMemberUsername/{username}")
	ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findByMemberUsername(@PathVariable String username);

	@PostMapping("/members/memberGrowth/history/{memberId}")
	ResponseEntity<Response<Boolean>> createMemberGrowthChangeHistory(@PathVariable Long memberId, @RequestBody MemberGrowthChangeHistory memberGrowthChangeHistory);

	@PutMapping("/members/memberGrowth/history/{id}")
	ResponseEntity<Response<Boolean>> updateMemberGrowthChangeHistory(@RequestBody MemberGrowthChangeHistory memberGrowthChangeHistory);

	@DeleteMapping("/members/memberGrowth/history/{id}")
	ResponseEntity<Response<Boolean>> deleteMemberGrowthChangeHistory(@PathVariable Long id);

	@GetMapping("/members/memberGrowth/level/")
	ResponseEntity<Response<List<MemberLevel>>> findAllMemberLevels();

	@GetMapping("/members/memberGrowth/level/{id}")
	ResponseEntity<Response<MemberLevel>> findMemberLevelById(@PathVariable Integer id);

	@GetMapping("/members/memberGrowth/level/findByLevelName/{levelName}")
	ResponseEntity<Response<MemberLevel>> findMemberLevelByLevelName(@PathVariable String levelName);

	@PostMapping("/members/memberGrowth/level")
	ResponseEntity<Response<Boolean>> createMemberLevel(@RequestBody MemberLevel memberLevel);

	@PutMapping("/members/memberGrowth/level/{id}")
	ResponseEntity<Response<Boolean>> updateMemberLevel(@RequestBody MemberLevel memberLevel);

	@DeleteMapping("/members/memberGrowth/level/{id}")
	ResponseEntity<Response<Boolean>> deleteMemberLevel(@PathVariable Integer id);

	// MemberIntegrationController
	@GetMapping("/members/memberIntegration/changeHistory")
	ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findAllMemberIntegrationChangeHistories();

	@GetMapping("/members/memberIntegration/changeHistory/{memberId}")
	ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findMemberIntegrationChangeHistoriesByMemberId(@PathVariable Long memberId);

	@GetMapping("/members/memberIntegration/changeHistory/findByMemberUsername/{username}")
	ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findMemberIntegrationChangeHistoriesByMemberUsername(@PathVariable String username);

	@PostMapping("/members/memberIntegration/changeHistory/{memberId}")
	ResponseEntity<Response<Boolean>> createMemberIntegrationChangeHistory(@PathVariable Long memberId, @RequestBody MemberIntegrationChangeHistory memberIntegrationChangeHistory);

	@PutMapping("/members/memberIntegration/changeHistory/{id}")
	ResponseEntity<Response<Boolean>> updateMemberIntegrationChangeHistory(@RequestBody MemberIntegrationChangeHistory memberIntegrationChangeHistory);

	@DeleteMapping("/members/memberIntegration/changeHistory/{id}")
	ResponseEntity<Response<Boolean>> deleteMemberIntegrationChangeHistory(@PathVariable Long id);

	@GetMapping("/members/memberIntegration/consumeSetting")
	ResponseEntity<Response<List<MemberIntegrationConsumeSetting>>> findAllMemberIntegrationSettings();

	@GetMapping("/members/memberIntegration/consumeSetting/{id}")
	ResponseEntity<Response<MemberIntegrationConsumeSetting>> findMemberIntegrationSettingById(@PathVariable Long id);

	@PostMapping("/members/memberIntegration/consumeSetting")
	ResponseEntity<Response<MemberIntegrationConsumeSetting>> createMemberIntegrationSetting(@RequestBody MemberIntegrationConsumeSetting memberIntegrationConsumeSetting);

	@PutMapping("/members/memberIntegration/consumeSetting/{id}")
	ResponseEntity<Response<MemberIntegrationConsumeSetting>> updateMemberIntegrationSetting(@PathVariable Long id, @RequestBody MemberIntegrationConsumeSetting memberIntegrationConsumeSetting);

	@DeleteMapping("/members/memberIntegration/consumeSetting/{id}")
	ResponseEntity<Response<Void>> deleteMemberIntegrationSetting(@PathVariable Long id);

	//MemberCouponController
	@GetMapping("/members/coupon/{memberId}")
	ResponseEntity<Response<List<SmsCouponHistory>>> getMemberCoupons(@PathVariable Long memberId);

	// AdminController
	@GetMapping("/admins")
	ResponseEntity<Response<List<Admin>>> findAllAdmins();

	@GetMapping("/admins/pages")
	ResponseEntity<Response<Page<Admin>>> findAllPages(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size);

	@GetMapping("/admins/{id}")
	ResponseEntity<Response<Admin>> findAdminById(@PathVariable Long id);

	@GetMapping("/admins/username/{username}")
	ResponseEntity<Response<Admin>> findAdminByUsername(@PathVariable String username);

	@PostMapping("/admins")
	ResponseEntity<Response<Admin>> createAdmin(@RequestBody Admin admin);

	@PutMapping("/admins/{id}")
	ResponseEntity<Response<Admin>> updateAdmin(@PathVariable Long id, @RequestBody Admin admin);

	@DeleteMapping("/admins/{id}")
	ResponseEntity<Response<Void>> deleteAdmin(@PathVariable Long id);
}