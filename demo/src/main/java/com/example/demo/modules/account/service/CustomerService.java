package com.example.demo.modules.account.service;

import java.util.List;

import com.example.demo.modules.account.entity.Customer;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

public interface CustomerService {

	List<Customer> getCustomers();

	PageInfo<Customer> getCustomers(SearchVo searchVo);

}
