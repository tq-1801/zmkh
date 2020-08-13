package com.example.demo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.account.entity.Contract;
import com.example.demo.modules.account.service.ContractService;

@RestController
@RequestMapping("/api")
public class ContractController {

	@Autowired
	private ContractService contractService;

	/**
	 * 127.0.0.1/api/contract
	 */
	@RequestMapping("/contract")
	List<Contract> getContracts() {
		return contractService.getContracts();
	}

}
