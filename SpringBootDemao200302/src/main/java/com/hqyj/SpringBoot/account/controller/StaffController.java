package com.hqyj.SpringBoot.account.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Staff;
import com.hqyj.SpringBoot.account.service.StaffService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class StaffController {

	@Autowired
	private StaffService staffService;

	/**
	 * 127.0.0.1/api/staffs
	 */
	@RequestMapping("/staffs")
	List<Staff> getStaffs() {
		return staffService.getStaffs();
	}

	@PostMapping(value = "/staffs", consumes = "application/json")
	public PageInfo<Staff> getStaffs(@RequestBody SearchVo searchVo) {
		return staffService.getStaffs(searchVo);
	}

	@PostMapping(value = "/staff", consumes = "application/json")
	public Result<Staff> insertStaff(@RequestBody Staff staff) {
		return staffService.editStaff(staff);
	}

	@PutMapping(value = "/staff", consumes = "application/json")
	public Result<Staff> updateStaff(@RequestBody Staff staff) {
		return staffService.editStaff(staff);
	}

	@RequestMapping("/staff/{staffId}")
	public Staff getStaff(@PathVariable int staffId) {
		return staffService.getStaffById(staffId);
	}

	@DeleteMapping("/staff/{staffId}")
	@RequiresPermissions(value = { "/api/staff" }, logical = Logical.OR)
	public Result<Staff> deletStaff(@PathVariable int staffId) {
		return staffService.deleteStaff(staffId);
	}
}
