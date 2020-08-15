package com.hqyj.SpringBoot.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.dao.StaffDao;
import com.hqyj.SpringBoot.account.entity.Staff;
import com.hqyj.SpringBoot.account.service.StaffService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.Result.ResultStatus;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	@Override
	public List<Staff> getStaffs() {
		return Optional.ofNullable(staffDao.getStaffs()).orElse(Collections.emptyList());
	}

	@Override
	@Transactional
	public Result<Staff> editStaff(Staff staff) {
		Staff staffTemp = staffDao.getStaffByStaffName(staff.getStaffName());
		if (staffTemp != null && staffTemp.getStaffId() != staff.getStaffId()) {
			return new Result<Staff>(ResultStatus.FAILED.status, "Staff name is repeat.");
		}

		if (staff.getStaffId() > 0) {
			staffDao.updateStaff(staff);
		} else {
			staffDao.addStaff(staff);
		}

		return new Result<Staff>(ResultStatus.SUCCESS.status, "success", staff);
	}

	@Override
	@Transactional
	public Result<Staff> deleteStaff(int staffId) {
		staffDao.deleteStaff(staffId);
		return new Result<Staff>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Staff> getStaffs(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(staffDao.getStaffsBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public List<Staff> getStaffsByUserId(int userId) {
		return staffDao.getStaffsByUserId(userId);
	}

	@Override
	public List<Staff> getStaffsByResourceId(int resourceId) {
		return staffDao.getStaffsByResourceId(resourceId);
	}

	@Override
	public Staff getStaffById(int staffId) {
		return staffDao.getStaffById(staffId);
	}
}
