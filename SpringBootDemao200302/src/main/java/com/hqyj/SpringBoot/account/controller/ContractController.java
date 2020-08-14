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
import com.hqyj.SpringBoot.account.entity.Contract;
import com.hqyj.SpringBoot.account.service.ContractService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class ContractController {

	@Autowired
	private ContractService contractService;

	/**
	 * 127.0.0.1/api/contracts
	 */
	@RequestMapping("/contracts")
	List<Contract> getContracts() {
		return contractService.getContracts();
	}

	@PostMapping(value = "/contracts", consumes = "application/json")
	public PageInfo<Contract> getContracts(@RequestBody SearchVo searchVo) {
		return contractService.getContracts(searchVo);
	}

	@PostMapping(value = "/contract", consumes = "application/json")
	public Result<Contract> insertContract(@RequestBody Contract contract) {
		return contractService.editContract(contract);
	}

	@PutMapping(value = "/contract", consumes = "application/json")
	public Result<Contract> updateContract(@RequestBody Contract contract) {
		return contractService.editContract(contract);
	}

	@RequestMapping("/contract/{contractId}")
	public Contract getContract(@PathVariable int contractId) {
		return contractService.getContractById(contractId);
	}

	@DeleteMapping("/contract/{contractId}")
	public Result<Contract> deletContract(@PathVariable int contractId) {
		return contractService.deleteContract(contractId);
	}
}
