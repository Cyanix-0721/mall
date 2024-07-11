package com.mole.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Member;
import com.mole.user.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member management")
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	@Operation(summary = "Find all members")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Member>>> findAll() {
		try {
			Response<List<Member>> response = Response.ok(memberService.findAll());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/pages")
	@Operation(summary = "Find all members with paging")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page of all members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Page<Member>>> findAllPages(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		try {
			Page<Member> memberPage = memberService.findAllPages(page, size);
			Response<Page<Member>> response = Response.ok(memberPage);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find member by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member details"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Member>> findById(@PathVariable Long id) {
		try {
			Member member = memberService.findById(id);
			if (member == null) {
				return ResponseEntity.status(404).body(Response.error("Member not found"));
			}
			return ResponseEntity.ok(Response.ok(member));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Find member by username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member details"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Member>> findByUsername(@PathVariable String username) {
		try {
			Member member = memberService.findByUsername(username);
			if (member == null) {
				return ResponseEntity.status(404).body(Response.error("Member not found"));
			}
			return ResponseEntity.ok(Response.ok(member));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/members/nickname/{nickname}")
	public ResponseEntity<Response<List<Member>>> findByNicknameFuzzy(@PathVariable String nickname) {
		try {
			List<Member> members = memberService.findByNicknameFuzzy(nickname);
			if (members.isEmpty()) {
				return ResponseEntity.status(404).body(Response.error("Members not found"));
			}
			return ResponseEntity.ok(Response.ok(members));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/phone/{phone}")
	@Operation(summary = "Find member by phone")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member details"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Member>> findByPhone(@PathVariable String phone) {
		try {
			Member member = memberService.findByPhone(phone);
			if (member == null) {
				return ResponseEntity.status(404).body(Response.error("Member not found"));
			}
			return ResponseEntity.ok(Response.ok(member));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/status/{status}")
	@Operation(summary = "Find members by status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Member>>> findByStatus(@PathVariable Integer status) {
		try {
			List<Member> members = memberService.findByStatus(status);
			return ResponseEntity.ok(Response.ok(members));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/memberLevelId/{memberLevelId}")
	@Operation(summary = "Find members by member level id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Member>>> findByMemberLevelId(@PathVariable Long memberLevelId) {
		try {
			List<Member> members = memberService.findByMemberLevelId(memberLevelId);
			return ResponseEntity.ok(Response.ok(members));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/gender/{gender}")
	@Operation(summary = "Find members by gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Member>>> findByGender(@PathVariable Integer gender) {
		try {
			List<Member> members = memberService.findByGender(gender);
			return ResponseEntity.ok(Response.ok(members));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/city/{city}")
	@Operation(summary = "Find members by city")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of members"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Member>>> findByCity(@PathVariable String city) {
		try {
			List<Member> members = memberService.findByCity(city);
			return ResponseEntity.ok(Response.ok(members));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PostMapping
	@Operation(summary = "Create member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Member created"),
			@ApiResponse(responseCode = "409", description = "Conflict, member already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Member>> create(@RequestBody Member member) {
		Member existingMember = memberService.findByUsername(member.getUsername());
		if (existingMember != null) {
			return ResponseEntity.status(409).body(Response.error("Conflict, member already exists"));
		}
		try {
			member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
			member.setCreateTime(new Timestamp(System.currentTimeMillis()));
			boolean created = memberService.create(member);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(201).body(Response.ok(member));
	}

	@PutMapping
	@Operation(summary = "Update member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Member updated"),
			@ApiResponse(responseCode = "304", description = "Not modified"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Member>> update(@RequestBody Member member) {
		Member existingMember = memberService.findById(member.getId());
		if (existingMember == null) {
			return ResponseEntity.status(404).body(Response.error("Member not found"));
		}
		try {
			boolean updated = memberService.update(member);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(200).body(Response.ok(member));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete member")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Member deleted"),
			@ApiResponse(responseCode = "404", description = "Member not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
		Member existingMember = memberService.findById(id);
		if (existingMember == null) {
			return ResponseEntity.status(404).body(Response.error("Member not found"));
		}
		try {
			boolean deleted = memberService.delete(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(204).body(Response.ok());
	}

}