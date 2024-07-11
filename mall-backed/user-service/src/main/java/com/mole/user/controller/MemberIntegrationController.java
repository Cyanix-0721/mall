package com.mole.user.controller;

import com.mole.common.entity.Response;
import com.mole.common.entity.user.MemberIntegrationChangeHistory;
import com.mole.common.entity.user.MemberIntegrationConsumeSetting;
import com.mole.user.service.MemberIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/memberIntegration")
@RequiredArgsConstructor
@Tag(name = "Member Integration management")
public class MemberIntegrationController {

	private final MemberIntegrationService memberIntegrationService;

	@GetMapping("/changeHistory")
	@Operation(summary = "Find all member integration change histories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all member integration change histories"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findAllIntegrationChangeHistories() {
		return ResponseEntity.ok(Response.ok(memberIntegrationService.findAllIntegrationChangeHistories()));
	}

	@GetMapping("/changeHistory/{memberId}")
	@Operation(summary = "Find member integration change histories by member id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of member integration change histories"),
			@ApiResponse(responseCode = "404", description = "Member integration change histories not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findIntegrationChangeHistoriesByMemberId(@PathVariable Long memberId) {
		return ResponseEntity.ok(Response.ok(memberIntegrationService.findIntegrationChangeHistoriesByMemberId(memberId)));
	}

	@GetMapping("/changeHistory/findByMemberUsername/{username}")
	@Operation(summary = "Find member integration change histories by member username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of member integration change histories"),
			@ApiResponse(responseCode = "404", description = "Member integration change histories not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberIntegrationChangeHistory>>> findIntegrationChangeHistoriesByMemberUsername(@PathVariable String username) {
		return ResponseEntity.ok(Response.ok(memberIntegrationService.findIntegrationChangeHistoriesByMemberUsername(username)));
	}

	@PostMapping("/changeHistory/{memberId}")
	@Operation(summary = "Create a new member integration change history")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member integration change history created"),
			@ApiResponse(responseCode = "409", description = "Conflict, member integration change history already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberIntegrationChangeHistory>> createIntegrationChangeHistory(@PathVariable Long memberId, @RequestBody MemberIntegrationChangeHistory memberIntegrationChangeHistory) {
		if (!memberId.equals(memberIntegrationChangeHistory.getMemberId())) {
			return ResponseEntity.status(400).body(Response.error("Path variable memberId does not match memberId in the request body"));
		}
		MemberIntegrationChangeHistory existingHistory = memberIntegrationService.findIntegrationChangeHistoryById(memberIntegrationChangeHistory.getId());
		if (existingHistory != null) {
			return ResponseEntity.status(409).body(Response.error("Conflict, member integration change history already exists"));
		}
		try {
			boolean created = memberIntegrationService.createIntegrationChangeHistory(memberIntegrationChangeHistory);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(201).body(Response.ok(memberIntegrationChangeHistory));
	}

	@PutMapping("/changeHistory/{id}")
	@Operation(summary = "Update a member integration change history")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member integration change history updated"),
			@ApiResponse(responseCode = "404", description = "Member integration change history not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberIntegrationChangeHistory>> updateIntegrationChangeHistory(@PathVariable Long id, @RequestBody MemberIntegrationChangeHistory memberIntegrationChangeHistory) {
		MemberIntegrationChangeHistory existingHistory = memberIntegrationService.findIntegrationChangeHistoryById(id);
		if (existingHistory == null) {
			return ResponseEntity.status(404).body(Response.error("Member integration change history not found"));
		}
		try {
			boolean updated = memberIntegrationService.updateIntegrationChangeHistory(memberIntegrationChangeHistory);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(200).body(Response.ok(memberIntegrationChangeHistory));
	}

	@DeleteMapping("/changeHistory/{id}")
	@Operation(summary = "Delete a member integration change history by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member integration change history deleted"),
			@ApiResponse(responseCode = "404", description = "Member integration change history not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> deleteIntegrationChangeHistory(@PathVariable Long id) {
		MemberIntegrationChangeHistory existingHistory = memberIntegrationService.findIntegrationChangeHistoryById(id);
		if (existingHistory == null) {
			return ResponseEntity.status(404).body(Response.error("Member integration change history not found"));
		}
		try {
			boolean deleted = memberIntegrationService.deleteIntegrationChangeHistory(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(204).body(Response.ok());
	}

	@GetMapping("/consumeSetting")
	@Operation(summary = "Find all member integration consume settings")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all member integration consume settings"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberIntegrationConsumeSetting>>> findAllIntegrationConsumeSettings() {
		return ResponseEntity.ok(Response.ok(memberIntegrationService.findAllIntegrationConsumeSettings()));
	}

	@GetMapping("/consumeSetting/{id}")
	@Operation(summary = "Find member integration consume setting by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member integration consume setting details"),
			@ApiResponse(responseCode = "404", description = "Member integration consume setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberIntegrationConsumeSetting>> findIntegrationConsumeSettingById(@PathVariable Long id) {
		return ResponseEntity.ok(Response.ok(memberIntegrationService.findIntegrationConsumeSettingById(id)));
	}

	@PostMapping("/consumeSetting")
	@Operation(summary = "Create a new member integration consume setting")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member integration consume setting created"),
			@ApiResponse(responseCode = "409", description = "Conflict, member integration consume setting already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberIntegrationConsumeSetting>> createIntegrationConsumeSetting(@RequestBody MemberIntegrationConsumeSetting memberIntegrationConsumeSetting) {
		MemberIntegrationConsumeSetting existingSetting = memberIntegrationService.findIntegrationConsumeSettingById(memberIntegrationConsumeSetting.getId());
		if (existingSetting != null) {
			return ResponseEntity.status(409).body(Response.error("Conflict, member integration consume setting already exists"));
		}
		try {
			boolean created = memberIntegrationService.createIntegrationConsumeSetting(memberIntegrationConsumeSetting);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(201).body(Response.ok(memberIntegrationConsumeSetting));
	}

	@PutMapping("/consumeSetting/{id}")
	@Operation(summary = "Update a member integration consume setting")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member integration consume setting updated"),
			@ApiResponse(responseCode = "404", description = "Member integration consume setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberIntegrationConsumeSetting>> updateIntegrationConsumeSetting(@PathVariable Long id, @RequestBody MemberIntegrationConsumeSetting memberIntegrationConsumeSetting) {
		MemberIntegrationConsumeSetting existingSetting = memberIntegrationService.findIntegrationConsumeSettingById(id);
		if (existingSetting == null) {
			return ResponseEntity.status(404).body(Response.error("Member integration consume setting not found"));
		}
		try {
			boolean updated = memberIntegrationService.updateIntegrationConsumeSetting(memberIntegrationConsumeSetting);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(200).body(Response.ok(memberIntegrationConsumeSetting));
	}

	@DeleteMapping("/consumeSetting/{id}")
	@Operation(summary = "Delete a member integration consume setting by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member integration consume setting deleted"),
			@ApiResponse(responseCode = "404", description = "Member integration consume setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> deleteIntegrationConsumeSetting(@PathVariable Long id) {
		MemberIntegrationConsumeSetting existingSetting = memberIntegrationService.findIntegrationConsumeSettingById(id);
		if (existingSetting == null) {
			return ResponseEntity.status(404).body(Response.error("Member integration consume setting not found"));
		}
		try {
			boolean deleted = memberIntegrationService.deleteIntegrationConsumeSetting(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(204).body(Response.ok());
	}
}