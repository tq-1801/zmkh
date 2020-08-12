package com.example.demo.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.demo.modules.account.entity.Role;

public interface RoleDao {

	@Insert("insert into role (role_name, password) " + "values (#{roleName}, #{password})")
	@Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
	void insertRole(Role role);

	@Select("select * from role where role_name = #{roleName}")
	Role getRoleByRoleName(String roleName);

}
