package com.hqyj.SpringBoot.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Customer;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

public interface CustomerService {

	List<Customer> getCustomers();

	Result<Customer> editCustomer(Customer customer);

	Result<Customer> deleteCustomer(int customerId);

	PageInfo<Customer> getCustomers(SearchVo searchVo);

	List<Customer> getCustomersByUserId(int userId);

	List<Customer> getCustomersByResourceId(int resourceId);

	Customer getCustomerById(int customerId);
}
