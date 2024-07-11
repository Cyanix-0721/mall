package com.mole.user.controller;

import com.mole.common.entity.Response;
import com.mole.common.entity.user.MemberGrowthChangeHistory;
import com.mole.common.entity.user.MemberLevel;
import com.mole.user.service.MemberGrowthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Member growth management")
@RequestMapping("/members/memberGrowth")
public class MemberGrowthController {

	private final MemberGrowthService memberGrowthService;

	@GetMapping("/history")
	@Operation(summary = "Find all member growth change histories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all member growth change histories"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findAll() {
		try {
			Response<List<MemberGrowthChangeHistory>> response = Response.ok(memberGrowthService.findAll());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/history/{memberId}")
	@Operation(summary = "Find member growth change histories by member id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of member growth change histories by member id"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findByMemberId(@PathVariable Long memberId) {
		try {
			Response<List<MemberGrowthChangeHistory>> response = Response.ok(memberGrowthService.findByMemberId(memberId));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/history/findByMemberUsername/{username}")
	@Operation(summary = "Find member growth change history by member username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of member growth change history by member username"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberGrowthChangeHistory>>> findByMemberUsername(@PathVariable String username) {
		try {
			Response<List<MemberGrowthChangeHistory>> response = Response.ok(memberGrowthService.findByMemberUsername(username));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@PostMapping("/history/{memberId}")
	@Operation(summary = "Create a new member growth change history")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member growth change history created"),
			@ApiResponse(responseCode = "400", description = "Path variable memberId does not match memberId in the request body"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> create(@PathVariable Long memberId, @RequestBody MemberGrowthChangeHistory memberGrowthChangeHistory) {
		if (!memberId.equals(memberGrowthChangeHistory.getMemberId())) {
			return ResponseEntity.status(400).body(Response.error("Path variable memberId does not match memberId in the request body"));
		}
		try {
			boolean created = memberGrowthService.create(memberGrowthChangeHistory);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(201).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PutMapping("/history/{id}")
	@Operation(summary = "Update a member growth change history")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member growth change history updated"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> update(@RequestBody MemberGrowthChangeHistory memberGrowthChangeHistory) {
		try {
			boolean updated = memberGrowthService.update(memberGrowthChangeHistory);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(200).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@DeleteMapping("/history/{id}")
	@Operation(summary = "Delete a member growth change history by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member growth change history deleted"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> delete(@PathVariable Long id) {
		try {
			boolean deleted = memberGrowthService.delete(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(204).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	//MemberLevel
	@GetMapping("/level")
	@Operation(summary = "Find all member levels")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all member levels"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberLevel>>> findAllMemberLevels() {
		try {
			Response<List<MemberLevel>> response = Response.ok(memberGrowthService.findAllMemberLevels());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/level/{id}")
	@Operation(summary = "Find member level by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member level details"),
			@ApiResponse(responseCode = "404", description = "Member level not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberLevel>> findMemberLevelById(@PathVariable Integer id) {
		try {
			MemberLevel memberLevel = memberGrowthService.findMemberLevelById(id);
			if (memberLevel == null) {
				return ResponseEntity.status(404).body(Response.error("Member level not found"));
			}
			return ResponseEntity.ok(Response.ok(memberLevel));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/level/findByLevelName/{levelName}")
	@Operation(summary = "Find member level by level name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member level details"),
			@ApiResponse(responseCode = "404", description = "Member level not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberLevel>> findMemberLevelByLevelName(@PathVariable String levelName) {
		try {
			MemberLevel memberLevel = memberGrowthService.findMemberLevelByLevelName(levelName);
			if (memberLevel == null) {
				return ResponseEntity.status(404).body(Response.error("Member level not found"));
			}
			return ResponseEntity.ok(Response.ok(memberLevel));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PostMapping("/level")
	@Operation(summary = "Create a new member level")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member level created"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> createMemberLevel(@RequestBody MemberLevel memberLevel) {
		try {
			boolean created = memberGrowthService.createMemberLevel(memberLevel);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(201).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PutMapping("/level/{id}")
	@Operation(summary = "Update a member level")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member level updated"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> updateMemberLevel(@RequestBody MemberLevel memberLevel) {
		try {
			boolean updated = memberGrowthService.updateMemberLevel(memberLevel);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(200).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@DeleteMapping("/level/{id}")
	@Operation(summary = "Delete a member level by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member level deleted"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Boolean>> deleteMemberLevel(@PathVariable Integer id) {
		try {
			boolean deleted = memberGrowthService.deleteMemberLevel(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
			return ResponseEntity.status(204).body(Response.ok(true));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}
}