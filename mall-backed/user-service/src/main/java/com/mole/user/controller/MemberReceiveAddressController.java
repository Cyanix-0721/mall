package com.mole.user.controller;

import com.mole.common.entity.Response;
import com.mole.common.entity.user.Member;
import com.mole.common.entity.user.MemberReceiveAddress;
import com.mole.user.service.MemberReceiveAddressService;
import com.mole.user.service.MemberService;
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
@RequestMapping("/members/member-receive-addresses")
@Tag(name = "Member receiveAddress management")
public class MemberReceiveAddressController {

	private final MemberService memberService;
	private final MemberReceiveAddressService memberReceiveAddressService;

	@GetMapping("/{memberId}")
	@Operation(summary = "Find all receive addresses of a member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all receive addresses of a member"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberReceiveAddress>>> findReceiveAddressesByMemberId(@PathVariable Long memberId) {
		try {
			Member member = memberService.findById(memberId);
			if (member == null || member.getId() == null || member.getId().equals(0L)) {
				return ResponseEntity.status(404).body(Response.error("Member not found"));
			}
			List<MemberReceiveAddress> addresses = memberReceiveAddressService.findReceiveAddressesByMemberId(memberId);
			if (addresses.isEmpty()) {
				return ResponseEntity.status(404).body(Response.error("Member not found address"));
			}
			return ResponseEntity.ok(Response.ok(addresses));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Find all receive addresses of a member by username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all receive addresses of a member"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<MemberReceiveAddress>>> findReceiveAddressesByMemberUsername(@PathVariable String username) {
		try {
			Member member = memberService.findByUsername(username);
			if (member == null || member.getId() == null || member.getId().equals(0L)) {
				return ResponseEntity.status(404).body(Response.error("Member not found"));
			}
			List<MemberReceiveAddress> addresses = memberReceiveAddressService.findReceiveAddressesByMemberUsername(username);
			if (addresses.isEmpty()) {
				return ResponseEntity.status(404).body(Response.error("Member not found address"));
			}
			return ResponseEntity.ok(Response.ok(addresses));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PostMapping
	@Operation(summary = "Add a new receive address for a member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Receive address added successfully"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberReceiveAddress>> addReceiveAddress(@RequestBody MemberReceiveAddress address) {
		try {
			boolean result = memberReceiveAddressService.addReceiveAddress(address);
			if (!result) {
				return ResponseEntity.status(400).body(Response.error("Failed to add receive address"));
			}
			return ResponseEntity.ok(Response.ok(address));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PutMapping
	@Operation(summary = "Update a receive address for a member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Receive address updated successfully"),
			@ApiResponse(responseCode = "400", description = "Failed to update receive address"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<MemberReceiveAddress>> updateReceiveAddress(@RequestBody MemberReceiveAddress address) {
		try {
			boolean result = memberReceiveAddressService.updateReceiveAddress(address);
			if (!result) {
				return ResponseEntity.status(400).body(Response.error("Failed to update receive address"));
			}
			return ResponseEntity.ok(Response.ok(address));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a receive address for a member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Receive address deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Failed to delete receive address"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> deleteReceiveAddress(@PathVariable Long id) {
		try {
			boolean result = memberReceiveAddressService.deleteReceiveAddress(id);
			if (!result) {
				return ResponseEntity.status(400).body(Response.error("Failed to delete receive address"));
			}
			return ResponseEntity.ok(Response.ok());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}
}