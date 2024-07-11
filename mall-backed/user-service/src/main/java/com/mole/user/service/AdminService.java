package com.mole.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mole.common.entity.user.Admin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Admin management")
public interface AdminService {

	@Operation(summary = "Find all admins")
	List<Admin> findAll();

	@Operation(summary = "Find all admins with paging")
	Page<Admin> findAllPages(int page, int size);

	@Operation(summary = "Find admin by id")
	Admin findById(Long id);

	@Operation(summary = "Find admin by username")
	Admin findByUsername(String username);

	@Operation(summary = "Find admin by status")
	Admin findByStatus(Integer status);

	@Operation(summary = "Create admin")
	boolean create(Admin admin);

	@Operation(summary = "Update admin")
	boolean update(Admin admin);

	@Operation(summary = "Delete admin")
	boolean delete(Long id);
}