package com.hqyj.SpringBoot.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.dao.CustomerDao;
import com.hqyj.SpringBoot.account.entity.Customer;
import com.hqyj.SpringBoot.account.service.CustomerService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.Result.ResultStatus;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getCustomers() {
		return Optional.ofNullable(customerDao.getCustomers()).orElse(Collections.emptyList());
	}

	@Override
	@Transactional
	public Result<Customer> editCustomer(Customer customer) {
		Customer customerTemp = customerDao.getCustomerByCustomerName(customer.getCustomerName());
		if (customerTemp != null && customerTemp.getCustomerId() != customer.getCustomerId()) {
			return new Result<Customer>(ResultStatus.FAILED.status, "Customer name is repeat.");
		}

		if (customer.getCustomerId() > 0) {
			customerDao.updateCustomer(customer);
		} else {
			customerDao.addCustomer(customer);
		}

		return new Result<Customer>(ResultStatus.SUCCESS.status, "success", customer);
	}

	@Override
	@Transactional
	public Result<Customer> deleteCustomer(int customerId) {
		customerDao.deleteCustomer(customerId);
		return new Result<Customer>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Customer> getCustomers(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(customerDao.getCustomersBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public List<Customer> getCustomersByUserId(int userId) {
		return customerDao.getCustomersByUserId(userId);
	}

	@Override
	public List<Customer> getCustomersByResourceId(int resourceId) {
		return customerDao.getCustomersByResourceId(resourceId);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return customerDao.getCustomerById(customerId);
	}
}
