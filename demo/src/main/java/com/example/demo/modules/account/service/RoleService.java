package com.example.demo.modules.account.service;

import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.common.vo.Result;

public interface RoleService {
	Result<Role> insertRole(Role role);

	Role getRoleByRoleName(String roleName);

	// 登录
	Result<Role> login(Role role);

}
