package com.example.demo.modules.account.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modules.account.dao.RoleDao;
import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.account.service.RoleService;
import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.Result.ResultStatus;
import com.example.demo.modules.utils.MD5Util;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public Result<Role> insertRole(Role role) {
		Role roleTemp = getRoleByRoleName(role.getRoleName());
		if (roleTemp != null) {
			return new Result<Role>(ResultStatus.FAILED.status, "Role name is repeat.");
		}

		role.setPassword(MD5Util.getMD5(role.getPassword()));

		roleDao.insertRole(role);
		return new Result<Role>(ResultStatus.SUCCESS.status, "Insert success.", role);
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		return roleDao.getRoleByRoleName(roleName);
	}

	// 登录
	@Override
	public Result<Role> login(Role role) {
		Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());

		if (roleTemp == null || !roleTemp.getPassword().equals(MD5Util.getMD5(role.getPassword()))) {
			return new Result<Role>(ResultStatus.FAILED.status, "用户或密码错误.");
		}

		return new Result<Role>(ResultStatus.SUCCESS.status, "登录成功.", roleTemp);
	}

}
