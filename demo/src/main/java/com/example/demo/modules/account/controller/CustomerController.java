package com.example.demo.modules.account.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.account.entity.Customer;
import com.example.demo.modules.account.service.CustomerService;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class CustomerController {
	private CustomerService customerService;

	/**
	 * 127.0.0.1/api/customer ---- post
	 */
	@PostMapping(value = "/customer", consumes = "application/json")
	public PageInfo<Customer> getCustomersBySearchVo(@RequestBody SearchVo searchVo) {
		return customerService.getCustomers(searchVo);
	}
}
