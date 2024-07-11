package com.mole.user.controller;

import com.mole.common.entity.Response;
import com.mole.common.entity.user.MemberRuleSetting;
import com.mole.user.service.MemberRuleSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/ruleSetting")
@RequiredArgsConstructor
@Tag(name = "Member Rule Setting management")
public class MemberRuleSettingController {

	private final MemberRuleSettingService memberRuleSettingService;

	@GetMapping
	@Operation(summary = "Find all member rule settings")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all member rule settings"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<List<MemberRuleSetting>> findAllRuleSettings() {
		return ResponseEntity.ok(memberRuleSettingService.findAllRuleSettings());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find member rule setting by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member rule setting details"),
			@ApiResponse(responseCode = "404", description = "Member rule setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<MemberRuleSetting> findRuleSettingById(@PathVariable Long id) {
		return ResponseEntity.ok(memberRuleSettingService.findRuleSettingById(id));
	}

	@PostMapping
	@Operation(summary = "Create a new member rule setting")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member rule setting created"),
			@ApiResponse(responseCode = "409", description = "Conflict, member rule setting already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberRuleSetting>> createRuleSetting(@RequestBody MemberRuleSetting memberRuleSetting) {
		MemberRuleSetting existingRuleSetting = memberRuleSettingService.findRuleSettingById(memberRuleSetting.getId());
		if (existingRuleSetting != null) {
			return ResponseEntity.status(409).body(Response.error("Conflict, member rule setting already exists"));
		}
		try {
			boolean created = memberRuleSettingService.createRuleSetting(memberRuleSetting);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(201).body(Response.ok(memberRuleSetting));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a member rule setting")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member rule setting updated"),
			@ApiResponse(responseCode = "404", description = "Member rule setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberRuleSetting>> updateRuleSetting(@PathVariable Long id, @RequestBody MemberRuleSetting memberRuleSetting) {
		MemberRuleSetting existingRuleSetting = memberRuleSettingService.findRuleSettingById(id);
		if (existingRuleSetting == null) {
			return ResponseEntity.status(404).body(Response.error("Member rule setting not found"));
		}
		try {
			boolean updated = memberRuleSettingService.updateRuleSetting(memberRuleSetting);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(200).body(Response.ok(memberRuleSetting));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a member rule setting by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member rule setting deleted"),
			@ApiResponse(responseCode = "404", description = "Member rule setting not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> deleteRuleSetting(@PathVariable Long id) {
		MemberRuleSetting existingRuleSetting = memberRuleSettingService.findRuleSettingById(id);
		if (existingRuleSetting == null) {
			return ResponseEntity.status(404).body(Response.error("Member rule setting not found"));
		}
		try {
			boolean deleted = memberRuleSettingService.deleteRuleSetting(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(204).body(Response.ok());
	}
}