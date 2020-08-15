package com.hqyj.SpringBoot.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Staff;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

public interface StaffService {

	List<Staff> getStaffs();

	Result<Staff> editStaff(Staff staff);

	Result<Staff> deleteStaff(int staffId);

	PageInfo<Staff> getStaffs(SearchVo searchVo);

	List<Staff> getStaffsByUserId(int userId);

	List<Staff> getStaffsByResourceId(int resourceId);

	Staff getStaffById(int staffId);
}
