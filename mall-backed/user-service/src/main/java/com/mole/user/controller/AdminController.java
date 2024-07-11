package com.mole.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.Response;
import com.mole.common.entity.user.Admin;
import com.mole.common.entity.user.Member;
import com.mole.user.service.AdminService;
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
@RequestMapping("/admins")
@Tag(name = "Admin management")
public class AdminController {

	private final AdminService adminService;

	@GetMapping
	@Operation(summary = "Find all admins")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all admins"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<List<Admin>>> findAllAdmins() {
		try {
			Response<List<Admin>> response = Response.ok(adminService.findAll());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/pages")
	@Operation(summary = "Find all admins with paging")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page of all admins"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Page<Admin>>> findAllPages(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		try {
			Page<Admin> memberPage = adminService.findAllPages(page, size);
			Response<Page<Admin>> response = Response.ok(memberPage);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find admin by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Admin details"),
			@ApiResponse(responseCode = "404", description = "Admin not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Admin>> findAdminById(@PathVariable Long id) {
		try {
			Admin admin = adminService.findById(id);
			if (admin == null) {
				return ResponseEntity.status(404).body(Response.error("Admin not found"));
			}
			return ResponseEntity.ok(Response.ok(admin));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Find admin by username")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Admin details"),
			@ApiResponse(responseCode = "404", description = "Admin not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Admin>> findAdminByUsername(@PathVariable String username) {
		try {
			Admin admin = adminService.findByUsername(username);
			if (admin == null) {
				return ResponseEntity.status(404).body(Response.error("Admin not found"));
			}
			return ResponseEntity.ok(Response.ok(admin));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
	}

	@PostMapping
	@Operation(summary = "Create admin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Admin created"),
			@ApiResponse(responseCode = "409", description = "Conflict, admin already exists"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Admin>> createAdmin(@RequestBody Admin admin) {
		Admin existingAdmin = adminService.findByUsername(admin.getUsername());
		if (existingAdmin != null) {
			return ResponseEntity.status(409).body(Response.error(409,"Conflict, admin already exists"));
		}
		try {
			admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
			admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
			boolean created = adminService.create(admin);
			if (!created) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(201).body(Response.ok(admin));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update admin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Admin updated"),
			@ApiResponse(responseCode = "304", description = "Not modified"),
			@ApiResponse(responseCode = "404", description = "Admin not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Admin>> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
		Admin existingAdmin = adminService.findById(id);
		if (existingAdmin == null) {
			return ResponseEntity.status(404).body(Response.error("Admin not found"));
		}
		try {
			boolean updated = adminService.update(admin);
			if (!updated) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(200).body(Response.ok(admin));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete admin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Admin deleted"),
			@ApiResponse(responseCode = "404", description = "Admin not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<Response<Void>> deleteAdmin(@PathVariable Long id) {
		Admin existingAdmin = adminService.findById(id);
		if (existingAdmin == null) {
			return ResponseEntity.status(404).body(Response.error("Admin not found"));
		}
		try {
			boolean deleted = adminService.delete(id);
			if (!deleted) {
				return ResponseEntity.status(500).body(Response.error("Internal server error"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Response.error("Internal server error"));
		}
		return ResponseEntity.status(204).body(Response.ok());
	}
}