package com.example.demo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.account.service.RoleService;
import com.example.demo.modules.common.vo.Result;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 127.0.0.1/api/role ---- post
	 */
	@PostMapping(value = "/role", consumes = "application/json")
	public Result<Role> insertRole(@RequestBody Role role) {
		return roleService.insertRole(role);
	}

	/**
	 * 
	 * 127.0.0.1/api/login ---- post
	 */
	@PostMapping(value = "/login", consumes = "application/json")
	public Result<Role> login(@RequestBody Role role) {
		return roleService.login(role);
	}

}
