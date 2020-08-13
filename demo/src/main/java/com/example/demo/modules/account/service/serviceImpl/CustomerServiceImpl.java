package com.example.demo.modules.account.service.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.account.dao.CustomerDao;
import com.example.demo.modules.account.entity.Customer;
import com.example.demo.modules.account.service.CustomerService;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getCustomers() {
		return Optional.ofNullable(customerDao.getcustomers()).orElse(Collections.emptyList());
	}

	@Override
	public PageInfo<Customer> getCustomers(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<Customer>(
				Optional.ofNullable(customerDao.getCustomersBySearchVo(searchVo)).orElse(Collections.emptyList()));

	}

}
