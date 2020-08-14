package com.example.demo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.account.entity.Contract;
import com.example.demo.modules.account.service.ContractService;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

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

	/**
	 * 127.0.0.1/api/contracts ---- post
	 */
	@PostMapping(value = "/contracts", consumes = "application/json")
	public PageInfo<Contract> getContractsBySearchVo(@RequestBody SearchVo searchVo) {
		return contractService.getContractsBySearchVo(searchVo);
	}
}
