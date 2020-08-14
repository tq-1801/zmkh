package com.hqyj.SpringBoot.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Customer;
import com.hqyj.SpringBoot.account.service.CustomerService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 127.0.0.1/api/customers
	 */
	@RequestMapping("/customers")
	List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@PostMapping(value = "/customers", consumes = "application/json")
	public PageInfo<Customer> getCustomers(@RequestBody SearchVo searchVo) {
		return customerService.getCustomers(searchVo);
	}

	@PostMapping(value = "/customer", consumes = "application/json")
	public Result<Customer> insertCustomer(@RequestBody Customer customer) {
		return customerService.editCustomer(customer);
	}

	@PutMapping(value = "/customer", consumes = "application/json")
	public Result<Customer> updateCustomer(@RequestBody Customer customer) {
		return customerService.editCustomer(customer);
	}

	@RequestMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return customerService.getCustomerById(customerId);
	}

	@DeleteMapping("/customer/{customerId}")
	public Result<Customer> deletCustomer(@PathVariable int customerId) {
		return customerService.deleteCustomer(customerId);
	}
}
